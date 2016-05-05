package com.openGDSMobileApplicationServer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;


@Repository("OpenGDSMobileDAO")
public class OpenGDSMobileTableDAO extends EgovAbstractMapper {

	@Autowired
	@Qualifier("sqlSession-postgresql")
	SqlSessionTemplate sess;
	List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
	Logger log = LogManager.getLogger("ERROR");
	

	public List<LinkedHashMap<String, Object>> attributeSelectTableInfo(JSONObject tableName) {
		HashMap<String, Object> hashMapTableName = (HashMap<String, Object>) toMap(tableName);
		try{
			tableContentList = sess.selectList("OpenGDSMobileTable.Attr.SelectTable", hashMapTableName);
		}catch(Exception e){
			log.error(e);
			tableContentList = null;
		}		
		return tableContentList;
	}

	public int attributeUpdateTable(JSONObject updateContent) {
		HashMap<String, Object> hashMapTableName = (HashMap<String, Object>) toMap(updateContent);
		try{
			return sess.update("OpenGDSMobileTable.Attr.UpdateTable", hashMapTableName);
		}catch(Exception e){
			tableContentList = null;
		}		
		return 0;
	}

	public List<LinkedHashMap<String, Object>> realtimeSelectTableInfo() {
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		try{
			tableContentList = sess.selectList("OpenGDSMobileTable.RealTime.SelectTable");
		}catch(Exception e){
			tableContentList = null;
		}
		return tableContentList;
	}
	public List<LinkedHashMap<String, Object>> realtimeSelectTableInfo(JSONObject type) {
		HashMap<String, Object> hashMapTableName = (HashMap<String, Object>) toMap(type);		
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		log.info(type);
		try{
			tableContentList = sess.selectList("OpenGDSMobileTable.RealTime.SelectTable-columns", hashMapTableName);
		}catch(Exception e){
			tableContentList = null;
		}
		return tableContentList;
	}
	public HashMap<String, Object> realtimeSelectTableInfoCount(JSONObject type) {
		HashMap<String, Object> hashMapTableName = (HashMap<String, Object>) toMap(type);
		try{
			return sess.selectOne("OpenGDSMobileTable.RealTime.CountSession", hashMapTableName);
		}catch(Exception e){
			return null;
		}
		
	}
	public List<LinkedHashMap<String, Object>> realtimeSelectTableWhereSubject(JSONObject type) {
		HashMap<String, Object> hashMapTableName = (HashMap<String, Object>) toMap(type);		
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		log.info(type);
		try{
			tableContentList = sess.selectList("OpenGDSMobileTable.RealTime.SelectTable-Session-Subject", hashMapTableName);
		}catch(Exception e){
			tableContentList = null;
		}
		return tableContentList;
		
	}
	
	public int realtimeInsertTableInfo(JSONObject type) {
		HashMap<String, Object> hashMapTableName = (HashMap<String, Object>) toMap(type);
		System.out.println(type);
		try{
			return sess.insert("OpenGDSMobileTable.RealTime.InsertData", hashMapTableName);
		} catch(Exception e){
			if (e.getCause().getMessage().contains("duplicate key value violates unique constraint")) {
				log.debug(e.getCause().getMessage());
				return -1;	
			}
			return 0;
		}
	}
	public int realtimeDeleteTableInfo(JSONObject type) {
		HashMap<String, Object> hashMapTableName = (HashMap<String, Object>) toMap(type);	
		return sess.delete("OpenGDSMobileTable.RealTime.DeleteData", hashMapTableName);
	}
	
	
    public static Map<String, Object> getMap(JSONObject object, String key) throws JSONException {
        return toMap(object.getJSONObject(key));
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        Iterator<?> keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }
    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }
    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else if (json instanceof JSONObject) {
            return toMap((JSONObject) json);
        } else if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        } else {
            return json;
        }
    }


}
