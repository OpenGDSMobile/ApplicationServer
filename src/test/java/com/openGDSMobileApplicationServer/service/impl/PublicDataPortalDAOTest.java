package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openGDSMobileApplicationServer.service.impl.PublicDataPortalDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/config/webmapping/context-servlet.xml",
		"file:src/main/resources/webmapping/spring/context-*.xml"
})
public class PublicDataPortalDAOTest {
	
	
	@Autowired
	@Qualifier("PortalDAO")
	PublicDataPortalDAO dao;

	
	//대기정보
	String TEST_AIR = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?envType=pm10Value&sidoName=%EC%84%9C%EC%9A%B8&serviceKey=kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D&numOfRows=100&";
	//원자력
	String TEST_UNCLEAR = "http://www.khnp.co.kr/environ/service/realtime/radiorate?startDate=YK&serviceKey=kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D&";
	//온실가스
	String TEST_GREENS = "http://www.kdhc.co.kr/openapi-data/service/kdhcCarbon/carbon?startDate=201411&serviceKey=kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D&endDate=201411&numOfRows=100&";
	@Test
	public void testGetXMLPublicData() {
		//대기정보
		assertNotNull(dao.getXMLPublicData(TEST_AIR));
		//원자력
		assertNotNull(dao.getXMLPublicData(TEST_UNCLEAR));
		//온실가스
		assertNotNull(dao.getXMLPublicData(TEST_GREENS));
		
	}

}
