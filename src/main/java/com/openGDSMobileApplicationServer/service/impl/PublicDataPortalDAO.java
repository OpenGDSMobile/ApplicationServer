package com.openGDSMobileApplicationServer.service.impl;
 

import java.net.URL;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository; 

import com.openGDSMobileApplicationServer.service.PublicData;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("PortalDAO") 
public class PublicDataPortalDAO extends EgovAbstractDAO implements PublicData {
 

	PublicDataPortalDAO(){ 
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
