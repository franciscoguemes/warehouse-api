package com.franciscoguemes.warehouseapi.repo;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface FindersRepository<T, I> extends Repository<T, I> {
    Collection<T> findAll();
    Optional<T> findById(I id);
    
    default T findOne(I id) {
        return findById(id).orElse(null);
    }    
}
