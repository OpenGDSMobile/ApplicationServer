package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openGDSMobileApplicationServer.service.TableService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/config/webmapping/context-servlet.xml",
		"file:src/main/resources/webmapping/spring/context-*.xml"
})
public class RealtimeInfoTableServiceImpTest {

	@Autowired
	@Qualifier("realtimeTable")
	TableService tableService;
	
	
	@Test
	public void testSearchTable() {

		JSONObject type = new JSONObject("{\"subject\":\"seoul_sig\",\"userid\":\"user\",\"session\":\"testSession\"}");
		tableService.insertData(type);
		// column : subject
		type = null;
		type = new JSONObject("{\"column\":\"SUBJECT\"}");		
		assertNotNull(tableService.searchTable(type));
		// column : userId
		type = null;
		type = new JSONObject("{\"column\":\"USER_ID\",\"userid\":\"user\"}");
		assertNotNull(tableService.searchTable(type));
		// column : all
		type = null;
		type = new JSONObject("{\"column\":\"ALL\"}");		
		assertNotNull(tableService.searchTable(type));
		type = null;
		type = new JSONObject("{\"session\":\"testSession\"}");
		tableService.deleteData(type);
	}

	@Test
	public void testSearchTableCount() {
		// session : testSessionID
		// count result 1

		JSONObject type = new JSONObject("{\"subject\":\"seoul_sig\",\"userid\":\"user\",\"session\":\"testSession\"}");
		tableService.insertData(type);
		type = null;
		type = new JSONObject("{\"session\":\"testSession\"}");
		assertEquals(tableService.searchTableCount(type), "1");
		
		tableService.deleteData(type);
	
	}

	@Test
	public void testInsertData() {
		// session : testSessionID
		// count result 1		
		JSONObject type = new JSONObject("{\"subject\":\"seoul_sig\",\"userid\":\"user\",\"session\":\"testSession\"}");
		assertEquals(tableService.insertData(type), 1);
		tableService.deleteData(type);
	}

	@Test
	public void testDeleteData() {
	
		JSONObject type = new JSONObject("{\"subject\":\"seoul_sig\",\"userid\":\"user\",\"session\":\"testSession\"}");
		tableService.insertData(type);
		type = null;
		type = new JSONObject("{\"session\":\"testSession\"}");
		assertEquals(tableService.deleteData(type), 1);
		
	}

	@Test
	public void testSearchTableWhere() {
		
		JSONObject type = new JSONObject("{\"subject\":\"seoul_sig\",\"userid\":\"user\",\"session\":\"testSession\"}");
		tableService.insertData(type);
		type = new JSONObject("{\"subject\":\"seoul_sig\",\"userdd\":\"user1\",\"session\":\"testSession1\"}");
		tableService.insertData(type);
		type = null;
		type = new JSONObject("{\"subject\":\"seoul_sig\"}");
		assertNotNull(tableService.searchTableWhere(type));

		type = new JSONObject("{\"session\":\"testSession\"}");
		tableService.deleteData(type);
		type = new JSONObject("{\"session\":\"testSession1\"}");
		tableService.deleteData(type);
		
	}

	@Test
	public void testUpdateTable() {
		JSONArray arr = new JSONArray("[{\"tableName\":\"seoul_sig\",\"column\":\"sig_kor_nm\","+
				"\"srcData\":\"성동구\",\"dstData\":\"성동구TEST\"}]");
		assertEquals(tableService.updateTable(arr), 1);
		arr = null;
				arr = new JSONArray("[{\"tableName\":\"seoul_sig\",\"column\":\"sig_kor_nm\","+
										"\"srcData\":\"성동구TEST\",\"dstData\":\"성동구\"}]");
		assertEquals(tableService.updateTable(arr), 1);
		
	}

}
