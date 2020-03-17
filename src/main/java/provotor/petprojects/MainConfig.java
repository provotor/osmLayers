package provotor.petprojects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@SpringBootApplication
@PropertySource("classpath:project.properties")
public class MainConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    public static void main(String[] args) {
        ConfigurableApplicationContext c = SpringApplication.run(MainConfig.class, args);
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(8081);
    }
}
