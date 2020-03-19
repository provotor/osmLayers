package provotor.petprojects.pgdnapshotdata.repositories;

import org.springframework.data.repository.RepositoryDefinition;
import provotor.petprojects.pgdnapshotdata.Way;

@RepositoryDefinition(domainClass = Way.class, idClass = Long.class)
public interface WaysRepository extends PagingRepository<Way, Long>{

}
