package com.openGDSMobileApplicationServer.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openGDSMobileApplicationServer.service.GeoJsonService;

@Service
public class GeoJsonServiceImp implements GeoJsonService {
	@Autowired
	@Qualifier("geojsondao")
	GeoJsonDAO geojson;
	
	//2016. 04. 11.
	@Override
	public JSONObject getLocation(JSONObject obj) {
		String location = (String) obj.get("jsonName");
		JSONObject geojsonObj = geojson.getGeoJSON(location);
		return geojsonObj;
	}

}
