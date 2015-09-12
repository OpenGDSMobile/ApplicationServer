package com.openGDSMobileApplicationServer.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;


@Repository("geojsondao")
public class GeoJsonDAO {
	
	public JSONObject getGeoJSON(String location){
		URL tpLocation = this.getClass().getResource("/webmapping/geoBasedData/" + location + ".json");
		String jsonString = readFile(tpLocation.getPath());
		JSONObject obj = new JSONObject(jsonString);
		return obj;
	}
	
	public String readFile(String filename) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while(line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

}
