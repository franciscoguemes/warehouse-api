package com.franciscoguemes.warehouseapi.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ORDER_LINE")
public class OrderLine extends BaseEntity {

	private static final long serialVersionUID = 7197517057894568567L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", nullable=false)
	public @Getter @Setter Product product;
	
	@Column
	private @Getter @Setter Integer quantity;
	
	@Column(name="PRODUCT_PRICE", nullable = true, precision = 50, scale = 30)
	private @Getter @Setter BigDecimal productPrice;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ORDER_ID", nullable=false)
	private @Getter @Setter Order order;
	
	
	public Long getProductId() {
		return this.product.getId();
	}
	
}
