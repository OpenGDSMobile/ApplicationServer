package com.openGDSMobileApplicationServer.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openGDSMobileApplicationServer.service.PublicDataService;

@RestController
@RequestMapping("/api")
public class PublicDataRestController {

	@Autowired
	@Qualifier("Seoul")
	PublicDataService seoulOpenDataObj;
	
	@Autowired
	@Qualifier("Portal")
	PublicDataService publicDataPortalObj; 

	@RequestMapping(value="/seoulOpenData.do", method=RequestMethod.POST,
					headers="Content-Type=application/json")
	public Map<String, Object> getSeoulOpenData(@RequestBody String str){ 
		Map<String, Object> message = new HashMap<String, Object>();
		System.out.println(str);
		JSONObject JSONData = new JSONObject(str);
		try {
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", seoulOpenDataObj.requestPublicData(JSONData).toString());
			return message;			
		} catch (Exception e) { 
			message.put("result", "ERROR");
			message.put("message", e.getMessage());
			message.put("data", null);
			return message;
		} 
	} 
	
	@RequestMapping(value="/publicDataPortal.do",method=RequestMethod.POST,
					headers="Content-Type=application/json")
	public Map<String, Object> getPublicDataPortal(@RequestBody String str){ 
		System.out.println(str);
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(str);
		try { 			
			message.put("result", "OK");
			message.put("data", publicDataPortalObj.requestPublicData(JSONData).toString());
			return message;
		} catch (Exception e) {
			message.put("result", "ERROR");
			message.put("message", e.getMessage());
			message.put("data", null);
			return message; 
		}
	}
}
