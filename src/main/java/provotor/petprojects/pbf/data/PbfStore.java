package provotor.petprojects.pbf.data;

import provotor.petprojects.pbf.PbfInfo;

import java.io.File;
import java.util.List;

public interface PbfStore {
    String OSM_PBF_EXTENSION = ".osm.pbf";

    List<PbfInfo> getPbfList();
}
