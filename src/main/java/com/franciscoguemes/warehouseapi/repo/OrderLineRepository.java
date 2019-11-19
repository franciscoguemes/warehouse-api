package com.franciscoguemes.warehouseapi.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.franciscoguemes.warehouseapi.model.OrderLine;

@RepositoryRestResource
public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {

}
