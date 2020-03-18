package provotor.petprojects.pbf.data;

import org.springframework.beans.factory.annotation.Autowired;
import provotor.petprojects.pbf.OsmosisHelper;
import provotor.petprojects.pbf.PbfInfo;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple implementation {@link PbfStore}
 */
public class PbfStoreImpl implements PbfStore {
    private File storeFolder;
    private Map<PbfInfo, File> pbfFileById;

    private final OsmosisHelper osmosisHelper;

    public PbfStoreImpl(OsmosisHelper osmosisHelper, File storeFolder) {
        this.storeFolder = storeFolder;
        this.osmosisHelper = osmosisHelper;
    }

    @PostConstruct
    public void init() {
        pbfFileById = new HashMap<>();
        File[] files = storeFolder.listFiles(file -> file.getName().endsWith(OSM_PBF_EXTENSION));
        if (files == null) {
            throw new NullPointerException();
        }
        for(File f: files) {
            PbfInfo pbfInfo = osmosisHelper.getPbfInfo(f);
            pbfInfo.setId(f.getName());
            pbfFileById.put(pbfInfo, f);
        }
    }

    @Override
    public List<PbfInfo> getPbfList() {
        return new ArrayList<>(pbfFileById.keySet());
    }
}
