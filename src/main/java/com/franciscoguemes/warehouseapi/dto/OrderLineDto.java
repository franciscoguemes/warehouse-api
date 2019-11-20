package com.franciscoguemes.warehouseapi.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderLineDto {
	
	private Long id;
	private Integer quantity;
	private BigDecimal productPrice;
	private Long productId;
	

}
