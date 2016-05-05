package com.openGDSMobileApplicationServer.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public interface TableService {
	
	String searchTableCount(JSONObject type);
	List<LinkedHashMap<String, Object>> searchTable(JSONObject tableName);
	int insertData(JSONObject insertData);
	int deleteData(JSONObject deleteData);
	int updateTable(JSONArray editObj);
	List<LinkedHashMap<String, Object>> searchTableWhere(JSONObject type);
}
