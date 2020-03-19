package provotor.petprojects.pgdnapshotdata.repositories;

import org.springframework.data.repository.RepositoryDefinition;
import provotor.petprojects.pgdnapshotdata.Polygon;

@RepositoryDefinition(domainClass = Polygon.class, idClass = Long.class)
public interface PolygonsRepository extends PagingRepository<Polygon, Long> {

}
