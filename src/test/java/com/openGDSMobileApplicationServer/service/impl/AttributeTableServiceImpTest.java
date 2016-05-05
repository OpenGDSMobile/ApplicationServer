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
import com.openGDSMobileApplicationServer.service.impl.AttributeTableServiceImp;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/config/webmapping/context-servlet.xml",
		"file:src/main/resources/webmapping/spring/context-*.xml"
})
public class AttributeTableServiceImpTest {
	
	@Autowired
	@Qualifier("AttributeTables")
	TableService attr;
	
	//2016. 04. 11.
	@Test
	public void testSearchTable() {
		JSONObject tableName = new JSONObject();
		tableName.put("tableName", "seoul_sig");
		assertNotNull(attr.searchTable(tableName));
	}

	@Test
	public void testInsertData() {
		JSONObject tableName = new JSONObject();
		tableName.put("", "");
		assertNotNull(attr.insertData(tableName));
	}

	@Test
	public void testDeleteData() {
		JSONObject tableName = new JSONObject();
		tableName.put("", "");
		assertNotNull(attr.deleteData(tableName));
	}

	@Test
	public void testSearchTableCount() {
		JSONObject tableName = new JSONObject();
		tableName.put("", "");
		assertNull( attr.searchTableCount(tableName));
	}

	@Test
	public void testUpdateTable() {
		JSONArray tableName = new JSONArray();
		assertEquals(attr.updateTable(tableName), 1);
	}

	@Test
	public void testSearchTableWhere() {
		JSONObject tableName = new JSONObject();
		tableName.put("", "");
		assertNull(attr.searchTableWhere(tableName));
	}
}
