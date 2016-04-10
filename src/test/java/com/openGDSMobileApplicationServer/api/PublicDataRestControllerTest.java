package com.openGDSMobileApplicationServer.api;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.openGDSMobileApplicationServer.TestUtil;
import com.openGDSMobileApplicationServer.service.PublicDataService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/resources/webmapping/spring/context-*.xml",
		"file:src/main/webapp/WEB-INF/config/webmapping/context-*.xml"
})
@WebAppConfiguration
public class PublicDataRestControllerTest {
	//************************String str****************************************//
	@Mock
	@Qualifier("Portal")
	PublicDataService publicDataPortalObj; 

	@InjectMocks
	PublicDataRestController publicDataRestController;
	
    private MockMvc mockMvc;

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
	@Test
	public void testGetPublicDataPortal() throws Exception {
		when(publicDataPortalObj.requestPublicData(isA(JSONObject.class))).thenReturn(new JSONObject());
	/*	String ttt = "{\"serviceName\":\"NuclearPowerPlantRealtimeLevelofRadiation\",\"serviceKey\":\"kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D\",\"startDate\":\"YK\"}";
		mockMvc.perform(post("/api/PublicDataPortal.do")
				.contentType(TestUtil.APPLICATION_JSON_UTF8) 
				.param("2222", "23333"))
			.andExpect(status().isOk())
			.andDo(print());*/
	}
}
