import base.BaseMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import provotor.petprojects.layers.CreateLayerTask;
import provotor.petprojects.layers.GeometryType;
import provotor.petprojects.layers.LayersConverter;
import provotor.petprojects.layers.rules.RuleIn;
import provotor.petprojects.layers.rules.RulesSet;

import static org.junit.Assert.assertNotNull;

public class LayersConvertTest extends BaseMockTest {
    @Test
    public void convert() throws Exception {
        CreateLayerTask t = new CreateLayerTask(GeometryType.POINT);
        t.setFieldsList("name", "ele");
        t.add(RulesSet.fromIn(new RuleIn("natural", "peak")));
        t.setName("peaks");
        t.setGeometryType(GeometryType.POINT);

        postRequest("createLayer", null, t);
    }
}
