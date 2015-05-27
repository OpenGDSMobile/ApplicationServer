package com.openGDSMobileApplicationServer.GeoServerManager;

import java.util.List;

public interface GeoServerManagerDAO {

	boolean geoserverCreateWorkspace(String name); 
	List<String> getGeoserverLayerNames(String workspace);
	
}
