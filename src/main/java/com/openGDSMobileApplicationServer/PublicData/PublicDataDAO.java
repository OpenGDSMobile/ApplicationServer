package com.openGDSMobileApplicationServer.PublicData;
 
import org.jdom2.Document;
import org.json.simple.JSONObject;

public interface PublicDataDAO {
	
	JSONObject getJSONPublicData(String path);
	Document getXMLPublicData(String path);

}
