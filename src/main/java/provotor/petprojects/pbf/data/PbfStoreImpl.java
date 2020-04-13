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
        if (!storeFolder.exists() || !storeFolder.isDirectory()) {
            storeFolder.mkdir();
        } else {
            File[] files = storeFolder.listFiles(file -> file.getName().endsWith(OSM_PBF_EXTENSION));
            for (File f : files) {
                PbfInfo pbfInfo = osmosisHelper.getPbfInfo(f);
                pbfInfo.setId(f.getName());
                pbfFileById.put(pbfInfo, f);
            }
        }
    }

    @Override
    public List<PbfInfo> getPbfList() {
        return new ArrayList<>(pbfFileById.keySet());
    }


    @Override
    public File getPbfById(String pbfId) {
        return pbfFileById.entrySet().stream()
                .filter(e -> e.getKey().getId().equals(pbfId))
                .findFirst().get().getValue();
    }


    @Override // TODO I don't like it, may be I have to use table in database with information about PBF files
    public PbfInfo getPbfInfoByPbf(File pbf) {
        return pbfFileById.entrySet().stream()
                .filter(e -> pbf.equals(e.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().get();
    }

    @Override
    public File newPbfFile(String newName) {
        return new File(storeFolder, newName + OSM_PBF_EXTENSION);
    }

    @Override
    public void refresh() {
        init();
    }
}
