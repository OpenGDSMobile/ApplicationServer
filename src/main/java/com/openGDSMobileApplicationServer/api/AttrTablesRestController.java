package com.openGDSMobileApplicationServer.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openGDSMobileApplicationServer.service.TableService;
import com.openGDSMobileApplicationServer.valueObject.AttrTableVO;

@RestController
@RequestMapping("/api")
public class AttrTablesRestController {

	@Autowired
	@Qualifier("AttributeTables")
	TableService ts;
	
	//2016. 04. 11.
	@RequestMapping(value="/getAttrTable.do", method=RequestMethod.GET)
	public Map<Object, Object> attrSearchTableGet(@RequestBody AttrTableVO vo){
		Map<Object, Object> message = new HashMap<Object, Object>();
		System.out.println(vo);
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
	
	//2016. 04. 11.
	@RequestMapping(value="/getAttrTable.do", method=RequestMethod.POST, 
					headers="Content-Type=application/json")
	public Map<String, Object> attrSearchTablePost(@RequestBody AttrTableVO vo){
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
