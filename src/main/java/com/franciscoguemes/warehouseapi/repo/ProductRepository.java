package com.franciscoguemes.warehouseapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.franciscoguemes.warehouseapi.model.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

}
