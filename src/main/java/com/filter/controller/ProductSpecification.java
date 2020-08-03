package com.filter.controller;

import java.text.MessageFormat;

import org.springframework.data.jpa.domain.Specification;

import com.filter.model.Product;

public  final class ProductSpecification {
	
	public static Specification<Product>  productTypeIs(String expression){
		return (root,query,builder) -> builder.like(root.get("productType"),contains(expression));
	}
	
	private static String contains(String expression) {
	    return MessageFormat.format("%{0}%", expression);
	}

}
