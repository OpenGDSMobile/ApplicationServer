package com.openGDSMobileApplicationServer.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openGDSMobileApplicationServer.GeoServerManager.GeoServerManagerService;
import com.openGDSMobileApplicationServer.util.Util;

@Controller
public class GeoServerManagerController { 
	@Autowired
	GeoServerManagerService geoServerManagerObj; 
	
	@RequestMapping(value="/createWorkspace.do")
	public @ResponseBody Map<String, Object> workspaceCreate(@RequestBody String JSONData){ 
		Map<String, Object> message = new HashMap<String, Object>();
		try { 
			message.put("result", "OK");
			message.put("message", null); 
			return message;			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message.put("result", "ERROR");
			message.put("message", e.getMessage());
			message.put("data", null);
			return message;
		} 
	}
	
	@RequestMapping(value="/getLayerNames.do")
	public @ResponseBody Map<String, Object> getLayerNames(@RequestBody String data){ 
		Map<String, Object> message = new HashMap<String, Object>();
		try {  
			Map<String,Object> Mapdata = Util.convertJsonToObject(data);
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", geoServerManagerObj.getLayerNames(Mapdata.get("wsName").toString())); 
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
