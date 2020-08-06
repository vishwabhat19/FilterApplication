package com.filter.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filter.model.Product;
import com.filter.model.ProductResponse;
import com.filter.repository.ProductRepository;

@RestController
public class FilterController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductResponse productReponse;
	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/product")
	public List<Product> getFilter(@RequestParam(value = "type",required = false) String type,
			@RequestParam(value = "min_price",required = false) String minPrice,
			@RequestParam(value = "max_price",required = false) String maxPrice,
			@RequestParam(value = "city",required = false) String city,
			@RequestParam(value = "property",required = false) String propertyType,
			@RequestParam(value = "property.color",required = false) String color,
			@RequestParam(value = "property.gb_limit_min",required = false) String gbLimitMin,
			@RequestParam(value = "property.gb_limit_max",required = false) String gbLimitMax) {
		
		Integer gbLimitMinInt = Integer.MIN_VALUE;
		Integer gbLimitMaxInt = Integer.MAX_VALUE;
		
		Specification<Product> specificationForProductType = Specification
				.where(type == null ? null : ProductSpecification.productTypeIs(type));
		
		Specification<Product> 	specificationForMinPrice = Specification
				.where(minPrice == null ? null : ProductSpecification.minPrice(minPrice));
		
		Specification<Product> specificationForMaxPrice = Specification
				.where(maxPrice == null ? null : ProductSpecification.maxPrice(maxPrice));
		Specification<Product> specificationForCity = Specification
				.where(city == null ? null : ProductSpecification.cityIs(city));
		Specification<Product> specificationForProductPropertyType = Specification
				.where(propertyType == null ? null : ProductSpecification.productPropertyTypeIs(propertyType));
		Specification<Product> specificationForProductColor = Specification
				.where(color == null ? null : ProductSpecification.productColorIs(color));
//		Specification<Product> specificationForMinGBLimit = Specification
//				.where(color == null ? null : ProductSpecification.productMinGBLimitIs(gbLimitMin));
//		
		
		
		List<Product> products = new ArrayList<Product>();
		
		
		
		products = productRepository.findAll(specificationForProductType
				.and(specificationForMinPrice)
				.and(specificationForMaxPrice)
				.and(specificationForCity)
				.and(specificationForProductPropertyType)
				.and(specificationForProductColor));
		
		/*
		 * At this point the products have been filtered to some extent and only the following filters remain:
		 * property:color			The color of the phone. (String)
		   property:gb_limit_min 	The minimum GB limit of the subscription. (Number)
		   property:gb_limit_max 	The maximum GB limit of the subscription. (Number)
		 */
		
		List<Product> finalProducts = new ArrayList<Product>();
		if(gbLimitMin==null && gbLimitMax == null) {
			return products;
		}
		
		
		
		
		else {
			if(gbLimitMax!=null) gbLimitMaxInt = Integer.valueOf(gbLimitMax);
			if(gbLimitMin!=null) gbLimitMinInt = Integer.valueOf(gbLimitMin);
			for(Product product: products) {
				if(product.getProductProperties().contains("gb_limit")) {
					JSONObject jsonObject = new JSONObject(product.getProductProperties());
					Integer gbLimit = jsonObject.getInt("gb_limit");
					if(gbLimit >= gbLimitMinInt && gbLimit <= gbLimitMaxInt) {
						finalProducts.add(product);
					}
				}
				
			}
			
		}
		

		
		
		return finalProducts;
	}

}
