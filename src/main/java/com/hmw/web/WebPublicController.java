package com.hmw.web;
 

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.hmw.publicData.PublicDataService;
import com.hmw.util.Util;
 

@Controller
public class WebPublicController { 

	@Autowired
	PublicDataService pds;
	
	@RequestMapping(headers="Content-Type=application/json", 
			value="/SeoulpublicOpenData.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> seoulPublic(@RequestBody String JSONData){ 
		Map<String, Object> message = new HashMap<String, Object>();
		
		try {
			Map<String,Object> data = Util.convertJsonToObject(JSONData); 
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", pds.requestSeoulPublicData(data));
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
