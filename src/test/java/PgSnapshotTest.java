import base.SpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import provotor.petprojects.pgdnapshotdata.PgSnapshotRepository;

public class PgSnapshotTest extends SpringTest {
    @Autowired
    private PgSnapshotRepository repository;

    @Test
    public void getNode() {
        Assert.assertTrue(repository.getNodes(0, 100).getContent().size() > 0);
    }

    @Test
    public void getWays() {
        Assert.assertTrue(repository.getWays(0, 100).getContent().size() > 0);
    }

    @Test
    public void getPolygons() {
        Assert.assertTrue(repository.getPolygons(0, 100).getContent().size() > 0);
    }
}
