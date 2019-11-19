package com.franciscoguemes.warehouseapi.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="`ORDER`")
public class Order extends BaseEntity{

	private static final long serialVersionUID = 1388758958415154932L;

	@OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}) 
    private @Getter Set<OrderLine> lines;
	
	@Column(name="BUYER_EMAIL", nullable = true, length=255)
	private @Getter @Setter String buyerEmail;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATION_DATE", nullable = true)
    private @Getter @Setter Date creationDate;

}
