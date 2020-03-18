package provotor.petprojects.pbf;

import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
import org.openstreetmap.osmosis.osmbinary.Osmformat;
import org.openstreetmap.osmosis.pbf2.v0_6.impl.HeaderSeeker;
import org.openstreetmap.osmosis.pbf2.v0_6.impl.StreamSplitter;

import java.io.*;

public class OsmosisHelper {
    private static final double COORDS_TO_DEGREES_COEF = 1.0*1_000_000_000;

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
}