package com.openGDSMobileApplicationServer.api;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
 
import com.openGDSMobileApplicationServer.service.PublicDataService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/resources/webmapping/spring/context-*.xml",
		"file:src/main/webapp/WEB-INF/config/webmapping/context-*.xml"
})
@WebAppConfiguration
public class PublicDataRestControllerTest {
	@Mock
	@Qualifier("Portal")
	PublicDataService publicDataPortalObj; 
	@Mock
	@Qualifier("Seoul")
	PublicDataService seoulOpenDataObj; 

	@InjectMocks
	PublicDataRestController publicDataRestController;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf-8"));
	
	
    private MockMvc mockMvc;

    String TEST_VALUE;
	@Before
	public void setup() throws Exception{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

		mockMvc = MockMvcBuilders.standaloneSetup(publicDataRestController)
				.setViewResolvers(viewResolver)
				.build();
		MockitoAnnotations.initMocks(this);
	}
	
	//2016. 04. 11.
	@Test
	public void testGetSeoulOpenData() throws Exception {
		when(seoulOpenDataObj.requestPublicData(isA(JSONObject.class))).thenReturn(new JSONObject());
		
		TEST_VALUE = "{\"serviceKey\":\"6473565a72696e7438326262524174\","+
					  "\"returnType\":\"json\",\"serviceName\":\"TimeAverageAirQuality\","+
					  "\"amount\":\"1/100\",\"dateTimeValue\":\"201601010100\",\"envType\":\"PM10\"}";
		mockMvc.perform(post("/api/SeoulOpenData.do")
				.contentType(APPLICATION_JSON_UTF8) 
				.content(TEST_VALUE))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	//2016. 04. 11.
	@Test
	public void testGetPublicDataPortal() throws Exception {
		when(publicDataPortalObj.requestPublicData(isA(JSONObject.class))).thenReturn(new JSONObject());
		
		TEST_VALUE = "{\"serviceName\":\"NuclearPowerPlantRealtimeLevelofRadiation\","+
					  "\"serviceKey\":\"kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D\","+
					  "\"startDate\":\"YK\"}";
		mockMvc.perform(post("/api/PublicDataPortal.do")
				.contentType(APPLICATION_JSON_UTF8) 
				.content(TEST_VALUE))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
