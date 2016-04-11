package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/config/webmapping/context-servlet.xml",
		"file:src/main/resources/webmapping/spring/context-*.xml"
})
public class GeoJsonDAOTest {

	@Autowired
	GeoJsonDAO dao;
	
	@Test
	public void testGetGeoJSON() {
		String name = "SIDO";
		assertNotNull(dao.getGeoJSON(name));		
	}

	@Test
	public void testReadFile() {
		URL tpLocation = this.getClass()
				.getResource("/webmapping/geoBasedData/SIDO.json");
		dao.readFile(tpLocation.getPath());
	}

}
