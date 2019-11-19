package com.franciscoguemes.warehouseapi.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PRODUCT")
public class Product extends BaseEntity{

	private static final long serialVersionUID = -1607178126985066285L;

	@Column(nullable = true, length=255)
	private @Getter @Setter String name;
	
	@Column(nullable = true, length=500)
	private @Getter @Setter String description;
	
	@Column(nullable = true, precision = 50, scale = 30)
	private @Getter @Setter BigDecimal price;
	
	
}
