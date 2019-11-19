package com.franciscoguemes.warehouseapi.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.franciscoguemes.warehouseapi.model.Product;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {

}
