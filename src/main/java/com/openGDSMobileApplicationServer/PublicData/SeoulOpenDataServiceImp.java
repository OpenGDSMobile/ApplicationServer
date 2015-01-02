package com.openGDSMobileApplicationServer.PublicData;
 
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParser; 
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openGDSMobileApplicationServer.airQuality.AirQualityDataDAO;
 


@Service("Seoul")
public class SeoulOpenDataServiceImp implements PublicDataService{ 

	@Autowired
	@Qualifier("seoulPublicDAO")
	PublicDataDAO publicDataobj;   
	
	@Autowired
	@Qualifier("airQualityData")
	AirQualityDataDAO airQualityDataobj; 
	
	@Override
	public String requestPublicData(Map<String,Object> data) {  
		String serviceName = null; 
		Set<String> keys = data.keySet();
		Iterator<String> it = keys.iterator();
		String serviceURL = null; 
		while(it.hasNext()){
			String tmp = it.next();
			if(tmp.equals("serviceName")){
				serviceName = String.valueOf(data.get(tmp));
			}			
		} 
		if(serviceName.equals("TimeAverageAirQuality") 
				|| serviceName.equals("RealtimeRoadsideStation")){
			serviceURL=processEnvironmentURL(data);
		}  
		JsonParser jp =publicDataobj.getJSONPublicData(serviceURL); 
		try {
			return String.valueOf(jp.readValueAsTree());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		
		/* 
		JsonParser jp =publicDataobj.getJSONPublicData(seoulBaseURL); 
		try {
			String result = String.valueOf(jp.readValueAsTree());  
			if(keysValue[0].equals("TimeAverageAirQuality")) 
				airQualityDataobj.createMap(result); 
			return result; 
		} catch (Exception e) { 
			e.printStackTrace();
			return null;
		}
		*/	
	}
	
	public String processEnvironmentURL(Map<String,Object> data){ 
		String[] keys = {"serviceName","keyValue","dateValue","timeValue"};
		String[] keysValue = new String[]{"","","",""}; 
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
	
	
}
