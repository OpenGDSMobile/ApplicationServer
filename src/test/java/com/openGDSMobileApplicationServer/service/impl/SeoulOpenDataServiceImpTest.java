package com.openGDSMobileApplicationServer.service.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openGDSMobileApplicationServer.service.PublicDataService;
import com.openGDSMobileApplicationServer.service.impl.SeoulOpenDataServiceImp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/config/webmapping/context-servlet.xml",
		"file:src/main/resources/webmapping/spring/context-*.xml"
})
public class SeoulOpenDataServiceImpTest { 
	@Autowired
	@Qualifier("Seoul")
	PublicDataService publicService; 
	String TEST_VALUE_AIR = "{\"serviceKey\":\"6473565a72696e7438326262524174\","+
				  "\"returnType\":\"json\",\"serviceName\":\"TimeAverageAirQuality\","+
				  "\"amount\":\"1/100\",\"dateTimeValue\":\"201601010100\",\"envType\":\"PM10\"}";
	
	String RESULT_VALUE_AIR =
			"http://openapi.seoul.go.kr:8088/6473565a72696e7438326262524174/json/TimeAverageAirQuality/1/100/201601010100/";
	JSONObject RESULT_OBJ_AIR = new JSONObject(
			"{\"row\":[{\"PM10\":100,\"MSRSTE_NM\":\"강남구\"},{\"PM10\":82,\"MSRSTE_NM\":\"강동구\"},{\"PM10\":79,\"MSRSTE_NM\":\"강북구\"},{\"PM10\":100,\"MSRSTE_NM\":\"강서구\"},{\"PM10\":103,\"MSRSTE_NM\":\"관악구\"},{\"PM10\":98,\"MSRSTE_NM\":\"광진구\"},{\"PM10\":87,\"MSRSTE_NM\":\"구로구\"},{\"PM10\":96,\"MSRSTE_NM\":\"금천구\"},{\"PM10\":76,\"MSRSTE_NM\":\"노원구\"},{\"PM10\":71,\"MSRSTE_NM\":\"도봉구\"},{\"PM10\":99,\"MSRSTE_NM\":\"동대문구\"},{\"PM10\":77,\"MSRSTE_NM\":\"동작구\"},{\"PM10\":78,\"MSRSTE_NM\":\"마포구\"},{\"PM10\":83,\"MSRSTE_NM\":\"서대문구\"},{\"PM10\":106,\"MSRSTE_NM\":\"서초구\"},{\"PM10\":102,\"MSRSTE_NM\":\"성동구\"},{\"PM10\":81,\"MSRSTE_NM\":\"성북구\"},{\"PM10\":86,\"MSRSTE_NM\":\"송파구\"},{\"PM10\":119,\"MSRSTE_NM\":\"양천구\"},{\"PM10\":110,\"MSRSTE_NM\":\"영등포구\"},{\"PM10\":71,\"MSRSTE_NM\":\"용산구\"},{\"PM10\":65,\"MSRSTE_NM\":\"은평구\"},{\"PM10\":84,\"MSRSTE_NM\":\"종로구\"},{\"PM10\":77,\"MSRSTE_NM\":\"중구\"},{\"PM10\":80,\"MSRSTE_NM\":\"중랑구\"}]}");
	@Test
	public void testRequestPublicData() {
		JSONObject obj = new JSONObject(TEST_VALUE_AIR);
		//TimeAverageAirQuality
		assertNotNull(publicService.requestPublicData(obj));
		obj = null;
	}

	@Test
	public void testProcessServiceURL() {
		JSONObject obj = new JSONObject(TEST_VALUE_AIR);
		String[] urlOrder = 
				new String []{"serviceKey", "returnType", "serviceName", "amount", "dateTimeValue"};
		assertEquals( 
				((SeoulOpenDataServiceImp) publicService)
					.processServiceURL(
					  obj, urlOrder,
					  "http://openapi.seoul.go.kr:8088/")
					,RESULT_VALUE_AIR);
	}

	@Test
	public void testProcessJSONbySeoulData() throws URISyntaxException, JSONException, MalformedURLException, IOException {

		URI url = new URI(RESULT_VALUE_AIR);
		JSONTokener tokener = new JSONTokener(url.toURL().openStream());
		JSONObject jo = new JSONObject(tokener);
		String [] resultJSONKeys = new String[]{"MSRSTE_NM", "PM10"};
		assertEquals( 
				((SeoulOpenDataServiceImp) publicService)
				.processJSONbySeoulData(jo, resultJSONKeys).toString(),
				RESULT_OBJ_AIR.toString());
				
	}

}
