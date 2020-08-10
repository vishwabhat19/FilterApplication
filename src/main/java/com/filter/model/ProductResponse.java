package com.filter.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Data
@Getter
@Setter
@Component
public class ProductResponse {
	
		
 	
    private String type;
 	
    private String properties;
 	
    private BigDecimal price;
 	
    private String store_address;
 	
 	public ProductResponse() {
 		
 	}
 	
 	

}
