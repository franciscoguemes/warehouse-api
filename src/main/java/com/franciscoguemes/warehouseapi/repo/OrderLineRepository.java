package com.franciscoguemes.warehouseapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.franciscoguemes.warehouseapi.model.OrderLine;

@RepositoryRestResource
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
