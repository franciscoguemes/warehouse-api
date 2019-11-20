package com.franciscoguemes.warehouseapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.franciscoguemes.warehouseapi.dto.OrderDto;
import com.franciscoguemes.warehouseapi.error.OrderException;
import com.franciscoguemes.warehouseapi.error.OrderLineException;
import com.franciscoguemes.warehouseapi.model.Order;
import com.franciscoguemes.warehouseapi.model.Product;



public interface OrderService {
	
	Optional<OrderDto> findOrderById(Long id);
	
	List<OrderDto> findAllOrders();

	OrderDto createOrder(OrderDto orderDto) throws OrderLineException, OrderException;

	void deleteOrder(Long id);

	OrderDto updateOrder(Long id, OrderDto newOrderDto);
	
}
