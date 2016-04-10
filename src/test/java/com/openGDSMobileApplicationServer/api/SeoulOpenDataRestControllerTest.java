package com.openGDSMobileApplicationServer.api;

import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SeoulOpenDataRestControllerTest {
	//************************String str****************************************//

	@Mock
	@Qualifier("Seoul")
	PublicDataService seoulOpenDataObj;
	
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
	public void testGetSeoulOpenData() throws Exception {
		when(seoulOpenDataObj.requestPublicData(isA(JSONObject.class)))
		.thenReturn(new String());
		/*
		mockMvc.perform(post("/api/SeoulOpenData.do", "{}")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.param("jsonName", "SIDO"))
			.andExpect(status().isOk())
			.andDo(print());*/
	}
}
