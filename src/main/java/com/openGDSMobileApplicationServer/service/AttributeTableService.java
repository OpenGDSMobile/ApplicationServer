package com.openGDSMobileApplicationServer.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface AttributeTableService {
	
	List<LinkedHashMap<String, Object>> createAttr(HashMap<String, Object> tableName);

}
