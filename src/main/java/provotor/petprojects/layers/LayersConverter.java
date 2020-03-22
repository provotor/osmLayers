package provotor.petprojects.layers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LayersConverter {
    private JdbcTemplate jdbcTemplate;

    public LayersConverter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void convert(CreateLayerTask task) {
        LayerQueriesBuilder b = new LayerQueriesBuilder(task);
        jdbcTemplate.execute(b.getDropTableSql());
        jdbcTemplate.execute(b.getCreateTableSql());
        jdbcTemplate.execute(b.getCreateIndexSql());
        jdbcTemplate.execute(b.getConvertSql());
    }
}
