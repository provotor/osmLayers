package provotor.petprojects.pgdnapshotdata;

import org.springframework.data.jpa.domain.AbstractPersistable;

public class OsmElement extends AbstractPersistable<Long> {
    public OsmElement() {
    }

    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
