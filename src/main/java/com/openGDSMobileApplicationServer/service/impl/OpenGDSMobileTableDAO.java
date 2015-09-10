package com.openGDSMobileApplicationServer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	Logger log = LogManager.getLogger("ERROR");
	
	
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
			tableContentList = sess.selectList("OpenGDSMobileMapper.realTimeTableColum", type);
		}catch(Exception e){
			tableContentList = null;
		}
		return tableContentList;
	}
	public List<LinkedHashMap<String, Object>> realtimeSelectTableInfo() {
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		try{
			tableContentList = sess.selectList("OpenGDSMobileMapper.realtimeTable");
		}catch(Exception e){
			tableContentList = null;
		}
		return tableContentList;
	}
	
	public int realtimeInsertTableInfo(JSONObject type) {
		try{
			return sess.insert("OpenGDSMobileMapper.realTimeTableInsert", type);
		} catch(Exception e){
			if (e.getCause().getMessage().contains("duplicate key value violates unique constraint")) {
				log.debug(e.getCause().getMessage());
				return -1;	
			}
			return 0;
		}
	}
	public int realtimeDeleteTableInfo(JSONObject type) {
		return sess.delete("OpenGDSMobileMapper.realTimeTableDelete", type);
	}

}
