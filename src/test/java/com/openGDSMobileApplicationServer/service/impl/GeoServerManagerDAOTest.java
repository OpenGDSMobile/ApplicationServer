package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import org.junit.Test;

public class GeoServerManagerDAOTest {
	
	GeoServerManagerDAO dao = null;
		
	@Test
	public void testGeoserverCreateWorkspace() {
		try {
			dao = new GeoServerManagerDAO("http://113.198.80.9/geoserver","admin","geoserver");
			assertTrue(dao.geoserverCreateWorkspace("test"));
			dao.getPublisher().removeWorkspace("test", true);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Test
	public void testGetGeoserverLayerNames() {
		try {
			dao = new GeoServerManagerDAO("http://113.198.80.9/geoserver","admin","geoserver");
			assertNotNull(dao.getGeoserverLayerNames("OpenGDSMobile"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
