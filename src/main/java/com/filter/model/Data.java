package com.filter.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@lombok.Data
@Getter
@Setter
@Component
public class Data {
	
	List<ProductResponse> Data;
	

	public Data() {
 		
 	}
 	
 	
 	
 	public void addProductResponse(List<ProductResponse> responses) {
 		this.Data = responses;
 	}
}
