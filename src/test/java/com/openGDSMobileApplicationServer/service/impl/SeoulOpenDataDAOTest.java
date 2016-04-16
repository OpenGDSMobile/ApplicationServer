package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openGDSMobileApplicationServer.service.impl.SeoulOpenDataDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/config/webmapping/context-servlet.xml",
		"file:src/main/resources/webmapping/spring/context-*.xml"
})
public class SeoulOpenDataDAOTest {
	
	@Autowired
	@Qualifier("seoulPublicDAO")
	SeoulOpenDataDAO dao;
	
	@Test
	public void testGetJSONPublicData() {
		String url = "http://openapi.seoul.go.kr:8088/6473565a72696e7438326262524174/json/TimeAverageAirQuality/1/100/201601010100/";
		assertNotNull(dao.getJSONPublicData(url));
	}
	
}
