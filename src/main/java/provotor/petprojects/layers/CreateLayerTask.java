package provotor.petprojects.layers;

import provotor.petprojects.layers.rules.RulesSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateLayerTask {
    private GeometryType geometryType;

    /**
     * New of the new table
     */
    private String name;

    /**
     * Set of rules.
     * We use "OR" between elements of the set, for example:
     *      (ruleSet1) OR (ruleSet2) OR (ruleSet3)
     * TODO: use {@link java.util.Set}
     */
    private List<RulesSet> rules = new ArrayList<>();

    /**
     * List of fields in the new table
     */
    private List<String> fields = new ArrayList<>();

    public CreateLayerTask() {}

    public CreateLayerTask(GeometryType geometryType) {
        this.geometryType = geometryType;
    }

    public GeometryType getGeometryType() {
        return geometryType;
    }

    public void setGeometryType(GeometryType geometryType) {
        this.geometryType = geometryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RulesSet> getRules() {
        return rules;
    }

    public void setRules(List<RulesSet> rules) {
        this.rules = rules;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public void setFieldsList(String... fields) {
        this.fields = Arrays.asList(fields);
    }

    @Override
    public String toString() {
        return "CreateLayerTask{" +
                "geometryType=" + geometryType +
                ", name='" + name + '\'' +
                ", rules=" + rules.size() +
                ", fields=" + fields +
                '}';
    }

    public void add(RulesSet... fromIn) {
        rules.addAll(Arrays.asList(fromIn));
    }
}
