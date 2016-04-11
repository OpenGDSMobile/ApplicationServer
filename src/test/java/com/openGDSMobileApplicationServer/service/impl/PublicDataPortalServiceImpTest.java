package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openGDSMobileApplicationServer.service.PublicDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/config/webmapping/context-*.xml",
		"file:src/main/resources/webmapping/spring/context-*.xml"
})
public class PublicDataPortalServiceImpTest {

	
	@Autowired
	@Qualifier("Portal")
	PublicDataService publicService; 

	String TEST_VALUE_AIR = "{\"serviceName\":\"ArpltnInforInqireSvc\","+
							 "\"numOfRows\":\"100\","+
							 "\"serviceKey\":\"kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D\","+
							 "\"envType\":\"pm10Value\",\"sidoName\":\"%EC%84%9C%EC%9A%B8\"}";
	
	String TEST_VALUE_NUCLEAR = 
			"{\"serviceName\":\"NuclearPowerPlantRealtimeLevelofRadiation\","+
			 "\"serviceKey\":\"kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D\","+
			 "\"startDate\":\"YK\"}";
		 
	String TEST_VALUE_GREENGAS =
			"{\"serviceName\":\"GreenGasEmissionReport\",\"numOfRows\":\"100\","+
			 "\"serviceKey\":\"kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D\","+
			 "\"startDate\":\"201411\",\"endDate\":\"201411\"}";
	
	String EQUALS_TEST_VALUE = 
			"http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=kCxEhXiTf1qmDBlQFOOmw%2BemcPSxQXn5V5%2Fx8EthoHdbSojIdQvwX%2BHtWFyuJaIco0nUJtu12e%2F9acb7HeRRRA%3D%3D&envType=pm10Value&sidoName=%EC%84%9C%EC%9A%B8&numOfRows=100&";
	@Test
	public void testRequestPublicData() {
		JSONObject obj = new JSONObject(TEST_VALUE_AIR);
		//TimeAverageAirQuality
		assertNotNull(publicService.requestPublicData(obj));
		obj = null;
		obj = new JSONObject(TEST_VALUE_NUCLEAR);
		//NUCLEAR
		assertNotNull(publicService.requestPublicData(obj));
		obj = null;
		obj = new JSONObject(TEST_VALUE_GREENGAS);
		//GREEN GAS
		assertNotNull(publicService.requestPublicData(obj));
	}
	@Test
	public void testProcessServiceURL() {
		JSONObject obj = new JSONObject(TEST_VALUE_AIR);
		assertEquals( 
				((PublicDataPortalServiceImp) publicService)
					.processServiceURL(
					  obj, 
					  "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?")
					,EQUALS_TEST_VALUE);
	} 
	@Test
	public void testProcessXmlbyPublicData() throws JDOMException, IOException {
		URL url = new URL(EQUALS_TEST_VALUE);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(url);
		String[] resultJSONKeys = new String[]{"stationName", "pm10Value"};
		assertNotNull(((PublicDataPortalServiceImp) publicService)
				.processXmlbyPublicData(doc, resultJSONKeys));
	}
}
