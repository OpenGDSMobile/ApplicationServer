package com.openGDSMobileApplicationServer.service;
 
import org.jdom2.Document;
import org.json.JSONObject;

public interface PublicData {
	
	JSONObject getJSONPublicData(String path);
	Document getXMLPublicData(String path);

}
