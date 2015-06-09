package com.openGDSMobileApplicationServer.service.impl;
 
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openGDSMobileApplicationServer.service.GeoServerManager;
import com.openGDSMobileApplicationServer.service.GeoServerManagerService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class GeoServerManagerServiceImp extends EgovAbstractServiceImpl implements GeoServerManagerService {

	@Autowired
	@Qualifier("geodao")
	GeoServerManager geo_manager;
	
	@Override
	public boolean createWorkspace(String name) {
		return geo_manager.geoserverCreateWorkspace(name);
	} 
	@Override
	public List<String> getLayerNames(String workspace) {
		return geo_manager.getGeoserverLayerNames(workspace);
	} 
}
