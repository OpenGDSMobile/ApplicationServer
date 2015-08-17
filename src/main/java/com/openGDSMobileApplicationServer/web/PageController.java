package com.openGDSMobileApplicationServer.web;


import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openGDSMobileApplicationServer.service.GeoJsonService;

@Controller
public class PageController {

	@Autowired
	GeoJsonService geoJsonObj; 

	@RequestMapping(value="/webMappingDemo1.2.do")
	public String webMappingDemo12(){ 
		return "webmapping/demo1.2";
	}
	@RequestMapping(value="/webMappingDemo1.3.do")
	public String webMappingDemo13(){ 
		return "webmapping/demo1.3";
	}
	
	
	@RequestMapping(headers="Content-Type=application/json", value="/geojson.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> geoJSONLoad(@RequestBody JSONObject JSONData){
		Map<String, Object> message = new HashMap<String, Object>();
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
