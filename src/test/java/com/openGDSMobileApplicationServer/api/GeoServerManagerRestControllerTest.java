package com.openGDSMobileApplicationServer.api;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.openGDSMobileApplicationServer.service.GeoServerManagerService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/resources/webmapping/spring/context-*.xml",
		"file:src/main/webapp/WEB-INF/config/webmapping/context-*.xml"
})
@WebAppConfiguration
public class GeoServerManagerRestControllerTest {

	@Mock
	GeoServerManagerService geoServerManagerService;
	
	@InjectMocks
	GeoServerManagerRestController geoServerManagerRestController;

    private MockMvc mockMvc;
	@Before
	public void setup() throws Exception{

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        
		mockMvc = MockMvcBuilders.standaloneSetup(geoServerManagerRestController)
				.setViewResolvers(viewResolver)
				.build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createWorkspaceTest() throws Exception {
		when(geoServerManagerService.createWorkspace(isA(String.class)))
		.thenReturn(true);
		mockMvc.perform(get("/api/createWorkspace.do")
				.param("wsName", "test"))
			.andExpect(status().isOk())
			.andDo(print());		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getLayerNamesTest() throws Exception {
		when(geoServerManagerService.getLayerNames(isA(String.class)))
		.thenReturn(Mockito.anyList());
		
		mockMvc.perform(get("/api/getLayerNames.do")
				.param("wsName", "opengdsMobile"))
			.andExpect(status().isOk())
			.andDo(print());		
	}
}
