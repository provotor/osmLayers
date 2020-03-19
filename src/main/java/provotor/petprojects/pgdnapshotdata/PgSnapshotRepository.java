package provotor.petprojects.pgdnapshotdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import provotor.petprojects.pgdnapshotdata.repositories.NodesRepository;
import provotor.petprojects.pgdnapshotdata.repositories.PolygonsRepository;
import provotor.petprojects.pgdnapshotdata.repositories.WaysRepository;

import javax.persistence.EntityManagerFactory;

@Component
public class PgSnapshotRepository {
    private final NodesRepository nodesRepository;
    private final WaysRepository waysRepository;
    private final PolygonsRepository polygonsRepository;

    public PgSnapshotRepository(EntityManagerFactory emf) {
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(emf.createEntityManager());
        nodesRepository = jpaRepositoryFactory.getRepository(NodesRepository.class);
        waysRepository = jpaRepositoryFactory.getRepository(WaysRepository.class);
        polygonsRepository = jpaRepositoryFactory.getRepository(PolygonsRepository.class);
    }

    public Page<Way> getWays(Integer offset, Integer limit) {
        return waysRepository.findAll(PageRequest.of(offset, limit));
    }

    public Page<Node> getNodes(Integer offset, Integer limit) {
        return nodesRepository.findAll(PageRequest.of(offset, limit));
    }

    public Page<Polygon> getPolygons(Integer offset, Integer limit) {
        return polygonsRepository.findAll(PageRequest.of(offset, limit));
    }
}
