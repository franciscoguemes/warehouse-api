package com.franciscoguemes.warehouseapi.service;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.franciscoguemes.warehouseapi.dto.OrderDto;
import com.franciscoguemes.warehouseapi.dto.OrderLineDto;
import com.franciscoguemes.warehouseapi.error.OrderException;
import com.franciscoguemes.warehouseapi.error.OrderLineException;
import com.franciscoguemes.warehouseapi.model.Order;
import com.franciscoguemes.warehouseapi.model.OrderLine;
import com.franciscoguemes.warehouseapi.repo.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService{

	
	private final OrderRepository orderRepository;
	private final OrderLineService orderLineService;
	
	public OrderServiceImpl(OrderRepository orderRepository, OrderLineService orderLineService) {
		this.orderRepository = orderRepository;
		this.orderLineService = orderLineService;
	}
	
	public OrderDto model2Dto(Order order) {
		
		List<OrderLineDto> linesDto = new LinkedList<>();
		
		Set<OrderLine> lines = order.getLines();
		for(OrderLine line : lines) {
			OrderLineDto lineDto = this.orderLineService.model2Dto(line);
			linesDto.add(lineDto);
		}
		
		return OrderDto.builder()
				.id(order.getId())
				.buyerEmail(order.getBuyerEmail())
				.creationDate(order.getCreationDate())
				.total(order.getTotal())
				.lines(linesDto)
				.build();
	}
	
	public Order dto2Model(OrderDto orderDto) throws OrderLineException {
		Set<OrderLine> lines = new HashSet<>();
		
		Order order = new Order();
		order.setId(orderDto.getId());
		order.setBuyerEmail(orderDto.getBuyerEmail());
		order.setCreationDate(orderDto.getCreationDate());
		
		List<OrderLineDto> linesDto = orderDto.getLines();
		for(OrderLineDto lineDto : linesDto) {
			OrderLine line = orderLineService.dto2Model(lineDto);
			lines.add(line);
			line.setOrder(order);
		}
		
		order.setLines(lines);
		
		return order;
	}
	
	
	@Override
	public Optional<OrderDto> findOrderById(Long id) {
		Optional<Order> optional = orderRepository.findById(id);		
		if(optional.isPresent()) {
			return Optional.of(this.model2Dto(optional.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<OrderDto> findAllOrders() {
		LinkedList<OrderDto> dtos = new LinkedList<>();
		
		List<Order> orders = this.orderRepository.findAll();
		for(Order order : orders) {
			dtos.add(this.model2Dto(order));
		}
		
		return dtos; 
	}
	
	@Override
	@Transactional
	public OrderDto createOrder(OrderDto orderDto) throws OrderLineException, OrderException {
		Order order = this.dto2Model(orderDto);
		
		//Safety of fields...
		order.setId(null);
		if(order.getBuyerEmail()==null) {
			throw new OrderException("It is not possible to create an order without a valid buyerEmail!!!");
		}
		if(order.getCreationDate() ==null) {
			order.setCreationDate(new Date());
		}
		
		Set<OrderLine> lines = order.getLines();
		for(OrderLine line : lines) {
			line.setId(null);
			if(line.getQuantity()==null) {
				throw new OrderLineException("It is not possible to create an OrderLine without Quantity!!!");
			}
			line.setProductPrice(line.getProduct().getPrice());
			//line.setOrder(order);
		}
		
		Order savedOrder = this.orderRepository.save(order);
		
		return this.model2Dto(savedOrder);
	}

	@Override
	public void deleteOrder(Long id) {
		this.orderRepository.deleteById(id);
	}

	@Override
	public OrderDto updateOrder(Long id, OrderDto newOrderDto) {
		
		Optional<Order> optional = this.orderRepository.findById(id);
		Order oldOrder = optional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
	
		if(newOrderDto.getBuyerEmail() != null) {
			oldOrder.setBuyerEmail(newOrderDto.getBuyerEmail());
		}
		
		if(newOrderDto.getCreationDate() != null) {
			oldOrder.setCreationDate(newOrderDto.getCreationDate());
		}
		
		Order savedOrder = this.orderRepository.save(oldOrder);
		
		return this.model2Dto(savedOrder);
	}


	

}
