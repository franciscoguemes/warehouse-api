package com.franciscoguemes.warehouseapi.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.franciscoguemes.warehouseapi.model.Order;
import com.franciscoguemes.warehouseapi.model.OrderLine;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {
	private Long id;
	private String buyerEmail;
	private Date creationDate;
	private BigDecimal total;
	private List<OrderLineDto> lines;
	
}
