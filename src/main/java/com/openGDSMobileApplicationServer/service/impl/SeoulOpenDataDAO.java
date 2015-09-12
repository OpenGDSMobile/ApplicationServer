package com.openGDSMobileApplicationServer.service.impl;
 

import java.net.URI;

import org.jdom2.Document;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Repository; 

import com.openGDSMobileApplicationServer.service.PublicData;



@Repository("seoulPublicDAO") 
public class SeoulOpenDataDAO implements PublicData {

	 
	URI url;
	
	SeoulOpenDataDAO(){
		url = null;
	}
	
	@Override
	public JSONObject getJSONPublicData(String path) {
		try {
			url = new URI(path);
			JSONTokener tokener = new JSONTokener(url.toURL().openStream());
			JSONObject jo = new JSONObject(tokener);
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
