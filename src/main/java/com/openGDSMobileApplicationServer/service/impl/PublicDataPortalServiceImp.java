package com.openGDSMobileApplicationServer.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service; 

import com.openGDSMobileApplicationServer.service.PublicData;
import com.openGDSMobileApplicationServer.service.PublicDataService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("Portal")
public class PublicDataPortalServiceImp extends EgovAbstractServiceImpl implements PublicDataService {

	@Autowired
	@Qualifier("PortalDAO")
	PublicData publicDataobj;
	
	private String serviceName = null;
	private String serviceURL = null;
	private String[] resultJSONKeys = null;
	
	Logger log = LogManager.getLogger("org.springframework");
	 	   
	@Override
	public JSONObject requestPublicData(JSONObject data) {
		
		Iterator<?> it = data.keySet().iterator();
		String baseURL = null;
		log.info(data);
		while(it.hasNext()){
			String tmp = (String) it.next();
			if(tmp.equals("serviceName")){
				serviceName = String.valueOf(data.get(tmp));
			}			
		}  
		/**
		 * JSON Object
		 * {serviceName:"NuclearPowerPlantRealtimeLevelofRadiation", genName: ??, ServiceKey: ??}
		 **/
		if(serviceName.equals("NuclearPowerPlantRealtimeLevelofRadiation")){
			baseURL = "http://www.khnp.co.kr/environ/service/realtime/radiorate?";
			
			//Nuclear Power Plant Realtime Level of Radiation Request URL
			serviceURL = processServiceURL(data, baseURL);			
			Document doc = publicDataobj.getXMLPublicData(serviceURL);
			log.info(baseURL);
			resultJSONKeys = new String[]{"expl", "name", "time", "value"};
			return processXmlbyPublicData(doc, resultJSONKeys);  
		}
		/**
		 * JSON Object
		 * {serviceName:"GreenGasEmissionReport", startDate: ??, endDate: ??, ServiceKey: ??}
		 **/
		else if(serviceName.equals("GreenGasEmissionReport")){
			baseURL = "http://www.kdhc.co.kr/openapi-data/service/kdhcCarbon/carbon?";
			//Green Gas Emission Report Request URL
			serviceURL = processServiceURL(data, baseURL);			
			Document doc = publicDataobj.getXMLPublicData(serviceURL);
			log.info(baseURL);
			resultJSONKeys = new String[]{"basYm", "brNm", "tco2eqUnit", "totEmTco2eq"};
			return processXmlbyPublicData(doc, resultJSONKeys);  
		}
		/**
		 * JSON Object
		 * {serviceName:"ArpltnInforInqireSvc", sidoName: ??, numOfRows: ??, ServiceKey: ??,
		 *  envType: ??}
		 **/
		else if(serviceName.equals("ArpltnInforInqireSvc")){
			baseURL = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?";			
			serviceURL=processServiceURL(data, baseURL);
			log.info(baseURL);
			Document doc = publicDataobj.getXMLPublicData(serviceURL);
			
			resultJSONKeys = new String[]{"stationName", (String) data.get("envType") };
			return processXmlbyPublicData(doc, resultJSONKeys); 
		}
		/**
		 * JSON Object
		 * {serviceName:"PublicStandardDataSvc", serviceIdentity: ??, serviceKey: ??, s_page: ??, s_list: ??, type: ??, numOfRows: ??, pageNo: ??}
		 * Traditional national markets, Children Protection Zone, City Park, Public Toilets, Parking lot, Social Enterprise, CCTV, Unattended complaints dispenser,
		 * Free Wi-Fi, Daycare Center, Library
		 **/
		else if(serviceName.equals("PublicStandardDataSvc")){
			
			baseURL = "http://api.data.go.kr/openapi/" + (String) data.get("serviceIdentity") + "?";			
	
			serviceURL=processServiceURL(data, baseURL);

			JSONObject result = publicDataobj.getJSONPublicData(serviceURL);
				
			return result;
		}		
		return null;
	}  
	
	public String processServiceURL(JSONObject data,String baseURL){
		String url = baseURL;
		Iterator<?> it = data.keySet().iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			if (key.equals("serviceName") || key.equals("serviceIdentity")){
				continue;
			}
			url += key + "=" + data.get(key) + "&";
		}
		log.info(url);
		return url;
	}
	
	public JSONObject processXmlbyPublicData(Document src, String[] keys){
		Element root = src.getRootElement();
		JSONObject result = new JSONObject();
		JSONArray list = new JSONArray();
		
		List<Element> bodyNodes = root.getChildren("body");
		for(Element bodyNode : bodyNodes){
			List<Element> itemsNodes = bodyNode.getChildren("items");
			for(Element itemNode : itemsNodes){
				List<Element> contents = itemNode.getChildren("item");
				for (int i=0; i<contents.size(); i++){
					JSONObject jsonObj = new JSONObject();
					Element content = contents.get(i);
					for (int j=0; j<keys.length; j++){
						String contentKey = keys[j];
						String contentValue = content.getChildText(keys[j]);
						jsonObj.put(contentKey, contentValue);
					}
					list.put(jsonObj);
				}
			}
		}
		result.put("row", list);
		
		log.info(result);
		return result;
	}
	
}
