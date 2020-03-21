package provotor.petprojects.layers.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Lists of {@link RuleIn} and {@link RuleIsNull}.
 * We use "and" for this rules, for example:
 *      ruleIn1 AND ruleIn2 AND rulesIsNull1 and rulesIsNull2.
 * TODO: use {@link java.util.Set}
 */
public class RulesSet {
    private List<RuleIn> rulesIn = new ArrayList<>();
    private List<RuleIsNull> rulesIsNull = new ArrayList<>();

    public static RulesSet fromIn(RuleIn... rulesIn) {
        RulesSet r = new RulesSet();
        r.setRulesIn(Arrays.asList(rulesIn));
        return r;
    }

    public List<RuleIn> getRulesIn() {
        return rulesIn;
    }

    public void setRulesIn(List<RuleIn> rulesIn) {
        this.rulesIn = rulesIn;
    }

    public List<RuleIsNull> getRulesIsNull() {
        return rulesIsNull;
    }

    public void setRulesIsNull(List<RuleIsNull> rulesIsNull) {
        this.rulesIsNull = rulesIsNull;
    }
}
