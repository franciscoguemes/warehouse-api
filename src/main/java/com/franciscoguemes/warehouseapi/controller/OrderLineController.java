package com.franciscoguemes.warehouseapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.franciscoguemes.warehouseapi.dto.OrderLineDto;
import com.franciscoguemes.warehouseapi.service.OrderLineService;

@RestController
@RequestMapping(OrderLineController.BASE_URL)
public class OrderLineController {
	
	public static final String BASE_URL="/api/v1/orderLines";

	private final OrderLineService orderLineService;
	
	
	public OrderLineController(OrderLineService orderLineService) {
		this.orderLineService = orderLineService;
	}
	
	
	@GetMapping
	List<OrderLineDto> getAllOrders(){
		return this.orderLineService.findAllOrderLines();
	}
	
	@GetMapping("/{id}")
	public OrderLineDto getOrderById(@PathVariable Long id) {
		Optional<OrderLineDto> optional = this.orderLineService.findOrderLineById(id);
		return optional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OrderLine not found"));
	}
	
//	@GetMapping("/{id}")
//	public OrderDto getOrderById(@PathVariable Long id) {
//		Optional<OrderDto> optional = this.orderService.findOrderById(id);
//		return optional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
//	}

}
