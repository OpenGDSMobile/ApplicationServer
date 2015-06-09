package com.openGDSMobileApplicationServer.service.impl;
 

import java.io.InputStreamReader;
import java.net.URL;

import org.jdom2.Document;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Repository; 

import com.openGDSMobileApplicationServer.service.PublicData;


@Repository("seoulPublicDAO") 
public class SeoulOpenDataDAO implements PublicData {

	 
	URL url;
	
	SeoulOpenDataDAO(){
		url = null;
	}
	
	@Override
	public JSONObject getJSONPublicData(String path) {
		try {
			url = new URL(path);
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			JSONObject jo = (JSONObject) JSONValue.parseWithException(isr);
			return jo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Document getXMLPublicData(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
