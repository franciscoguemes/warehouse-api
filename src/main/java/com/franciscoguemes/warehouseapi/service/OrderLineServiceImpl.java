package com.franciscoguemes.warehouseapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.franciscoguemes.warehouseapi.dto.OrderLineDto;
import com.franciscoguemes.warehouseapi.error.OrderLineException;
import com.franciscoguemes.warehouseapi.model.Order;
import com.franciscoguemes.warehouseapi.model.OrderLine;
import com.franciscoguemes.warehouseapi.model.Product;
import com.franciscoguemes.warehouseapi.repo.OrderLineRepository;
import com.franciscoguemes.warehouseapi.repo.ProductRepository;

@Service
public class OrderLineServiceImpl implements OrderLineService{

	private final OrderLineRepository orderLineRepository;
	private final ProductRepository productRepository;
	
	public OrderLineServiceImpl(OrderLineRepository orderLineRepository, ProductRepository productRepository) {
		this.orderLineRepository = orderLineRepository;
		this.productRepository = productRepository;
	}
	
	@Override
	public OrderLineDto model2Dto(OrderLine orderLine) {
		return OrderLineDto.builder()
		.id(orderLine.getId())
		.quantity(orderLine.getQuantity())
		.productPrice(orderLine.getProductPrice())
		.productId(orderLine.getProductId())
		.build();
	}
	
	@Override
	public OrderLine dto2Model(OrderLineDto dto) throws OrderLineException {
		OrderLine line = new OrderLine();
		line.setId(dto.getId());
		line.setQuantity(dto.getQuantity());
		line.setProductPrice(dto.getProductPrice());
		
		if(dto.getProductId()==null) {
			throw new OrderLineException("It is not possible to create an OrderLine without a Product");
		}
		
		Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
		if(optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			line.setProduct(product);
		}else {
			throw new OrderLineException(String.format("The given productId: %d do not match any existing Product", dto.getId()));
		}
		
		return line;
	}
	
	@Override
	public Optional<OrderLine> findOrderLineById(Long id) {
		return orderLineRepository.findById(id);
	}

	@Override
	public List<OrderLine> findAllOrderLines() {
		return orderLineRepository.findAll();
	}

	@Override
	public OrderLine saveOrderLine(OrderLine orderLine) {
		return orderLineRepository.save(orderLine);
	}

	@Override
	public void deleteOrderLine(Long id) {
		this.orderLineRepository.deleteById(id);
	}

	@Override
	public OrderLine modifyOrderLine(OrderLine oldOrderLine, OrderLine newOrderLine) {
		
		if(newOrderLine.getQuantity()!=null) {
			oldOrderLine.setQuantity(newOrderLine.getQuantity());
		}
		
		//TODO: Do this for the productId ????
		// The productPrice must not be modifiable (is read only) ...
		
		return this.orderLineRepository.save(oldOrderLine);
	}

}
