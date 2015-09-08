package com.openGDSMobileApplicaionServer.service.impl.test;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.openGDSMobileApplicationServer.service.impl.RealtimeInfoTableServiceImp;

public class RealtimeInfoTableServiceImpTest {

	
	RealtimeInfoTableServiceImp test;
	
	@Test
	public void testSearchTable() {
		fail("Not yet implemented");
		JSONObject type = null;
		type.put("column", "subject");
		test.searchTable(type);
	}

}
