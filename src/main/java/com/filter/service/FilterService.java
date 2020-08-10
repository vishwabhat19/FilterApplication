package com.filter.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.filter.controller.ProductSpecification;
import com.filter.model.Data;
import com.filter.model.Product;
import com.filter.model.ProductResponse;
import com.filter.repository.ProductRepository;

@Service
public class FilterService {
	
	@Autowired
	ProductRepository productRepository;
	
	
	
	@Autowired
	Data data;
	
	public Data getProducts(String type,String minPrice,String maxPrice,String city,String propertyType,String color,String gbLimitMin,String gbLimitMax) {
		
		
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
		List<ProductResponse> productResponses = new ArrayList<ProductResponse>();
		
		
		
		products = productRepository.findAll(specificationForProductType
				.and(specificationForMinPrice)
				.and(specificationForMaxPrice)
				.and(specificationForCity)
				.and(specificationForProductPropertyType)
				.and(specificationForProductColor));
		/*
		 * Doing this since the expected output does not expect the id key value.
		 */
		
		if(null != products && !(products.isEmpty())) {
			for(Product product: products) {
				ProductResponse productResponse = new ProductResponse();
				productResponse.setPrice(product.getPrice());
				productResponse.setProperties(product.getProductProperties());
				productResponse.setStore_address(product.getStoreAddress());
				productResponse.setType(product.getProductType());
				productResponses.add(productResponse);
			}
			data.setData(productResponses);
		}
		/*
		 * At this point the products have been filtered to some extent and only the following filters remain:
		 * property:color			The color of the phone. (String)
		   property:gb_limit_min 	The minimum GB limit of the subscription. (Number)
		   property:gb_limit_max 	The maximum GB limit of the subscription. (Number)
		 */
		
		List<Product> finalProducts = new ArrayList<Product>();
		if(gbLimitMin==null && gbLimitMax == null) {
			return data;
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
		
		/*
		 * Doing this since the expected output does not expect the id key value.
		 */
		if(null != finalProducts && !(finalProducts.isEmpty())) {
			productResponses.clear();
			
			for(Product product: finalProducts) {
				ProductResponse productResponse = new ProductResponse();
				productResponse.setPrice(product.getPrice());
				productResponse.setProperties(product.getProductProperties());
				productResponse.setStore_address(product.getStoreAddress());
				productResponse.setType(product.getProductType());
				productResponses.add(productResponse);
			}
			data.setData(productResponses);
		}

		
		
		return data;
	}

}
