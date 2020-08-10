package com.filter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filter.model.Data;
import com.filter.service.FilterService;

@RestController
public class FilterController {
	

	
	@Autowired
	FilterService service;
	
	@Autowired
	Data data;
	

	
	@GetMapping("/product")
	public Data getFilter(@RequestParam(value = "type",required = false) String type,
			@RequestParam(value = "min_price",required = false) String minPrice,
			@RequestParam(value = "max_price",required = false) String maxPrice,
			@RequestParam(value = "city",required = false) String city,
			@RequestParam(value = "property",required = false) String propertyType,
			@RequestParam(value = "property.color",required = false) String color,
			@RequestParam(value = "property.gb_limit_min",required = false) String gbLimitMin,
			@RequestParam(value = "property.gb_limit_max",required = false) String gbLimitMax) {
		
		//data = service.getProducts("subscription", "900", null, "Stockholm", null, null, "11", null);
		data = service.getProducts(type,minPrice,maxPrice,city,propertyType,color,gbLimitMin,gbLimitMax);
		
		return data;
		
	}

}
