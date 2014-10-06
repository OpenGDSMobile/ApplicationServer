package com.hmw.publicData;
 
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParser; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
 


@Service
public class PublicDataServiceImp implements PublicDataService{ 

	@Autowired
	@Qualifier("publicdao")
	PublicDataDAO publicDataobj; 
	
	String[] keysData = {"serviceName","keyValue","dateValue","timeValue"};
	String[] keysValue; 
	String amount = "";
	String seoulBaseURL="";
	@Override
	public String requestSeoulPublicData(Map<String,Object> data) {
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		keysValue=new String[]{"","","",""};
		Set<String> keys = data.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){ 
			String tmp = it.next();
			for(int i=0; i<keysData.length; i++){
				if(keysData[i].equals(tmp)){
					keysValue[i] = String.valueOf(data.get(tmp));
				}
			} 
		}
		keysValue[2] = keysValue[2].replaceAll(match, "");
		keysValue[3] = keysValue[3].replaceAll(match, "");
		amount = "1/100"; 
		seoulBaseURL ="http://openapi.seoul.go.kr:8088/"+keysValue[1]+"/json/"+
				keysValue[0]+"/"+amount+"/"+keysValue[2]+keysValue[3]; 
		
		System.out.println(seoulBaseURL);
		JsonParser jp =publicDataobj.getSeoulPublicData(seoulBaseURL); 
		try {
			String result = String.valueOf(jp.readValueAsTree()); 
			return result; 
		} catch (Exception e) { 
			e.printStackTrace();
			return null;
		}	
	}
}
