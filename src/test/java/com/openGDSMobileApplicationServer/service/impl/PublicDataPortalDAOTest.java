package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class PublicDataPortalDAOTest {
	
	PublicDataPortalDAO dao = new PublicDataPortalDAO();

	@Test
	public void testGetXMLPublicData() {
		String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?envType=pm10Value&sidoName=%EC%84%9C%EC%9A%B8&serviceKey=kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D&numOfRows=100&";
		assertNotNull(dao.getXMLPublicData(url));		
	}

}
