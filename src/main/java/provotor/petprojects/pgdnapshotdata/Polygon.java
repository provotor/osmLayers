package provotor.petprojects.pgdnapshotdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NODES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Polygon extends OsmElement {
    public Polygon() {
    }
}
