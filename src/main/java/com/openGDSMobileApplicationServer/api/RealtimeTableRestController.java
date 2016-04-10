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
import com.openGDSMobileApplicationServer.valueObject.UsersVO;

@RestController
@RequestMapping("/api")
public class RealtimeTableRestController {

	@Autowired
	@Qualifier("realtimeTable")
	TableService ts;
	
	@RequestMapping(value="/realtimeInfoSearch.do", method=RequestMethod.POST,
					headers="Content-Type=application/json")
	public Map<String, Object> realtimeSearchTable(UsersVO vo){
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(vo);
		System.out.println(JSONData.toString());
		try {
			message.put("result", "OK");
			message.put("message", null);
			if (JSONData.get("column").equals("userid")) {
				boolean resultData;
				if (ts.searchTable(JSONData) == null ){
					resultData = true;
				} else {
					resultData = false;
				}
				message.put("data", resultData);
			} else {
				message.put("data", ts.searchTable(JSONData));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}
	@RequestMapping(value="/realtimeInfoInsert.do", method=RequestMethod.POST,
					headers="Content-Type=application/json")
	public Map<String, Object> realtimeInsertTable(UsersVO vo){
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(vo);
		try {
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", ts.insertData(JSONData));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return message;
	}
	@RequestMapping(value="/realtimeInfoDelete.do", method=RequestMethod.POST,
					headers="Content-Type=application/json")
	public Map<String, Object> realtimeDeleteTable(UsersVO vo){
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(vo);
		try {
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", ts.deleteData(JSONData));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return message;	
	}
}
