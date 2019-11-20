package com.franciscoguemes.warehouseapi.service;

import java.util.List;
import java.util.Optional;

import com.franciscoguemes.warehouseapi.dto.OrderLineDto;
import com.franciscoguemes.warehouseapi.error.OrderLineException;
import com.franciscoguemes.warehouseapi.model.Order;
import com.franciscoguemes.warehouseapi.model.OrderLine;

public interface OrderLineService {

	
	OrderLineDto model2Dto(OrderLine orderLine);
	
	OrderLine dto2Model(OrderLineDto orderLineDto) throws OrderLineException;
	
	List<OrderLineDto> findAllOrderLines();

	Optional<OrderLineDto> findOrderLineById(Long id);
//	
//	OrderLine saveOrderLine(OrderLine orderLine);
//	
//	void deleteOrderLine(Long id);
//
//	OrderLine modifyOrderLine(OrderLine oldOrderLine, OrderLine newOrderLine);

	

}
