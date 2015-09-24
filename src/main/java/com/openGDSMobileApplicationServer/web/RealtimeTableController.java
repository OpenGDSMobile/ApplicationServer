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
public class RealtimeTableController {
	
	@Autowired
	@Qualifier("realtimeTable")
	TableService ts;
	
	@RequestMapping(headers="Content-Type=application/json", value="/realtimeInfoSearch.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> realtimeSearchTable(@RequestBody String str){
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(str);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}
	
	@RequestMapping(headers="Content-Type=application/json", value="/realtimeInfoInsert.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> realtimeInsertTable(@RequestBody String str){
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(str);
		try {
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", ts.insertData(JSONData));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return message;
	}
	
	@RequestMapping(headers="Content-Type=application/json", value="/realtimeInfoDelete.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> realtimeDeleteTable(@RequestBody String str){
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject JSONData = new JSONObject(str);
		try {
			message.put("result", "OK");
			message.put("message", null); 
			message.put("data", ts.deleteData(JSONData));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return message;
		
	}

	
}
