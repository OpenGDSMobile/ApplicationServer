package com.openGDSMobileApplicationServer.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openGDSMobileApplicationServer.airQuality.AirQualityService;

@Controller
public class WebAirQualityController {
	
	@Autowired
	AirQualityService aqs;
	
	@RequestMapping("/airQualityCreateMap.do")
	public @ResponseBody Map<String, Object> createAirQualityMap(@RequestBody String JSONData){
		System.out.println("airQualityCreateMap");

		Map<String, Object> message = new HashMap<String, Object>();
		
		try { 
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", aqs.requestAirQualityMapCreate(JSONData));
			return message;			
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			message.put("result", "ERROR");
			message.put("message", e.getMessage());
			message.put("data", null);
			return message;
		}
	}
}
