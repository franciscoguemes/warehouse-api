package com.franciscoguemes.warehouseapi.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.franciscoguemes.warehouseapi.dto.OrderDto;
import com.franciscoguemes.warehouseapi.error.OrderException;
import com.franciscoguemes.warehouseapi.error.OrderLineException;
import com.franciscoguemes.warehouseapi.model.Order;
import com.franciscoguemes.warehouseapi.model.OrderLine;
import com.franciscoguemes.warehouseapi.model.Product;
import com.franciscoguemes.warehouseapi.service.OrderLineService;
import com.franciscoguemes.warehouseapi.service.OrderService;

@RestController
@RequestMapping(OrderController.BASE_URL)
public class OrderController {
	
	public static final String BASE_URL="/api/v1/orders";

	private final OrderService orderService;
	
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
	@GetMapping
	List<OrderDto> getAllOrders(){
		return this.orderService.findAllOrders();
	}
	
	@GetMapping("/{id}")
	public OrderDto getOrderById(@PathVariable Long id) {
		Optional<OrderDto> optional = this.orderService.findOrderById(id);
		return optional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderDto createOrder(@RequestBody OrderDto orderDto) {
		
		try {
			return orderService.createOrder(orderDto);
		} catch (OrderLineException | OrderException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage()); 
		}
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto newOrderDto) {
		
		//TODO: Validate OrderDto for modification...
		
		Optional<OrderDto> optional = this.orderService.findOrderById(id);
		OrderDto oldOrderDto = optional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
		
		OrderDto updatedOrderDto = this.orderService.updateOrder(id, newOrderDto);
		
		return updatedOrderDto;
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable Long id) {
		Optional<OrderDto> optional = this.orderService.findOrderById(id);
		OrderDto orderDto = optional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
		orderService.deleteOrder(orderDto.getId());
		
	}
	
	
}
