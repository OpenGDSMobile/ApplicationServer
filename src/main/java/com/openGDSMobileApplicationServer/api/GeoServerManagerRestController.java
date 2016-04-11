package com.openGDSMobileApplicationServer.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openGDSMobileApplicationServer.service.GeoServerManagerService;

@RestController
@RequestMapping("/api")
public class GeoServerManagerRestController {

	@Autowired
	GeoServerManagerService geoServerManagerObj; 
	
	@RequestMapping(value="/createWorkspace.do", method=RequestMethod.GET)
	public Map<String, Object> createWorkspace(@RequestParam("wsName") String wsName){ 
		Map<String, Object> message = new HashMap<String, Object>();
		try { 
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", geoServerManagerObj.createWorkspace(wsName)); 
			return message;			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message.put("result", "ERROR");
			message.put("message", e.getMessage());
			message.put("data", null);
			return message;
		} 
	}
	
	@RequestMapping(value="/getLayerNames.do", method=RequestMethod.GET)
	public Map<String, Object> getLayerNames(@RequestParam("wsName") String wsName){ 
		Map<String, Object> message = new HashMap<String, Object>();
		try {
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", geoServerManagerObj.getLayerNames(wsName)); 
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
