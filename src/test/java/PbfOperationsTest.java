import base.BaseMockTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import provotor.petprojects.pbf.Bound;
import provotor.petprojects.pbf.PbfCutTask;
import provotor.petprojects.pbf.PbfInfo;
import provotor.petprojects.pbf.PbfToDatabaseTask;
import provotor.petprojects.pbf.data.PbfStore;

import static junit.framework.TestCase.assertTrue;

public class PbfOperationsTest extends BaseMockTest {
    @Autowired
    private PbfStore pbfStore;

    @Test
    public void get() throws Exception {
        PbfInfo[] pbfInfoArray = getRequest("pbfList", PbfInfo[].class);
        assertTrue(pbfInfoArray.length > 0);
    }

    @Test
    public void cut() throws Exception {
        PbfInfo pbfInfo = pbfStore.getPbfList().get(0);
        PbfCutTask cmd = new PbfCutTask();
        cmd.setPbfSourceId(pbfInfo.getId());
        cmd.setNewPbfName("test_cutted");
        cmd.setBound(new Bound(-55.0, -51.0, 2.0, 3.0));

        PbfInfo newPbfInfo = postRequest("cutPbf", PbfInfo.class, cmd);
        Assert.assertEquals(cmd.getNewPbfName() + PbfStore.OSM_PBF_EXTENSION, newPbfInfo.getName());
    }

    @Test
    public void toDatabase() throws Exception {
        PbfInfo pbfInfo = pbfStore.getPbfList().get(0);
        PbfToDatabaseTask cmd = new PbfToDatabaseTask();
        cmd.setPbfId(pbfInfo.getId());
        cmd.setBound(new Bound(-55.0, -51.0, 2.0, 3.0));

        postRequest("pbfToDatabase", null, cmd);
    }
}
