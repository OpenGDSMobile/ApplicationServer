package com.openGDSMobileApplicationServer.AttributeTable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AttributeTableServiceImp implements AttributeTableService {


	@Autowired
	@Qualifier("attrDAO")
	AttributeTableDAO at; 
	
	@Override
	public List<LinkedHashMap<String, Object>> createAttr(HashMap<String, Object> tableName) {
		List<LinkedHashMap<String, Object>> resultData = at.selectAttr(tableName);
		return resultData;
	}

}
