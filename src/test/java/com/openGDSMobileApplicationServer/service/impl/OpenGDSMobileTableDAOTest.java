package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/config/webmapping/context-servlet.xml",
		"file:src/main/resources/webmapping/spring/context-*.xml"
})
public class OpenGDSMobileTableDAOTest {

	@Autowired
	@Qualifier("OpenGDSMobileDAO")
	OpenGDSMobileTableDAO opengdsMobileDAO;
	
	//2016. 04. 11.
	@Test
	public void testAttributeSelectTableInfo() {
		JSONObject obj = new JSONObject();
		obj.put("tableName", "seoul_sig");
		assertNotNull(opengdsMobileDAO.attributeSelectTableInfo(obj));
	}
/*
	@Test
	public void testAttributeUpdateTable() {
		fail("Not yet implemented");
	}

	@Test
	public void testRealtimeSelectTableInfoJSONObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRealtimeSelectTableInfoCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testRealtimeSelectTableWhereSubject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRealtimeSelectTableInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testRealtimeInsertTableInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testRealtimeDeleteTableInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testToMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testToList() {
		fail("Not yet implemented");
	}
*/
}
