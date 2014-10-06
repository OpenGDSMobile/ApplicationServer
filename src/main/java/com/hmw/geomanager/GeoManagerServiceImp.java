package com.hmw.geomanager;

import java.io.IOException;

import it.geosolutions.geoserver.rest.decoder.RESTLayer;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GeoManagerServiceImp implements GeoManagerService {

	@Autowired
	@Qualifier("geodao")
	GeoManagerDAO geo_manager;
	
	@Override
	public boolean createWorkspace(String name) {
		// TODO Auto-generated method stub
		//System.out.println(name);
		return geo_manager.geoserverCreateWorkspace(name);
	}

	@Override
	public String requestLoadVector(String workspace, String name) {
		// TODO Auto-generated method stub
			try {
				return geo_manager.geoserverLoadVector(workspace,name).readValueAsTree().toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				return null;
			} 
	} 
}
