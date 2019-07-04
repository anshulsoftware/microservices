package com.moody.moon.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.moody.moon.model.FilterBean;

@RestController
public class FilterController {

	@GetMapping(path ="/filtering")
	public MappingJacksonValue filtering() {
		FilterBean  filterBean= new FilterBean("value1","value2","value3");
		
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider filters= new SimpleFilterProvider().addFilter("filterBean", filter);
		 MappingJacksonValue mapping = new MappingJacksonValue(filterBean); 
		 mapping.setFilters(filters);
		return mapping;
	}

		 
	@GetMapping(path ="/list-filtering")
	public MappingJacksonValue filterOnList() {
		
		List<FilterBean> list =Arrays.asList(new FilterBean("value1","value2","value3"),new FilterBean("value4","value5","value6"));
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider filters= new SimpleFilterProvider().addFilter("filterBean", filter);
		 MappingJacksonValue mapping = new MappingJacksonValue(list); 
		 mapping.setFilters(filters);
		
		return  mapping;
	}
}
