package provotor.petprojects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import provotor.petprojects.layers.CreateLayerTask;
import provotor.petprojects.layers.LayersConverter;
import provotor.petprojects.pbf.OsmosisHelper;
import provotor.petprojects.pbf.PbfCutTask;
import provotor.petprojects.pbf.PbfInfo;
import provotor.petprojects.pbf.PbfToDatabaseTask;
import provotor.petprojects.pbf.data.PbfStore;
import provotor.petprojects.pgdnapshotdata.Node;
import provotor.petprojects.pgdnapshotdata.PgSnapshotRepository;
import provotor.petprojects.pgdnapshotdata.Polygon;
import provotor.petprojects.pgdnapshotdata.Way;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class MainRestController {
    private final PbfStore pbfStore;
    private final OsmosisHelper osmosisHelper;
    private final PgSnapshotRepository pgSnapshotRepository;
    private final LayersConverter layersConverter;

    public MainRestController(PbfStore pbfStore,
                              OsmosisHelper osmosisHelper,
                              PgSnapshotRepository pgSnapshotRepository,
                              LayersConverter layersConverter
    ) {
        this.pbfStore = pbfStore;
        this.osmosisHelper = osmosisHelper;
        this.pgSnapshotRepository = pgSnapshotRepository;
        this.layersConverter = layersConverter;
    }

    @RequestMapping("/pbfList")
    List<PbfInfo> getPbfFiles() {
        return pbfStore.getPbfList();
    }

    @RequestMapping(value = "/cutPbf", method = RequestMethod.POST)
    PbfInfo cutPbf(@RequestBody PbfCutTask pbfCutTask) throws FileNotFoundException {
        File result = osmosisHelper.cutPbfToPbf(pbfCutTask);
        pbfStore.refresh();
        return pbfStore.getPbfInfoByPbf(result);
    }

    @RequestMapping(value = "/pbfToDatabase", method = RequestMethod.POST)
    void pbfToDatabase(@RequestBody PbfToDatabaseTask pbfToDatabaseTask) {
        osmosisHelper.pbfToDatabase(pbfToDatabaseTask);
    }

    @RequestMapping(value = "/getWays")
    Page<Way> getWays(@RequestParam Integer offset, @RequestParam Integer limit) {
        return pgSnapshotRepository.getWays(offset, limit);
    }

    @RequestMapping(value = "/getNodes")
    @ResponseBody
    Page<Node> getNodes(@RequestParam Integer offset, @RequestParam Integer limit) {
        return pgSnapshotRepository.getNodes(offset, limit);
    }

    @RequestMapping(value = "/getPolygons")
    Page<Polygon> getPolygons(@RequestParam Integer offset, @RequestParam Integer limit) {
        return pgSnapshotRepository.getPolygons(offset, limit);
    }

    @RequestMapping(value = "/createLayer", method = RequestMethod.POST)
    void createLayer(@RequestBody CreateLayerTask task) {
        layersConverter.convert(task);
    }
}
