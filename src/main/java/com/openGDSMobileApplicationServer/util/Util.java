package com.openGDSMobileApplicationServer.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class Util {
	
	public static HashMap<String, Object> convertJsonToObject(String json) 
			throws IOException{
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		try {
			InputStream inputStream = new ByteArrayInputStream(json.getBytes("UTF-8"));
			InputStreamReader isr = new InputStreamReader(inputStream,"UTF-8");
			JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(isr);
			Iterator<?> keys = jsonObject.keySet().iterator();
			
			while(keys.hasNext()){
				String key = (String)keys.next();
				String value = (String) jsonObject.get(key);
				hashMap.put(key, value);				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashMap;
	} 
}