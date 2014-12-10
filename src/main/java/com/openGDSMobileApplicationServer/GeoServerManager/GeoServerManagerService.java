package com.openGDSMobileApplicationServer.GeoServerManager;

import java.util.List;
 

public interface GeoServerManagerService {
	
	boolean createWorkspace(String name);  
	List<String> getLayerNames(String workspace);
}
