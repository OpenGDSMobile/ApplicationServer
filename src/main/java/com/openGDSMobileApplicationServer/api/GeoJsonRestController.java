package com.openGDSMobileApplicationServer.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openGDSMobileApplicationServer.service.GeoJsonService;
import com.openGDSMobileApplicationServer.valueObject.GeoJsonVO;

@RestController
@RequestMapping("/api")
public class GeoJsonRestController {

	@Autowired
	GeoJsonService geoJsonObj; 

	@RequestMapping(value="/getGeoJson.do", method=RequestMethod.GET)
	public Map<Object, Object> getGeoJsonGet(GeoJsonVO vo){
		JSONObject JSONData = new JSONObject(vo);
		System.out.println(JSONData.toString());
		Map<Object, Object> message = new HashMap<>();
		JSONObject resultObj = geoJsonObj.getLocation(JSONData);
		try {
			message.put("result", "OK");
			message.put("message", "OK"); 
			message.put("data", resultObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return message;
	}
	@RequestMapping(value="/getGeoJson.do", method=RequestMethod.POST, headers="Content-Type=application/json")
	public Map<Object, Object> getGeoJsonPost(GeoJsonVO vo){
		JSONObject JSONData = new JSONObject(vo);
		Map<Object, Object> message = new HashMap<>();
		System.out.println(JSONData.toString());
		JSONObject resultObj = geoJsonObj.getLocation(JSONData);
		try {
			message.put("result", "OK");
			message.put("message", "OK"); 
			message.put("data", resultObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return message;
	}
	

}
