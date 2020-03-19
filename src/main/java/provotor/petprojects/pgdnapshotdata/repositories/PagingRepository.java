package provotor.petprojects.pgdnapshotdata.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface PagingRepository<T, ID extends Serializable> extends Repository<T, ID> {
    Page<T> findAll(Pageable pageable);
}
