package provotor.petprojects.layers;

import provotor.petprojects.layers.rules.RuleIn;
import provotor.petprojects.layers.rules.RuleIsNull;
import provotor.petprojects.layers.rules.RulesSet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LayerQueriesBuilder {
    private static final String templateFields = "tags -> '%s' as %s";
    private static final String templateSelect = "SELECT \n %s FROM %s WHERE \n %s";
    private static final String templateDrop = "DROP TABLE IF EXISTS \"%s\"";
    private static final String templateCreate = "CREATE TABLE \"%s\" \n" +
            " (id  bigint not null, geom geometry(%s, 4326), %s varchar)";
    private static final String templateCreateIndex = "CREATE INDEX \"%s_geom_idx\"\n on \"%s\" (geom)";
    private static final String templateConvert = "INSERT INTO \"%s\" (id, geom, %s) \n" +
            "SELECT id, st_multi(geom) as geom, %s \nFROM %s \nWHERE %s";

    private static final String templatePositiveIsNull = "tags -> '%s' IS NULL";
    private static final String templateNegativeIsNull = "tags -> '%s' IS NOT NULL";

    private static final String templatePositiveIn = "tags -> '%s' IN ('%s')";
    private static final String templateNegativeIn = "tags -> '%s' NOT IN ('%s')";

    private CreateLayerTask task;

    public LayerQueriesBuilder(CreateLayerTask task) {
        this.task = task;
    }

    private String getSqlRules() {
        return task.getRules().stream().map(this::getSql)
                .collect(Collectors.joining(") OR (", "(", ")"));
    }

    public String getSelectFromPgSnapshotSql() {
        List<String> selectFieldsList = new ArrayList<>(task.getFields().size());
        for (String f: task.getFields()) {
            selectFieldsList.add(String.format(templateFields, f, f));
        }
        String selectFields = String.join(", ", selectFieldsList);
        return String.format(templateSelect, selectFields, getActualTable(), getSqlRules());
    }

    public String getCountFromLayerSql() {
        return String.format(templateSelect, "COUNT(*) as count", task.getName(), "TRUE");
    }

    public String getCreateTableSql() {
        String selectFields = String.join(" varchar, ", task.getFields());
        return String.format(templateCreate, task.getName(), task.getGeometryType().getName(), selectFields);
    }

    public String getCreateIndexSql() {
        return String.format(templateCreateIndex, task.getName(), task.getName());
    }

    public String getDropTableSql() {
        return String.format(templateDrop, task.getName());
    }

    private String getSql(RulesSet r) {
        Stream<String> s1 = r.getRulesIn().stream().map(this::getSql);
        Stream<String> s2 = r.getRulesIsNull().stream().map(this::getSql);
        return Stream.concat(s1, s2).collect(Collectors.joining(" ) AND ( ", " ( ", " ) "));
    }

    private String getSql(RuleIsNull r) {
        if (r.getNot())
            return String.format(templateNegativeIsNull, r.getTag());
        else
            return String.format(templatePositiveIsNull, r.getTag());
    }

    private String getSql(RuleIn r) {
        String valuesStr = String.join("' ,", r.getValues());
        if (r.getNot())
            return String.format(templateNegativeIn, r.getTag(), valuesStr);
        else
            return String.format(templatePositiveIn, r.getTag(), valuesStr);
    }

    public String getConvertSql() {
        String fieldsInsert = String.join(", ", task.getFields());
        List<String> selectFieldsList = new ArrayList<>(task.getFields().size());
        for (String f: task.getFields()) {
            selectFieldsList.add(String.format(templateFields, f, f));
        }
        String selectFields = String.join(", ", selectFieldsList);
        return String.format(templateConvert, task.getName(), fieldsInsert, selectFields, getActualTable(), getSqlRules());
    }

    private String getActualTable() {
        switch (task.getGeometryType()) {
            case POINT: return "nodes";
            case LINE: return "ways";
            case POLYGON: return "multipolygons";
        }
        return null;
    }
}
