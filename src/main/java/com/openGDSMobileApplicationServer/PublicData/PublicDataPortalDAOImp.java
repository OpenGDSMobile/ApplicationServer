package com.openGDSMobileApplicationServer.PublicData;
 

import java.net.URL;

import org.codehaus.jackson.JsonParser;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository; 


@Repository("PortalDAO") 
public class PublicDataPortalDAOImp implements PublicDataDAO {
 

	PublicDataPortalDAOImp(){ 
	}
	
	@Override
	public JSONObject getJSONPublicData(String path) {
		
		return null;
	}

	@Override
	public Document getXMLPublicData(String path) {
		try {
			URL url = new URL(path);
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(url);
			return doc;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
