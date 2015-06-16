package com.openGDSMobileApplicationServer.service.impl;

import java.net.MalformedURLException; 
import java.util.List;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader; 

import org.springframework.stereotype.Repository;
 
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;


@Repository("geodao")
public class GeoServerManagerDAO{
 
	static String RESTURL = "http://127.0.0.1/geoserver";
	static String RESTUSER = "root";
	static String RESTPW = "geoserver";
	
	GeoServerRESTPublisher publisher; 
	GeoServerRESTReader reader ;
	
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
		System.out.println(reader.getLayers().getNames());
		return reader.getLayers().getNames();
	}

}