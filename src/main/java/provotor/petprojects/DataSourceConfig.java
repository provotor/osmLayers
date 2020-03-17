package provotor.petprojects;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:project.properties")
public class DataSourceConfig {
    @Value( "${jdbc.url}" )
    private String jdbcUrl;

    @Value( "${jdbc.driver}" )
    private String jdbcDriver;

    @Value( "${jdbc.host}" )
    private String jdbcHost;

    @Value( "${jdbc.password}" )
    private String jdbcPassword;

    @Value( "${jdbc.user}" )
    private String jdbcUser;

    @Value( "${jdbc.port}" )
    private String jdbcPort;


    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPassword);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource(), true);
    }
}
