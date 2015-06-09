package com.openGDSMobileApplicationServer.service.impl;
 
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openGDSMobileApplicationServer.service.PublicData;
import com.openGDSMobileApplicationServer.service.PublicDataService;

@Service("Seoul")
public class SeoulOpenDataServiceImp implements PublicDataService{ 

	@Autowired
	@Qualifier("seoulPublicDAO")
	PublicData publicDataobj;   
	
	@Override
	public String requestPublicData(Map<String,Object> data) {  
		String serviceName = null; 
		String serviceURL = null;
		String result = null;
		Set<String> keys = data.keySet();
		Iterator<String> it = keys.iterator();
		JSONObject jo = null;
		
		while(it.hasNext()){
			String tmp = it.next();
			if(tmp.equals("serviceName")){
				serviceName = String.valueOf(data.get(tmp));
			}			
		} 
		if(serviceName.equals("TimeAverageAirQuality") 
				|| serviceName.equals("RealtimeRoadsideStation")){
			serviceURL=processEnvironmentURL(data);
			jo = publicDataobj.getJSONPublicData(serviceURL);
			result = processEnvironmentData(data, jo);
		}
		try {
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public String processEnvironmentURL(Map<String,Object> data){ 
		String[] keys = {"serviceName","keyValue","dateValue","timeValue","envType"};
		String[] keysValue = new String[]{"","","","",""}; 
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		String amount = "1/100"; 
		Set<String> dataKeyNames = data.keySet();
		Iterator<String> it = dataKeyNames.iterator();  
		while(it.hasNext()){ 
			String tmp = it.next();
			for(int i=0; i<keys.length; i++){
				if(keys[i].equals(tmp)){
					keysValue[i] = String.valueOf(data.get(tmp));
				}
			} 
		}
		keysValue[2] = keysValue[2].replaceAll(match, "");
		keysValue[3] = keysValue[3].replaceAll(match, "");
		String seoulEnvURL ="http://openapi.seoul.go.kr:8088/"+keysValue[1]+"/json/"+
				keysValue[0]+"/"+amount+"/"+keysValue[2]+keysValue[3];  
		System.out.println(seoulEnvURL);
		return seoulEnvURL;
	}
	

	public String processEnvironmentData(Map<String,Object> data, JSONObject jo){
		String[] keys = {"serviceName","keyValue","dateValue","envType","timeValue"};
		String[] keysValue = new String[]{"","","","",""};
		Set<String> dataKeyNames = data.keySet();
		Iterator<String> it = dataKeyNames.iterator();  
		while(it.hasNext()){ 
			String tmp = it.next();
			for(int i=0; i<keys.length; i++){
				if(keys[i].equals(tmp)){
					keysValue[i] = String.valueOf(data.get(tmp));
				}
			} 
		}
		
		String result = "{\"row\":[";
		
		JSONObject root = (JSONObject) jo.get(keysValue[0]);
		JSONArray row = (JSONArray) root.get("row");
		
		for(int i=0; i< row.size(); i++){
			JSONObject obj = (JSONObject) row.get(i);
			result += "{\"MSRSTE_NM\":\""+obj.get("MSRSTE_NM").toString() +"\",";
			result += "\""+keysValue[3]+"\":\""+obj.get(keysValue[3]).toString()+"\"},";
		}

		result = result.substring(0,result.length()-1);
		result +="]}";  
		System.out.println(result);
		return result;
	}
}
