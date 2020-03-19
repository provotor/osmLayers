package provotor.petprojects.pgdnapshotdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WAYS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Way extends OsmElement {
    public Way() {
    }
}
