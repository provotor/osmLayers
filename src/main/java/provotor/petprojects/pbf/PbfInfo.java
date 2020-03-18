package provotor.petprojects.pbf;

import java.io.Serializable;
import java.util.Objects;

public class PbfInfo implements Serializable {
    private String id;
    private String name;
    private Bound bound;

    public PbfInfo() {
    }

    public PbfInfo(String name, Bound bound) {
        this(null, name, bound);
    }

    public PbfInfo(String id, String name, Bound bound) {
        this.id = id;
        this.name = name;
        this.bound = bound;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PbfInfo pbfInfo = (PbfInfo) o;
        return Objects.equals(id, pbfInfo.id) &&
                Objects.equals(name, pbfInfo.name) &&
                Objects.equals(bound, pbfInfo.bound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bound);
    }

    @Override
    public String toString() {
        return "PbfInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bound=" + bound +
                '}';
    }
}
