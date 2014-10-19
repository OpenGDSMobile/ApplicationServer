package com.hmw.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hmw.geomanager.GeoManagerService;
import com.hmw.util.Util;

@Controller
public class WebGeoServerController { 
	@Autowired
	GeoManagerService geomanager; 
	
	@RequestMapping(headers="Content-Type=application/json", value="/createWorkspace.do",
					method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Map<String, Object> workspaceCreate(@RequestBody String JSONData){
		
		Map<String, Object> message = new HashMap<String, Object>();
		try {
			//Map<String,Object> data = Util.convertJsonToObject(JSONData);
			//boolean result = geomanager.createWorkspace(data.get("name").toString());
			
			message.put("result", "OK");
			message.put("message", null);
		//	message.put("data", result);
			return message;			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message.put("result", "ERROR");
			message.put("message", e.getMessage());
			message.put("data", null);
			return message;
		} 
	}  

	@RequestMapping(headers="Content-Type=application/json", value="/loadVector.do",
			method={RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Map<String, Object> loadVector(@RequestBody String JSONData){
//	public String loadVector(@RequestBody String JSONData){
		//System.out.println(geomanager.requestLoadVector("opengds", ""));
		//geomanager.requestLoadVector("opengds", "");
		System.out.println(JSONData);
		
		Map<String, Object> message = new HashMap<String, Object>();
		try {
			Map<String,Object> data = Util.convertJsonToObject(JSONData);
		//	geomanager.requestLoadVector("korea", data.get("name").toString());
			message.put("result", "OK");
			message.put("message", null);
			message.put("data", geomanager.requestLoadVector("opengds", data.get("name").toString() ));
			return message;
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			message.put("result", "ERROR");
			message.put("message", e.getMessage());
			message.put("data", null);
			return message;
		} 
		
		
		
		//return geomanager.requestLoadVector("opengds","");
	} 
	
}
