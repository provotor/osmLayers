package provotor.petprojects.pgdnapshotdata.repositories;

import org.springframework.data.repository.RepositoryDefinition;
import provotor.petprojects.pgdnapshotdata.Node;

@RepositoryDefinition(domainClass = Node.class, idClass = Long.class)
public interface NodesRepository extends PagingRepository<Node, Long>{

}
