package com.openGDSMobileApplicationServer.api;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import static org.mockito.Matchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import com.openGDSMobileApplicationServer.TestUtil;
import com.openGDSMobileApplicationServer.service.GeoJsonService;
import com.openGDSMobileApplicationServer.valueObject.GeoJsonVO;



@RunWith(MockitoJUnitRunner.class)
/*@RunWith(SpringJUnit4ClassRunner.class)*/

@ContextConfiguration(locations = {
		"file:src/main/resources/webmapping/spring/context-*.xml",
		"file:src/main/webapp/WEB-INF/config/webmapping/context-*.xml"
})
@WebAppConfiguration
public class GeoJsonRestControllerTest {

	@Mock
	GeoJsonService geoJsonService;
	
	@InjectMocks
	GeoJsonRestController geoJsonRestController;
	
    private MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception{

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        
		mockMvc = MockMvcBuilders.standaloneSetup(geoJsonRestController)
				.setViewResolvers(viewResolver)
				.build();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetGeoJson() throws Exception {
		GeoJsonVO vo = new GeoJsonVO("SIDO");
		JSONObject JSONData = new JSONObject(vo);
		// execute test
		//verify(geoJsonService, never()).getLocation(JSONData);
/*
		mockMvc.perform(get("/api/getGeoJson.do")
				.param("jsonName", "SIDO"))
			.andExpect(status().isOk())
			.andDo(print());
		*/
		when(geoJsonService.getLocation(isA(JSONObject.class))).thenReturn(new JSONObject());
		
		mockMvc.perform(post("/api/getGeoJson.do")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.param("jsonName", "SIDO"))
			.andExpect(status().isOk())/*
			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))*/
			.andDo(print());
	}
}
