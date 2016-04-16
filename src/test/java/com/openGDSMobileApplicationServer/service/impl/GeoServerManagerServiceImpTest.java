package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openGDSMobileApplicationServer.service.impl.GeoServerManagerServiceImp;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/config/webmapping/context-servlet.xml",
		"file:src/main/resources/webmapping/spring/context-*.xml"
})
public class GeoServerManagerServiceImpTest {
	
	@Autowired
	GeoServerManagerServiceImp service;

	//2016. 04. 11.
	@Test
	public void testCreateWorkspace() {
		assertTrue(service.createWorkspace("test"));
		service.removeWorkspace("test");
	}

	//2016. 04. 11.
	@Test
	public void testGetLayerNames() {
		assertNotNull(service.getLayerNames("OpenGDSMobile"));
	}
	
	//2016. 04. 11.
	@Test
	public void testRemoveWorkspace() {
		service.createWorkspace("test");
		assertTrue(service.removeWorkspace("test"));
	}

}
