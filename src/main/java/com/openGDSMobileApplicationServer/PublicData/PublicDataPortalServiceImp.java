package com.openGDSMobileApplicationServer.PublicData;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set; 

import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service; 


@Service("Portal")
public class PublicDataPortalServiceImp implements PublicDataService {

	@Autowired
	@Qualifier("PortalDAO")
	PublicDataDAO publicDataobj;
	 
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
		if(serviceName.equals("ArpltnInforInqireSvc")){
			serviceURL=processEnvironmentURL(data);
			Document doc = publicDataobj.getXMLPublicData(serviceURL);
			result = processEnvironmentData(data, doc);
			System.out.println(result);
			return result;  
		}    
		
		return null;
	}  
	public String processEnvironmentURL(Map<String,Object> data){ 
		String[] keys = {"provider","serviceName","areaType","keyValue","envType"};
		String[] keyValue=new String[]{"","","","",""};
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
		keyValue[1]+"/getCtprvnRltmMesureDnsty?sidoName="+keyValue[2]+
		"&numOfRows=100&ServiceKey="+keyValue[3];
		System.out.println(portalEnvURL); 
		return portalEnvURL;
	}
	public String processEnvironmentData(Map<String,Object> data, Document doc){
		String[] keys = {"provider","serviceName","areaType","keyValue","envType"};
		String[] keyValue=new String[]{"","","","",""};
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
						if(node.getName().equals(keyValue[4])){
							result +="\""+keyValue[4]+"\":\""+node.getValue().trim()+"\"},";
							
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
