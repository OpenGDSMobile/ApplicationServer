package com.openGDSMobileApplicationServer.service;

import java.util.List;
 

public interface GeoServerManagerService {

	boolean createWorkspace(String name);  
	boolean removeWorkspace(String name);  
	List<String> getLayerNames(String workspace);
}
