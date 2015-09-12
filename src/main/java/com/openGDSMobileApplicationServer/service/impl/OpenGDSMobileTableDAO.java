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

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;


@Repository("OpenGDSMobileDAO")
public class OpenGDSMobileTableDAO extends EgovAbstractMapper {

	@Autowired
	@Qualifier("sqlSession-postgresql")
	SqlSessionTemplate sess;
	Logger log = LogManager.getLogger("ERROR");
	
	
	public List<LinkedHashMap<String, Object>> attributeSelectTableInfo(JSONObject tableName) {

		HashMap<String, Object> hashMapTableName = (HashMap<String, Object>) toMap(tableName);
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		try{
			tableContentList = sess.selectList("OpenGDSMobileMapper.attrTable", hashMapTableName);
		}catch(Exception e){

			tableContentList = null;
		}		
		return tableContentList;
	}

	public List<LinkedHashMap<String, Object>> realtimeSelectTableInfo(JSONObject type) {
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		log.info(type);
		try{
			tableContentList = sess.selectList("OpenGDSMobileMapper.realTimeTableColum", type);
		}catch(Exception e){
			tableContentList = null;
		}
		return tableContentList;
	}
	public List<LinkedHashMap<String, Object>> realtimeSelectTableInfo() {
		List<LinkedHashMap<String, Object>> tableContentList = new ArrayList<LinkedHashMap<String,Object>>();
		try{
			tableContentList = sess.selectList("OpenGDSMobileMapper.realtimeTable");
		}catch(Exception e){
			tableContentList = null;
		}
		return tableContentList;
	}
	
	public int realtimeInsertTableInfo(JSONObject type) {
		try{
			return sess.insert("OpenGDSMobileMapper.realTimeTableInsert", type);
		} catch(Exception e){
			if (e.getCause().getMessage().contains("duplicate key value violates unique constraint")) {
				log.debug(e.getCause().getMessage());
				return -1;	
			}
			return 0;
		}
	}
	public int realtimeDeleteTableInfo(JSONObject type) {
		return sess.delete("OpenGDSMobileMapper.realTimeTableDelete", type);
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
