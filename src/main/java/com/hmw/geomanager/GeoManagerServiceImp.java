package com.hmw.geomanager;
 

import org.codehaus.jackson.JsonParser; 
import org.codehaus.jackson.map.ObjectMapper;
import org.geojson.FeatureCollection;
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
				JsonParser jp = geo_manager.geoserverLoadVector(workspace,name);
				String result = String.valueOf(jp.readValueAsTree());
				return result;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				return null;
			} 
	} 
}
