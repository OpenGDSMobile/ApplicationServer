package com.openGDSMobileApplicationServer.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.openGDSMobileApplicationServer.service.GeoJsonService;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.json.JSONObject;
@RunWith(MockitoJUnitRunner.class)
/*@RunWith(SpringJUnit4ClassRunner.class)*/

@ContextConfiguration(locations = {
		"file:src/main/resources/webmapping/spring/context-*.xml",
		"file:src/main/webapp/WEB-INF/config/webmapping/context-*.xml"
})
@WebAppConfiguration
public class GeoJsonControllerTest {
	
	@Mock
	GeoJsonService geoJsonService;
	
	@InjectMocks
	GeoJsonController geoJsonController;
	
    private MockMvc mockMvc;

	
	@Before
	public void setup() throws Exception{

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");
        
		mockMvc = MockMvcBuilders.standaloneSetup(geoJsonController)
				.setViewResolvers(viewResolver).build();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGeoJSONLoad() throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("jsonName","SIDO");
		// execute test
		verify(geoJsonService, never()).getLocation(obj); 
				
		mockMvc.perform(post("/geojson.do")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("jsonName").value("SIDO"))
			.andDo(print());
	}
	/*
	@Test(expected = Exception.class)
	public void whenNotGeoJSONLoad() {
		
	}*/
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
