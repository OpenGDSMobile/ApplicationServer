package com.openGDSMobileApplicationServer.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface AttributeTable {
	
	List<LinkedHashMap<String, Object>> selectAttr(HashMap<String, Object> name);

}
