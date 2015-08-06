package com.openGDSMobileApplicationServer.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;

public interface AttributeTableService {
	
//	List<LinkedHashMap<String, Object>> createAttr(HashMap<String, Object> tableName);
	List<LinkedHashMap<String, Object>> createAttr(JSONObject tableName);

}
