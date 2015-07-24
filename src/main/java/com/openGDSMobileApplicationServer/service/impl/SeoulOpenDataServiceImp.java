package com.openGDSMobileApplicationServer.service.impl;
 
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openGDSMobileApplicationServer.service.PublicData;
import com.openGDSMobileApplicationServer.service.PublicDataService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("Seoul")
public class SeoulOpenDataServiceImp extends EgovAbstractServiceImpl implements PublicDataService{ 

	@Autowired
	@Qualifier("seoulPublicDAO")
	PublicData publicDataobj;   
	
	private String serviceName = null;
	private String serviceURL = null;
	private String[] resultJSONKeys = null;
	Logger log = LogManager.getLogger("org.springframework");
	
	@Override
	public String requestPublicData(JSONObject data) {
		Iterator<?> it = data.keySet().iterator();
		log.info(data);
		while(it.hasNext()){
			String tmp = (String) it.next();
			if(tmp.equals("serviceName")){
				serviceName = String.valueOf(data.get(tmp));
			}			
		} 
		if(serviceName.equals("TimeAverageAirQuality") || serviceName.equals("RealtimeRoadsideStation")){
			String baseURL = "http://openapi.seoul.go.kr:8088/";
			String[] urlOrder = 
					new String []{"serviceKey", "returnType", "serviceName", "amount", "dateTimeValue"};
			serviceURL=processServiceURL(data, urlOrder, baseURL);
			JSONObject jsonObj = publicDataobj.getJSONPublicData(serviceURL);
			
			resultJSONKeys = new String[]{"MSRSTE_NM", (String) data.get("envType") };
			return processJSONbySeoulData(jsonObj, resultJSONKeys).toJSONString();
		}
		return null; 
	}
	
	public String processServiceURL(JSONObject data, String[] urlOrder,String baseURL){
		String url = baseURL;
		int order = 0;
		for (int i=0; i<urlOrder.length; i++){
			Iterator<?> it = data.keySet().iterator();
			while(it.hasNext()){
				String key = (String) it.next();
				if (urlOrder[order].equals(key)){
					url += data.get(key) + "/";
					break;
				}
			}			
			order++;
		}
		log.info(url);
		return url;
	}
		
	@SuppressWarnings("unchecked")
	public JSONObject processJSONbySeoulData(JSONObject data, String[] keys){
		JSONObject source = (JSONObject) data.get(serviceName);
		JSONArray rowList = (JSONArray) source.get("row");
		JSONObject result = new JSONObject();
		JSONArray list = new JSONArray();
		for(int i=0; i<rowList.size(); i++){
			JSONObject contents = (JSONObject) rowList.get(i);
			JSONObject obj = new JSONObject();
			for(int j=0; j<keys.length; j++){
				obj.put(keys[j], contents.get(keys[j]));				
			}
			list.add(obj);
		}
		result.put("row", list);
		log.info(source);
		log.info(result);
		return result;
	}
}
