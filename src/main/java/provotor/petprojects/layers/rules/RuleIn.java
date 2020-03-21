package provotor.petprojects.layers.rules;

import java.util.Arrays;
import java.util.List;

/**
 * List of acceptable (or not acceptable) values in the specified OSM tag
 */
public class RuleIn {
    private Boolean isNot = false;
    private String tag;
    private List<String> values;

    public RuleIn() {
    }

    public RuleIn(Boolean isNot, String tag, List<String> values) {
        this.isNot = isNot;
        this.tag = tag;
        this.values = values;
    }

    public RuleIn(String tag, String ... v) {
        this.tag = tag;
        this.values = Arrays.asList(v);
    }

    public Boolean getNot() {
        return isNot;
    }

    public void setNot(Boolean not) {
        isNot = not;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
