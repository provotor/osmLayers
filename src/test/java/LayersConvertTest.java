import base.BaseMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import provotor.petprojects.layers.CreateLayerTask;
import provotor.petprojects.layers.GeometryType;
import provotor.petprojects.layers.LayersConverter;
import provotor.petprojects.layers.rules.RuleIn;
import provotor.petprojects.layers.rules.RulesSet;

public class LayersConvertTest extends BaseMockTest {
    @Autowired
    private LayersConverter layersConverter;

    @Test
    public void convert() {
        CreateLayerTask t = new CreateLayerTask(GeometryType.POINT);
        t.setFieldsList("name", "ele");
        t.add(RulesSet.fromIn(new RuleIn("natural", "peak")));
        t.setName("peaks");
        t.setGeometryType(GeometryType.POINT);

        layersConverter.convert(t);
    }
}
