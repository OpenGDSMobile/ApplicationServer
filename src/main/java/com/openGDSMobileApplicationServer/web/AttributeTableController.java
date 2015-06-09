package com.openGDSMobileApplicationServer.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openGDSMobileApplicationServer.service.AttributeTableService;
import com.openGDSMobileApplicationServer.util.Util;


@Controller
public class AttributeTableController {
	
	@Autowired
	AttributeTableService ats;
	
	@RequestMapping(headers="Content-Type=application/json", value="/attrTable.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> attrTable(@RequestBody String JSONData){
		Map<String, Object> message = new HashMap<String, Object>();
		
		try {
			HashMap<String,Object> data = Util.convertJsonToObject(JSONData);
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", ats.createAttr(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return message;
	}
	

}
