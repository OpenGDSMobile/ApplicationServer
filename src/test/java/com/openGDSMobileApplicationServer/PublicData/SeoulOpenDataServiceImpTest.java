package com.openGDSMobileApplicationServer.PublicData;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;
import org.springframework.beans.factory.annotation.Autowired;

public class SeoulOpenDataServiceImpTest extends SqlSessionDaoSupport{

	
	@Test
	public void databaseTest() {
		List<HashMap<String, Object>> testTableList = new ArrayList<HashMap<String,Object>>();
	//	testTableList = sess.selectList("ump.attrTable");
		System.out.println(getSqlSession());
		
		
	}

}
