package com.openGDSMobileApplicationServer.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set; 

import org.jdom2.Document;
import org.jdom2.Element;
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
	 
	//Environment
		String url=""; 
	
	@Override
	public Object requestPublicData(Map<String, Object> data) { 
		String serviceName = null; 
		Set<String> keys = data.keySet();
		Iterator<String> it = keys.iterator();
		String serviceURL, result = null; 
		while(it.hasNext()){
			String tmp = it.next();
			if(tmp.equals("serviceName")){
				serviceName = String.valueOf(data.get(tmp));
			}			
		}  
		if(serviceName.equals("NuclearPowerPlantRealtimeLevelofRadiation")){
			serviceURL=processNuclearPowerPlantRealtimeLevelofRadiationServiceURL(data);
			Document doc = publicDataobj.getXMLPublicData(serviceURL);
			result = processNuclearPowerPlantRealtimeLevelofRadiationServiceData(data, doc);
			return result;  
		}   
		else if(serviceName.equals("GreenGasEmissionReport")){
			serviceURL=processGreenGasEmissionReportServiceURL(data);
			Document doc = publicDataobj.getXMLPublicData(serviceURL);
			result = processGreenGasEmissionReportServiceData(data, doc);
			return result;  
		}
		else if(serviceName.equals("ArpltnInforInqireSvc")){
			serviceURL=processEnvironmentURL(data);
			Document doc = publicDataobj.getXMLPublicData(serviceURL);
			result = processEnvironmentData(data, doc);
			return result;			
		}
		return null;
	}  
	
	//Nuclear Power Plant Realtime Level of Radiation Request URL
	public String processNuclearPowerPlantRealtimeLevelofRadiationServiceURL(Map<String,Object> data){ 
		String[] keys = {"NPP_name","keyValue"};
		String[] keyValue=new String[]{"", ""};
		Set<String> dataKeyNames = data.keySet();
		Iterator<String> it = dataKeyNames.iterator(); 
		while(it.hasNext()){ 
			String tmp = it.next();
			for(int i=0; i<keyValue.length; i++){ 
				if(keys[i].equals(tmp)){
					keyValue[i] = String.valueOf(data.get(tmp));
				}
			} 
		}  
		String portalEnvURL = "http://www.khnp.co.kr/environ/service/realtime/radiorate?genName="
		+keyValue[0]+"&ServiceKey="+keyValue[1];
		System.out.println(portalEnvURL); 
		return portalEnvURL;
	}
	
	//Nuclear Power Plant Realtime Level of Radiation Data(XML) Parsing :)
	public String processNuclearPowerPlantRealtimeLevelofRadiationServiceData(Map<String,Object> data, Document doc){
		Element root = doc.getRootElement(); 
		String result = "{\"row\":[";
		
		List<Element> bodyNodes = root.getChildren("body");
		for(Element bodyNode : bodyNodes){
			List<Element> itemsNodes = bodyNode.getChildren("items");
			for(Element itemNode : itemsNodes){
				List<Element> itemValues = itemNode.getChildren("item");
				for(Element itemValue : itemValues){
					List<Element> nodes = itemValue.getChildren();
					for(Element node : nodes){
						if(node.getName().equals("expl"))
							result +="{\"expl\":\"" + node.getValue() + "\",";
						if(node.getName().equals("name"))
							result +="\"name\":\"" + node.getValue() + "\",";
						if(node.getName().equals("time"))
							result +="\"time\":\"" + node.getValue() + "\",";
						if(node.getName().equals("value"))
							result +="\"value\":\"" + node.getValue() + "\"},";
					}
				}
			}
		}
		
		result = result.substring(0,result.length()-1);
		result +="]}";  
		System.out.println(result);
		return result;
	}
	
	//Green Gas Emission Report Request URL
	public String processGreenGasEmissionReportServiceURL(Map<String,Object> data){ 
		String[] keys = {"startDate","endDate","keyValue"};
		String[] keyValue=new String[]{"","",""};
		Set<String> dataKeyNames = data.keySet();
		Iterator<String> it = dataKeyNames.iterator(); 
		while(it.hasNext()){ 
			String tmp = it.next();
			for(int i=0; i<keyValue.length; i++){ 
				if(keys[i].equals(tmp)){
					keyValue[i] = String.valueOf(data.get(tmp));
				}
			} 
		}  
		
		//http://www.kdhc.co.kr/openapi-data/service/kdhcCarbon/carbon?startDate=201007&endDate=201009&pageNo=1&rowOfNums=10&serviceKey=인증키
		String portalEnvURL = "http://www.kdhc.co.kr/openapi-data/service/kdhcCarbon/carbon?startDate="
							  +keyValue[0]+"&endDate="+keyValue[1]+"&ServiceKey="+keyValue[2]+"&numOfRows=100";
		System.out.println(portalEnvURL);
		return portalEnvURL;
	}

	//Green Gas Emission Report Data(XML) Parsing :)
	public String processGreenGasEmissionReportServiceData(Map<String,Object> data, Document doc){
		Element root = doc.getRootElement(); 
		String result = "{\"row\":[";

		List<Element> bodyNodes = root.getChildren("body");
		for(Element bodyNode : bodyNodes){
			List<Element> itemsNodes = bodyNode.getChildren("items");
			for(Element itemNode : itemsNodes){
				List<Element> itemValues = itemNode.getChildren("item");
				for(Element itemValue : itemValues){
					List<Element> nodes = itemValue.getChildren();
					for(Element node : nodes){
						if(node.getName().equals("basYm"))
							result +="{\"basYm\":\"" + node.getValue() + "\",";
						if(node.getName().equals("brNm"))
							result +="\"brNm\":\"" + node.getValue() + "\",";
						if(node.getName().equals("tco2eqUnit"))
							result +="\"tco2eqUnit\":\"" + node.getValue() + "\",";
						if(node.getName().equals("totEmTco2eq"))
							result +="\"totEmTco2eq\":\"" + node.getValue() + "\"},";
					}
				}
			}
		}

		result = result.substring(0,result.length()-1);
		result +="]}";  
		System.out.println(result);
		return result;
	}
	
	
	
	
	
	public String processEnvironmentURL(Map<String,Object> data){ 
		String[] keys = {"serviceName","areaType","keyValue","envType"};
		String[] keyValue=new String[]{"","","",""};
		Set<String> dataKeyNames = data.keySet();
		Iterator<String> it = dataKeyNames.iterator(); 
		while(it.hasNext()){ 
			String tmp = it.next();
			for(int i=0; i<keyValue.length; i++){ 
				if(keys[i].equals(tmp)){
					keyValue[i] = String.valueOf(data.get(tmp));
				}
			} 
		}  
		String portalEnvURL = "http://openapi.airkorea.or.kr/openapi/services/rest/"+ 
		keyValue[0]+"/getCtprvnRltmMesureDnsty?sidoName="+keyValue[1]+
		"&numOfRows=100&ServiceKey="+keyValue[2];
		System.out.println(portalEnvURL); 
		return portalEnvURL;
	}
	
	
	
	
	public String processEnvironmentData(Map<String,Object> data, Document doc){
		String[] keys = {"serviceName","areaType","keyValue","envType"};
		String[] keyValue=new String[]{"","","",""};
		Set<String> dataKeyNames = data.keySet();
		Iterator<String> it = dataKeyNames.iterator(); 
		while(it.hasNext()){ 
			String tmp = it.next();
			for(int i=0; i<keyValue.length; i++){ 
				if(keys[i].equals(tmp)){
					keyValue[i] = String.valueOf(data.get(tmp));
				}
			} 
		}  
		Element root = doc.getRootElement(); 
		String result = "{\"row\":[";
		
		List<Element> bodyNodes = root.getChildren("body");
		for(Element bodyNode : bodyNodes){
			List<Element> itemsNodes = bodyNode.getChildren("items");
			for(Element itemNode : itemsNodes){
				List<Element> itemValues = itemNode.getChildren("item");
				for(Element itemValue : itemValues){
					List<Element> nodes = itemValue.getChildren();
					for(Element node : nodes){
						if(node.getName().equals("stationName")){
							result +="{\"stationName\":\""+node.getValue()+"\",";
						}
						if(node.getName().equals(keyValue[3])){
							result +="\""+keyValue[3]+"\":\""+node.getValue().trim()+"\"},";
							
						}
					}
				}
			}
		}
		result = result.substring(0,result.length()-1);
		result +="]}";  
		return result;
	}

}
