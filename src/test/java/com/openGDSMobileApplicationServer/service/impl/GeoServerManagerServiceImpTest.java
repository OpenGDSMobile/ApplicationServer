package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

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
public class GeoServerManagerServiceImpTest {
	
	@Autowired
	GeoServerManagerServiceImp service;

	@Test
	public void testCreateWorkspace() {
		assertTrue(service.createWorkspace("test"));
		service.removeWorkspace("test");
	}

	@Test
	public void testGetLayerNames() {
		assertNotNull(service.getLayerNames("OpenGDSMobile"));
	}
	@Test
	public void testRemoveWorkspace() {
		service.createWorkspace("test");
		assertTrue(service.removeWorkspace("test"));
	}

}
