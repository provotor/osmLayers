import base.BaseMockTest;
import base.SpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import provotor.petprojects.pgdnapshotdata.PgSnapshotRepository;
import util.TestPageFromRest;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PgSnapshotTest extends BaseMockTest {
    @Test
    public void get() throws Exception {
        test("/getNodes");
        test("/getWays");
        test("/getPolygons");
    }

    private void test(String uri) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("offset", "0")
                .param("limit", "100")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("", 200, status);
        TestPageFromRest page = super.mapFromJson(mvcResult.getResponse().getContentAsString(),
                TestPageFromRest.class);
        assertTrue("Page is empty: " + uri, page.getTotalElements() > 0);
        assertNotNull("Page content is empty: " + uri, page.getContent().length);
    }
}
