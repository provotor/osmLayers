package provotor.petprojects.pbf;

import java.util.Objects;

public class PbfToDatabaseTask {
    private String pbfId;
    private Bound bound;

    public PbfToDatabaseTask(String pbfId, Bound bound) {
        this.pbfId = pbfId;
        this.bound = bound;
    }

    public PbfToDatabaseTask() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PbfToDatabaseTask that = (PbfToDatabaseTask) o;
        return Objects.equals(pbfId, that.pbfId) &&
                Objects.equals(bound, that.bound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pbfId, bound);
    }

    public String getPbfId() {
        return pbfId;
    }

    public void setPbfId(String pbfId) {
        this.pbfId = pbfId;
    }

    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }

    @Override
    public String toString() {
        return "PbfCutCommand{" +
                "pbfName='" + pbfId + '\'' +
                ", bound=" + bound +
                '}';
    }
}
