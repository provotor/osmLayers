package provotor.petprojects.layers.rules;

/**
 * A tag that must be present or absent
 */
public class RuleIsNull {
    private Boolean isNot = false;
    private String tag;

    public RuleIsNull() {
    }

    public RuleIsNull(Boolean isNot, String tag) {
        this.isNot = isNot;
        this.tag = tag;
    }

    public RuleIsNull(String tag) {
        this.tag = tag;
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
}
