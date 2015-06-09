package com.openGDSMobileApplicationServer.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openGDSMobileApplicationServer.service.AttributeTable;
import com.openGDSMobileApplicationServer.service.AttributeTableService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service
public class AttributeTableServiceImp extends EgovAbstractServiceImpl implements AttributeTableService {


	@Autowired
	@Qualifier("attrDAO")
	AttributeTable at; 
	
	@Override
	public List<LinkedHashMap<String, Object>> createAttr(HashMap<String, Object> tableName) {
		List<LinkedHashMap<String, Object>> resultData = at.selectAttr(tableName);
		return resultData;
	}

}
