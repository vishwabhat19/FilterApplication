package com.filter.controller;

import java.text.MessageFormat;

import org.springframework.data.jpa.domain.Specification;

import com.filter.model.Product;

public  final class ProductSpecification {
	

	
	public static Specification<Product>  productTypeIs(String expression){
		return (root,query,builder) -> builder.like(root.get("productType"),contains(expression));
	}
	
	public static Specification<Product>  minPrice(String expression){
		return (root,query,builder) -> builder.greaterThanOrEqualTo(root.get("price"),expression);
	}
	
	public static Specification<Product>  maxPrice(String expression){
		return (root,query,builder) -> builder.lessThanOrEqualTo(root.get("price"),expression);
	}
	
	public static Specification<Product>  cityIs(String expression){
		return (root,query,builder) -> builder.like(root.get("storeAddress"),"%" + expression + "%");
	}
	
	public static Specification<Product>  productPropertyTypeIs(String expression){
		return (root,query,builder) -> builder.like(root.get("productProperties"),"%" + expression + "%");
	}
	
	public static Specification<Product>  productColorIs(String expression){
		return (root,query,builder) -> builder.like(root.get("productProperties"),"%" + expression + "%");
	}
	
	
	
	
	private static String contains(String expression) {
	    return MessageFormat.format("%{0}%", expression);
	}

	

}
