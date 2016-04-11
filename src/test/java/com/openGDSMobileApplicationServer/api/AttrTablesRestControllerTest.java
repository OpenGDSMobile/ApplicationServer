package com.openGDSMobileApplicationServer.api;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.openGDSMobileApplicationServer.TestUtil;
import com.openGDSMobileApplicationServer.service.TableService;


//2016. 04. 11.
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/resources/webmapping/spring/context-*.xml",
		"file:src/main/webapp/WEB-INF/config/webmapping/context-*.xml"
})
@WebAppConfiguration
public class AttrTablesRestControllerTest {
	@Mock
	@Qualifier("AttributeTables")
	TableService attrTableService;
	
	@InjectMocks
	AttrTablesRestController attrTableRestController;
	
	String TEST_VALUE = "{\"tableName\" : \"seoul_sig\"}";
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception{

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        
		mockMvc = MockMvcBuilders.standaloneSetup(attrTableRestController)
				.setViewResolvers(viewResolver)
				.build();
		MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAttrSearchTableGet() throws Exception { 		
		when(attrTableService.searchTable(isA(JSONObject.class)))
		.thenReturn(Mockito.anyList());

		mockMvc.perform(get("/api/getAttrTable.do")
				.content(TEST_VALUE)
				.contentType(TestUtil.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAttrSearchTablePost() throws Exception {
		when(attrTableService.searchTable(isA(JSONObject.class)))
		.thenReturn(Mockito.anyList());

		mockMvc.perform(post("/api/getAttrTable.do")
				.content(TEST_VALUE)
				.contentType(TestUtil.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andDo(print());
	}

}
