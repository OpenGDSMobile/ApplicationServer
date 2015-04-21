package com.openGDSMobileApplicationServer.AttributeTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("attrDAO")
public class AttributeTableDAOImp implements AttributeTableDAO {

	@Autowired
	SqlSessionTemplate sess;
	
	@Override
	public String selectAttr(String tableName) {
		// TODO Auto-generated method stub
		System.out.println(tableName);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		List<HashMap<String, Object>> testTableList = new ArrayList<HashMap<String,Object>>();
		testTableList = sess.selectList("spatialMapper.attrTable", map);
		
		System.out.println(testTableList);
		
		return null;
	}

}
