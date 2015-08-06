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
 
	static String RESTURL = "http://127.0.0.1/geoserver";
	static String RESTUSER = "admin";
	static String RESTPW = "geoserver";
	
	GeoServerRESTPublisher publisher; 
	GeoServerRESTReader reader ;
	Logger log = LogManager.getLogger("org.springframework");
	
	GeoServerManagerDAO() throws MalformedURLException{
		super();
		publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
		reader= new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
	}
			
	public boolean geoserverCreateWorkspace(String name) {
		// TODO Auto-generated method stub
		return publisher.createWorkspace(name);
	} 
	
	public List<String> getGeoserverLayerNames(String workspace) {
		// TODO Auto-generated method stub 
		return reader.getDatastores(workspace).getNames();
	}

}