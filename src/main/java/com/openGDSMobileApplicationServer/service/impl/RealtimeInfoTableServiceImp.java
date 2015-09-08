package com.openGDSMobileApplicationServer.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openGDSMobileApplicationServer.service.TableService;


@Service("realtimeTable")
public class RealtimeInfoTableServiceImp implements TableService {

	
	@Autowired
	@Qualifier("OpenGDSMobileDAO")
	OpenGDSMobileTableDAO at;
	Logger log = LogManager.getLogger("org.springframework");
	
	@Override
	public List<LinkedHashMap<String, Object>> searchTable(JSONObject type) {
		List<LinkedHashMap<String, Object>> tmpData = null;
		List<LinkedHashMap<String, Object>> resultData = null;
		List<String> sub = new ArrayList<String>();
		
		if (type.get("column") == "all") {
			tmpData = at.realtimeSelectTableInfo();
			
		} else {
			tmpData = at.realtimeSelectTableInfo(type);
			ListIterator<LinkedHashMap<String, Object>> listItr = tmpData.listIterator();
			if (type.get("column") == "subject") {
	//			resultData = new List<LinkedHashMap<String, Object>>(new HashSet<String>(tmpData));
				while(listItr.hasNext()){
					LinkedHashMap<String, Object> tmp = listItr.next();
					String subject = (String) tmp.get(type.get("column"));
					sub.add(subject);
					log.info(subject);
				}
			}
			
			
		}
		
		log.info(tmpData);
		return null;
	}	
	
	public void insertUser(JSONObject data){
		
		
	}


}
