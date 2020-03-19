package provotor.petprojects.pgdnapshotdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NODES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Node extends OsmElement {
    public Node() {
    }
}
