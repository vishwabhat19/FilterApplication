package com.filter.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity(name = "Product")
public class Product {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="ID")
		private Integer id;		
	 	@Column(name="PRODUCT_TYPE")
	    private String productType;
	 	@Column(name="PRODUCT_PROPERTIES")
	    private String productProperties;
	 	@Column(name="PRICE")
	    private BigDecimal price;
	 	@Column(name="STORE_ADDRESS")
	    private String storeAddress;
	 	
	 	public Product() {
	 		
	 	}
	 	
	 	public Product(String productType, String productProperties, BigDecimal price, String storeAddress) {
	 		
	 	    this.productType = productType;
	 	    this.productProperties = productProperties;
	 	    this.price = price;
	 	    this.storeAddress = storeAddress;
	 	  }

}
