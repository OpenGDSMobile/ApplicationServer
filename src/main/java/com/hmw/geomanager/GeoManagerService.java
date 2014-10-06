package com.hmw.geomanager;

import it.geosolutions.geoserver.rest.decoder.RESTLayer;

public interface GeoManagerService {
	
	boolean createWorkspace(String name); 
	String requestLoadVector(String workspace,String name);
}
