package com.openGDSMobileApplicationServer.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;

public interface TableService {

	List<LinkedHashMap<String, Object>> searchTable(JSONObject tableName);
}
