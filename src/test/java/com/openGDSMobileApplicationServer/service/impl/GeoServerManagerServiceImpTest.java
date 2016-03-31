package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeoServerManagerServiceImpTest {
	
	GeoServerManagerServiceImp service = new GeoServerManagerServiceImp();

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
	public void testRemoveWorkspace(String name) {
		service.createWorkspace("test");
		assertTrue(service.removeWorkspace(name));
	}

}
