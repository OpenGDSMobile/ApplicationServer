package com.openGDSMobileApplicationServer.service.impl;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openGDSMobileApplicationServer.service.TableService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("AttributeTables")
public class AttributeTableServiceImp extends EgovAbstractServiceImpl implements TableService {


	@Autowired
	@Qualifier("OpenGDSMobileDAO")
	OpenGDSMobileTableDAO at; 
	Logger log = LogManager.getLogger("org.springframework");

	@Override
	public List<LinkedHashMap<String, Object>> searchTable(JSONObject tableName) {
		List<LinkedHashMap<String, Object>> resultData = at.attributeSelectTableInfo(tableName);
		ListIterator<LinkedHashMap<String, Object>> listItr = resultData.listIterator();
		while(listItr.hasNext()){
			LinkedHashMap<String, Object> tmp = listItr.next();
			tmp.remove("geom");
		}
		log.info(resultData);
		return resultData;
	}


	@Override
	public int insertData(JSONObject insertData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteData(JSONObject deleteData) {
		// TODO Auto-generated method stub
		return 0;
	}

}
