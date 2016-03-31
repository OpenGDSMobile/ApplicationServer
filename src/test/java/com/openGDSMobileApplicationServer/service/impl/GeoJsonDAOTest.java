package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;

public class GeoJsonDAOTest {

	GeoJsonDAO dao = new GeoJsonDAO();
	
	@Test
	public void testGetGeoJSON() {
		String name = "SIDO";
		assertNotNull(dao.getGeoJSON(name));		
	}

	@Test
	public void testReadFile() {
		URL tpLocation = this.getClass().getResource("/webmapping/geoBasedData/SIDO.json");
		dao.readFile(tpLocation.getPath());
	}

}
