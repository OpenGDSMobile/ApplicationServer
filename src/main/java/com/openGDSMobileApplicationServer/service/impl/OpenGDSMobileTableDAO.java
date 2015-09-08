package com.openGDSMobileApplicationServer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;


@Repository("OpenGDSMobileDAO")
public class OpenGDSMobileTableDAO extends EgovAbstractMapper {

	@Autowired
	@Qualifier("sqlSession-postgresql")
	SqlSessionTemplate sess;
	
	
	public List<LinkedHashMap<String, Object>> attributeSelectTableInfo(JSONObject tableName) {
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		try{
			tableContentList = sess.selectList("OpenGDSMobileMapper.attrTable", tableName);
		}catch(Exception e){
			tableContentList = null;
		}		
		return tableContentList;
	}

	public List<LinkedHashMap<String, Object>> realtimeSelectTableInfo(JSONObject type) {
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		try{
			tableContentList = sess.selectList("OpenGDSMobileMapper.reamTimeTableColum", type);
		}catch(Exception e){
			tableContentList = null;
		}
		return tableContentList;
	}
	public List<LinkedHashMap<String, Object>> realtimeSelectTableInfo() {
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		try{
			tableContentList = sess.selectList("OpenGDSMobileMapper.reamTimeTable");
		}catch(Exception e){
			tableContentList = null;
		}
		return tableContentList;
	}

}
