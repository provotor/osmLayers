import base.BaseMockTest;
import base.SpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import provotor.petprojects.pbf.PbfInfo;
import provotor.petprojects.pbf.data.PbfStore;

import java.util.List;

public class PbfOperationsTest extends SpringTest {
    @Autowired
    private PbfStore pbfStore;

    @Test
    public void get() {
        List<PbfInfo> pbfInfoList = pbfStore.getPbfList();
        Assert.assertTrue("Store is empty", pbfInfoList.size() > 0);
        Assert.assertEquals("Unknown pbf file name", "suriname.osm.pbf", pbfInfoList.get(0).getName());
    }
}
