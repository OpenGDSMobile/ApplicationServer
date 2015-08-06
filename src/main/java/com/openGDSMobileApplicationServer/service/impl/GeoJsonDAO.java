package com.openGDSMobileApplicationServer.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

@Repository("geojsondao")
public class GeoJsonDAO {
	
	
	public JSONObject getGeoJSON(String location){
		URL xmlLocation = this.getClass().getResource("/webmapping/geoBasedData/" + location + ".json");
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(xmlLocation.getPath()));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		return (JSONObject) obj;
	}

}
