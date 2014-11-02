package com.openGDSMobile.GeoServerManager;

import java.util.List;

import org.codehaus.jackson.JsonParser;

import it.geosolutions.geoserver.rest.decoder.RESTLayer;

public interface GeoManagerDAO {

	boolean geoserverCreateWorkspace(String name); 
	List<String> getGeoserverLayerNames(String workspace);
	
}
