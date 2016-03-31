package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class SeoulOpenDataDAOTest {
	
	SeoulOpenDataDAO dao = new SeoulOpenDataDAO();
	
	@Test
	public void testGetJSONPublicData() {
		String url = "http://openapi.seoul.go.kr:8088/6473565a72696e7438326262524174/json/TimeAverageAirQuality/1/100/201601010100/";
		assertNotNull(dao.getJSONPublicData(url));
	}
	
}
