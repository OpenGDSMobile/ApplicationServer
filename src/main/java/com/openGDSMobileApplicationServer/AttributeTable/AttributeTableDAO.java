package com.openGDSMobileApplicationServer.AttributeTable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface AttributeTableDAO {
	
	List<LinkedHashMap<String, Object>> selectAttr(HashMap<String, Object> name);

}
