package com.openGDSMobileApplicationServer.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
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
		List<String> sub = null;
		int cnt = 0;
		if (type.get("column") == "all") {
			tmpData = at.realtimeSelectTableInfo();
			
		} else {
			tmpData = at.realtimeSelectTableInfo(type);
			if (tmpData != null){
				ListIterator<LinkedHashMap<String, Object>> listItr = tmpData.listIterator();
				if (type.get("column").equals("subject")) {
					while(listItr.hasNext()){
						LinkedHashMap<String, Object> tmp = listItr.next();
						String subject = (String) tmp.get(type.get("column"));
						if (sub == null) {
							sub = new ArrayList<String>();
							sub.add(subject);
							resultData = new ArrayList<LinkedHashMap<String, Object>>();
							resultData.add(cnt, tmp);
							cnt++;
						}
						for (String s : sub) {
							if (!s.equals(subject)) {
								sub.add(subject);
								resultData.add(cnt, tmp);
								cnt++;
							}
						}
					}
				} else if (type.get("column").equals("userid")) {
					while(listItr.hasNext()) {
						LinkedHashMap<String, Object> tmp = listItr.next();
						String userid = (String) tmp.get(type.get("column"));
						if (userid.equals(type.get("userid"))) {
							sub = new ArrayList<String>();
							sub.add(userid);
							resultData = new ArrayList<LinkedHashMap<String, Object>>();
							resultData.add(0, tmp);
						}
					}
				}
			}
		}
		log.info(resultData);
		return resultData;
	}
	@Override
	public String searchTableCount(JSONObject type) {
		return at.realtimeSelectTableInfoCount(type).get("count").toString();
	}
	/*성공 1, 아이디가 있는 경우 -1, 그 외 0*/
	@Override
	public int insertData(JSONObject insertData) {
		return at.realtimeInsertTableInfo(insertData);
		
	}

	@Override
	public int deleteData(JSONObject deleteData) {
		// TODO Auto-generated method stub
		return at.realtimeDeleteTableInfo(deleteData);
	}

	@Override
	public List<LinkedHashMap<String, Object>> updateTable(JSONArray editObj) {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	@Override
	public List<LinkedHashMap<String, Object>> searchTableWhere(JSONObject type) {
		// TODO Auto-generated method stub
		return at.realtimeSelectTableWhereSubject(type);
	}


}
