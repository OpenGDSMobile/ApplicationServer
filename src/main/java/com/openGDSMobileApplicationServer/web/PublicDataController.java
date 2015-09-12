package com.openGDSMobileApplicationServer.web;
 

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openGDSMobileApplicationServer.service.PublicDataService; 

@Controller
public class PublicDataController { 

	@Autowired
	@Qualifier("Seoul")
	PublicDataService seoulOpenDataObj;
	
	@Autowired
	@Qualifier("Portal")
	PublicDataService publicDataPortalObj; 
	
	@RequestMapping(headers="Content-Type=application/json", 
			value="/SeoulOpenData.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> seoulOpenData(@RequestBody String str){ 
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(str);
		try {
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", seoulOpenDataObj.requestPublicData(JSONData).toString());
			return message;			
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			message.put("result", "ERROR");
			message.put("message", e.getMessage());
			message.put("data", null);
			return message;
		} 
	} 
	
	@RequestMapping(headers="Content-Type=application/json", 
			value="/PublicDataPortal.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> publicDataPortal(@RequestBody String str){ 
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(str);
		
		try { 			
			message.put("result", "OK");
			message.put("data", publicDataPortalObj.requestPublicData(JSONData).toString());
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
