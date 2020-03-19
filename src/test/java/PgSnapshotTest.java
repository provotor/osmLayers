import base.SpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import provotor.petprojects.pgdnapshotdata.PgSnapshotRepository;

public class PgSnapshotTest extends SpringTest {
    @Autowired
    private PgSnapshotRepository repository;

    @Test
    public void getNode() throws Exception {
        Assert.assertTrue(repository.getNodes(0, 100).getContent().size() > 0);
    }

    @Test
    public void getWays() throws Exception {
        Assert.assertTrue(repository.getWays(0, 100).getContent().size() > 0);
    }

    @Test
    public void getPolygons() throws Exception {
        Assert.assertTrue(repository.getPolygons(0, 100).getContent().size() > 0);
    }
}
