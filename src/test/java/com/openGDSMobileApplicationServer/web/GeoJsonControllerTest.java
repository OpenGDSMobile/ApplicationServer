package com.openGDSMobileApplicationServer.web;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openGDSMobileApplicationServer.service.GeoJsonService;
import com.openGDSMobileApplicationServer.service.impl.GeoJsonDAO;


@RunWith(MockitoJUnitRunner.class)
public class GeoJsonControllerTest {
/*	
	@Mock
	private GeoJsonDAO dao;
	
	@InjectMocks
	private GeoJsonController controller;
	
	private MockMvc mockMvc;
	*/
	@Mock
	GeoJsonService service;
	@InjectMocks
	private GeoJsonController controller;
	private MockMvc mockMvc;
		
	
	@Before
	public void setup() throws Exception{
	//	this.mockMvc = standaloneSetup(controller).build();
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testGeoJSONLoad() throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("jsonName","SIDO");
		/*this.mockMvc.perform(post("/geojson.do").param("jsonName", "SIDO"))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
        .andExpect(redirectedUrl("/"));*/
		//mockMvc.perform(post("/geojson.do")).andExpect(status().isOk());
	}
/*
	@Test
	public void testWebMappingDemo12() {
		fail("Not yet implemented");
	}

	@Test
	public void testWebMappingDemo13() {
		fail("Not yet implemented");
	}
*/
}
