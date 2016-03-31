package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class AttributeTableServiceImpTest {
	
	@Autowired
	@Qualifier("AttributeTables")
	AttributeTableServiceImp attr;
	/*
	@Test
	public void testSearchTable() {
		JSONObject tableName = new JSONObject();
		tableName.put("tableName", "seoul_sig");
		attr.searchTable(tableName);
	}

	@Test
	public void testInsertData() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteData() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchTableCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateTable() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchTableWhere() {
		fail("Not yet implemented");
	}
*/
}
