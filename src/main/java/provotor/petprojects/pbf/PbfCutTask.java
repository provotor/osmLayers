package provotor.petprojects.pbf;

import java.util.Objects;

public class PbfCutTask {
    private String pbfSourceId;
    private String newPbfName;
    private Bound bound;

    public PbfCutTask(String newPbfName, Bound bound) {
        this.newPbfName = newPbfName;
        this.bound = bound;
    }

    public PbfCutTask() {
    }

    public String getNewPbfName() {
        return newPbfName;
    }

    public void setNewPbfName(String newPbfName) {
        this.newPbfName = newPbfName;
    }

    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }

    public String getPbfSourceId() {
        return pbfSourceId;
    }

    public void setPbfSourceId(String pbfSourceId) {
        this.pbfSourceId = pbfSourceId;
    }

    @Override
    public String toString() {
        return "PbfCutCommand{" +
                "pbfSourceId='" + pbfSourceId + '\'' +
                ", newPbfName='" + newPbfName + '\'' +
                ", bound=" + bound +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PbfCutTask that = (PbfCutTask) o;
        return Objects.equals(pbfSourceId, that.pbfSourceId) &&
                Objects.equals(newPbfName, that.newPbfName) &&
                Objects.equals(bound, that.bound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pbfSourceId, newPbfName, bound);
    }
}
