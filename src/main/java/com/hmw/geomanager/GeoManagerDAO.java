package com.hmw.geomanager;

import org.codehaus.jackson.JsonParser;

import it.geosolutions.geoserver.rest.decoder.RESTLayer;

public interface GeoManagerDAO {

	boolean geoserverCreateWorkspace(String name);
	JsonParser geoserverLoadVector(String workspace, String name);
}
