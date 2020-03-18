package base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import provotor.petprojects.MainConfig;
import provotor.petprojects.pbf.data.PbfStore;
import provotor.petprojects.pbf.data.PbfStoreImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Special {@link PbfStore} for tests.
 * Replaces default implementation with special provider for unit tests
 */
@Configuration
@Import(MainConfig.class)
public class TestConfiguration {
    /**
     * We create test directory and add test file.
     * @return
     * @throws IOException
     */
    @Bean
    @Primary
    public PbfStore getPbfProviderForTest() throws IOException {
        File testSource = new File(getClass().getClassLoader().getResource("suriname.osm.pbf").getFile());
        File storeFolder = null;
        try {
            storeFolder = Files.createTempDirectory(this.getClass().getName()).toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Files.copy(testSource.toPath(), new File(storeFolder, testSource.getName()).toPath());
        return new PbfStoreImpl(storeFolder);
    }
}
