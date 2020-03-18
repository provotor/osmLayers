package provotor.petprojects.pbf.data;

import provotor.petprojects.pbf.PbfInfo;

import java.io.File;
import java.util.List;

public interface PbfStore {
    String OSM_PBF_EXTENSION = ".osm.pbf";

    List<PbfInfo> getPbfList();

    File getPbfById(String pbfId);

    // TODO I don't like it, and I going to use table in database with information about PBF files
    PbfInfo getPbfInfoByPbf(File pbf);

    File newPbfFile(String newName);

    void refresh();
}
