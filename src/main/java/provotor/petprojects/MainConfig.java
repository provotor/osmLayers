package provotor.petprojects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ResourceLoader;
import provotor.petprojects.pbf.OsmosisHelper;
import provotor.petprojects.pbf.data.PbfStore;
import provotor.petprojects.pbf.data.PbfStoreImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
@SpringBootApplication
@PropertySource("classpath:project.properties")
public class MainConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    public static final String UNIT_TESTS_PROFILE = "unitTests";

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
    @Profile("default")
    public PbfStore getPbfProvider(OsmosisHelper osmosisHelper) {
        return new PbfStoreImpl(osmosisHelper, new File(PBF_STORE_PATH));
    }

    /**
     * We create test directory and add test file.
     * @return
     * @throws IOException
     */
    @Bean
    @Profile(UNIT_TESTS_PROFILE)
    public PbfStore getPbfProviderForTest(OsmosisHelper osmosisHelper, ResourceLoader resourceLoader) throws IOException {
        File testSource = resourceLoader.getResource("classpath:suriname.osm.pbf").getFile();
        File storeFolder = null;
        try {
            storeFolder = Files.createTempDirectory(this.getClass().getName()).toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Files.copy(testSource.toPath(), new File(storeFolder, testSource.getName()).toPath());
        return new PbfStoreImpl(osmosisHelper, storeFolder);
    }

}
