package com.openGDSMobileApplicationServer.service.impl;

import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Repository;


import com.openGDSMobileApplicationServer.service.PublicData;

@Repository("PortalDAO")
public class PublicDataPortalDAO implements PublicData {

	PublicDataPortalDAO() {
		
	}

	@Override
	public JSONObject getJSONPublicData(String path) {
		URI url =null;
		try {
			url = new URI(path);
			InputStreamReader is = new InputStreamReader(url.toURL().openStream(), "EUC-KR");//Encoding for Hangul breakage occurs.
			JSONTokener tokener = new JSONTokener(is);
			JSONArray ja = new JSONArray(tokener);//Return value is JSONArray.
			JSONObject jo = new JSONObject();
			jo.put("row", ja);
			return jo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

	}
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
