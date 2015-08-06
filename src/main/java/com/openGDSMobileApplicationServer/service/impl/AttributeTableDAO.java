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


@Repository("attrDAO")
public class AttributeTableDAO extends EgovAbstractMapper {

	@Autowired
	@Qualifier("sqlSession-postgresql")
	SqlSessionTemplate sess;
	
	
	public List<LinkedHashMap<String, Object>> selectAttr(JSONObject tableName) {
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		try{
			tableContentList = sess.selectList("spatialMapper.attrTable", tableName);
		}catch(Exception e){
			tableContentList = null;
		}		
		return tableContentList;
	}

}
