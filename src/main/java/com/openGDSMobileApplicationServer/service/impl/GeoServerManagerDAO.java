package com.openGDSMobileApplicationServer.service.impl;

import java.net.MalformedURLException; 
import java.util.List;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader; 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
 

@Repository("geodao")
public class GeoServerManagerDAO{
 
	static String geoServerUrl = "http://113.198.80.9/geoserver";
	static String geoServerUser = "admin";
	static String geoServerPw = "geoserver";
	
	GeoServerRESTPublisher publisher; 
	GeoServerRESTReader reader ;
	Logger log = LogManager.getLogger("org.springframework");
	
	GeoServerManagerDAO() throws MalformedURLException{
		super();
		publisher = new GeoServerRESTPublisher(geoServerUrl, geoServerUser, geoServerPw);
		reader= new GeoServerRESTReader(geoServerUrl, geoServerUser, geoServerPw);
	}
	
	public boolean geoserverCreateWorkspace(String name) {
		// TODO Auto-generated method stub
		return publisher.createWorkspace(name);
	} 	
	
	public boolean geoserverRemoveWorkspace(String name) {
		// TODO Auto-generated method stub
		return publisher.removeWorkspace(name, true);
	} 
	
	public List<String> getGeoserverLayerNames(String workspace) {
		// TODO Auto-generated method stub 
		return reader.getDatastores(workspace).getNames();
	}

	public GeoServerRESTPublisher getPublisher() {
		return publisher;
	} 

}