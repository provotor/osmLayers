package base;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import provotor.petprojects.MainConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainConfig.class)
@WebAppConfiguration
@ActiveProfiles(MainConfig.UNIT_TESTS_PROFILE)
public abstract class SpringTest {
    @Autowired
    protected WebApplicationContext webApplicationContext;
}
