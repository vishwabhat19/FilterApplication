package com.filter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filter.model.Product;
import com.filter.repository.ProductRepository;

@RestController
public class FilterController {
	
	@Autowired
	ProductRepository productRepository;
	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/product")
	public List<Product> getFilter(@RequestParam(value = "type",required = false) String type) {
		
		Specification<Product> specification = Specification
				.where(type == null ? null : ProductSpecification.productTypeIs(type));
		
		List<Product> products = new ArrayList<Product>();
		
		
		
		products = productRepository.findAll(specification);
		
//		jdbcTemplate.query(
//		        "SELECT product_type, product_properties,price,store_address FROM data WHERE price = ?", new Object[] { 277.00 },
//		        (rs, rowNum) -> new Product(rs.getString("product_type"), rs.getString("product_properties"), rs.getBigDecimal("price"), rs.getString("store_address"))
//		    ).forEach(product -> products.add(product));
		
		
		return products;
	}

}
