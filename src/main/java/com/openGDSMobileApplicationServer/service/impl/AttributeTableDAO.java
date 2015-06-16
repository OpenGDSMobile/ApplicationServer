package com.openGDSMobileApplicationServer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;


@Repository("attrDAO")
public class AttributeTableDAO extends EgovAbstractMapper {

	@Autowired
	SqlSessionTemplate sess;
	
	
	public List<LinkedHashMap<String, Object>> selectAttr(HashMap<String, Object> tableName) {
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		try{
			tableContentList = sess.selectList("spatialMapper.attrTable", tableName);
		}catch(Exception e){
			tableContentList = null;
		}
		//System.out.println(tableContentList);
		
		return tableContentList;
	}

}
