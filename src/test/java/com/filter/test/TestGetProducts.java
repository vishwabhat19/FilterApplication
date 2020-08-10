package com.filter.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.filter.controller.FilterController;
import com.filter.model.Data;
import com.filter.service.FilterService;


@SpringBootTest
public class TestGetProducts{
	
	@Autowired
	FilterService service;
	
	@Autowired
	Data data;
	
	@Autowired
	FilterController controller;
	
	
	     

	    
	@Test
	public void getProductTest() {
		
		
		
		Data data = service.getProducts("subscription", "900", null, "Stockholm", null, null, "11", null);
		assertEquals(1,data.getData().size());
		
		
	}
	
	@Test
	public void controllerTest() throws Exception {
		Data data = controller.getFilter(null, null, null, null, null, null, null, null);
		assertEquals(100,data.getData().size());
	}
	
	
	
}
