package provotor.petprojects.pbf;

import crosby.binary.osmosis.OsmosisSerializer;
import org.openstreetmap.osmosis.areafilter.v0_6.AreaFilter;
import org.openstreetmap.osmosis.areafilter.v0_6.BoundingBoxFilter;
import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
import org.openstreetmap.osmosis.core.filter.common.IdTrackerType;
import org.openstreetmap.osmosis.osmbinary.Osmformat;
import org.openstreetmap.osmosis.osmbinary.file.BlockOutputStream;
import org.openstreetmap.osmosis.pbf2.v0_6.PbfReader;
import org.openstreetmap.osmosis.pbf2.v0_6.impl.HeaderSeeker;
import org.openstreetmap.osmosis.pbf2.v0_6.impl.StreamSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import provotor.petprojects.pbf.data.PbfStore;
import provotor.petprojects.pbf.data.PbfStoreImpl;

import java.io.*;

@Component
public class OsmosisHelper {
    private static final double COORDS_TO_DEGREES_COEF = 1.0*1_000_000_000;

    private PbfStore pbfStore;

    /**
     * Circular dependency. {@link  PbfStoreImpl#setOsmosisHelper(OsmosisHelper)}
     */
    @Autowired
    public void setPbfStore(PbfStore pbfStore) {
        this.pbfStore = pbfStore;
    }

    public PbfInfo getPbfInfo(File file) {
        StreamSplitter streamSplitter = null;
        Osmformat.HeaderBlock header;
        try {
            InputStream inputStream;
            try {
                inputStream = new FileInputStream(file);
            } catch (IOException var2) {
                throw new OsmosisRuntimeException("Unable to read PBF file " + file + ".", var2);
            }
            streamSplitter = new StreamSplitter(new DataInputStream(inputStream));
            header = (new HeaderSeeker()).apply(streamSplitter);
        } finally {
            if (streamSplitter != null) {
                streamSplitter.close();
            }
        }
        return getPbfInfo(header, file);
    }

    private PbfInfo getPbfInfo(Osmformat.HeaderBlock hb, File file) {
        Osmformat.HeaderBBox bBox = hb.getBbox();
        double c = COORDS_TO_DEGREES_COEF;
        Bound bound = new Bound(bBox.getLeft()/c, bBox.getRight()/c,
                bBox.getBottom()/c, bBox.getTop()/c);
        return new PbfInfo(file.getName(), bound);
    }


    public File cutPbfToPbf(PbfCutTask pbfCutTask) throws FileNotFoundException {
        Bound bound = pbfCutTask.getBound();
        int workers = Runtime.getRuntime().availableProcessors();
        if (workers > 1)
            workers -= 1;
        File source = pbfStore.getPbfById(pbfCutTask.getPbfSourceId());
        File destination = pbfStore.newPbfFile(pbfCutTask.getNewPbfName());
        PbfReader pbfReader = new PbfReader(source, workers);
        OsmosisSerializer osmosisSerializer = null;
        osmosisSerializer = new OsmosisSerializer(new BlockOutputStream(new FileOutputStream(destination)));
        AreaFilter filter = new BoundingBoxFilter(IdTrackerType.Dynamic,
                bound.getLeft(),
                bound.getRight(),
                bound.getTop(),
                bound.getBottom(),
                true, true, true, false
        );
        filter.setSink(osmosisSerializer);
        pbfReader.setSink(filter);
        pbfReader.run();
        return destination;
    }
}
