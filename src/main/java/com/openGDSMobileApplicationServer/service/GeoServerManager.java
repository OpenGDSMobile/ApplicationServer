package com.openGDSMobileApplicationServer.service;

import java.util.List;

public interface GeoServerManager {

	boolean geoserverCreateWorkspace(String name); 
	List<String> getGeoserverLayerNames(String workspace);
	
}
