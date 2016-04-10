package com.openGDSMobileApplicationServer.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openGDSMobileApplicationServer.service.TableService;
import com.openGDSMobileApplicationServer.valueObject.AttrTableVO;

@RestController
@RequestMapping("/api")
public class AttrTablesRestController {

	@Autowired
	@Qualifier("AttributeTables")
	TableService ts;

	@RequestMapping(value="/getAttrTable.do", method=RequestMethod.GET)
	public Map<String, Object> attrSearchTableGet(AttrTableVO vo){
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(vo);
		try {
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", ts.searchTable(JSONData));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	@RequestMapping(value="/getAttrTable.do", method=RequestMethod.POST, headers="Content-Type=application/json")
	public Map<String, Object> attrSearchTablePost(AttrTableVO vo){
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(vo);
		try {
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", ts.searchTable(JSONData));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
}
