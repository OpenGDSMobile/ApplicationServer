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

import com.openGDSMobileApplicationServer.service.TableService;


@Controller
public class AttributeTablesController {
	
	@Autowired
	@Qualifier("AttributeTables")
	TableService ts;
	
	@RequestMapping(headers="Content-Type=application/json", value="/attrTable.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> attrSearchTable(@RequestBody String str){
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(str);
		
		try {
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", ts.searchTable(JSONData));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return message;
	}
}
