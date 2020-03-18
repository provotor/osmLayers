package provotor.petprojects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import provotor.petprojects.pbf.OsmosisHelper;
import provotor.petprojects.pbf.data.PbfStore;
import provotor.petprojects.pbf.data.PbfStoreImpl;

import java.io.File;

@Configuration
@SpringBootApplication
@PropertySource("classpath:project.properties")
public class MainConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Value( "${pbfStorePath}" )
    private String PBF_STORE_PATH;

    public static void main(String[] args) {
        ConfigurableApplicationContext c = SpringApplication.run(MainConfig.class, args);
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(8081);
    }

    @Bean
    public OsmosisHelper getOsmosisHelper(){
        return new OsmosisHelper();
    }

    @Bean
    @Lazy // We don't need it in tests
    @DependsOn("osmosisHelper")
    public PbfStore getPbfProvider() {
        return new PbfStoreImpl(getOsmosisHelper(), new File(PBF_STORE_PATH));
    }
}
