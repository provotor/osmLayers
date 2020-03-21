package provotor.petprojects.layers;

import org.postgis.Geometry;

public enum GeometryType {
    POINT(Geometry.MULTIPOINT),
    LINE(Geometry.MULTILINESTRING),
    POLYGON(Geometry.MULTIPOLYGON);

    private final int type;

    GeometryType(int typePostgresql) {
        this.type = typePostgresql;
    }

    public String getName() {
        return Geometry.getTypeString(type);
    }
}
