import base.SpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import provotor.petprojects.pbf.*;
import provotor.petprojects.pbf.data.PbfStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class PbfOperationsTest extends SpringTest {
    @Autowired
    private PbfStore pbfStore;

    @Autowired
    private OsmosisHelper osmosisHelper;

    @Test
    public void get() {
        List<PbfInfo> pbfInfoList = pbfStore.getPbfList();
        Assert.assertTrue("Store is empty", pbfInfoList.size() > 0);
        Assert.assertEquals("Unknown pbf file name", "suriname.osm.pbf", pbfInfoList.get(0).getName());
    }

    @Test
    public void cut(){
        PbfInfo pbfInfo = pbfStore.getPbfList().get(0);
        PbfCutTask cmd = new PbfCutTask();
        cmd.setPbfSourceId(pbfInfo.getId());
        cmd.setNewPbfName("test_cutted");
        cmd.setBound(new Bound(-55.0, -51.0, 2.0, 3.0));

        File f = null;
        try {
            f = osmosisHelper.cutPbfToPbf(cmd);
        } catch (FileNotFoundException e) {
            Assert.fail("Source PBF file not found");
        }
        pbfStore.refresh();
        PbfInfo pbfInfoNew = pbfStore.getPbfInfoByPbf(f);
        Assert.assertNotNull(pbfInfoNew);
    }

    @Test
    public void toDatabase() {
        PbfInfo pbfInfo = pbfStore.getPbfList().get(0);
        PbfToDatabaseTask cmd = new PbfToDatabaseTask();
        cmd.setPbfId(pbfInfo.getId());
        cmd.setBound(new Bound(-55.0, -51.0, 2.0, 3.0));

        osmosisHelper.pbfToDatabase(cmd);
    }
}
