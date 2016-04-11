package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/config/webmapping/context-*.xml",
		"file:src/main/resources/webmapping/spring/context-*.xml"
})
public class GeoServerManagerDAOTest {
	
	@Autowired
	GeoServerManagerDAO dao;
	
	//2016. 04. 11.
	@Test
	public void testGeoserverCreateWorkspace() {
		assertTrue(dao.geoserverCreateWorkspace("test"));
		dao.getPublisher().removeWorkspace("test", true);	
	}

	//2016. 04. 11.
	@Test
	public void testGetGeoserverLayerNames() {
		assertNotNull(dao.getGeoserverLayerNames("OpenGDSMobile"));
		
	}

}
