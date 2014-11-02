package com.openGDSMobile.PublicOpenData;
 
import java.net.URL;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.stereotype.Repository;


@Repository("publicdao") 
public class PublicDataDAOImp implements PublicDataDAO {

	JsonFactory jsonfactory;
	ObjectMapper mapper;
	 
	PublicDataDAOImp(){
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		mapper.setVisibility(JsonMethod.FIELD,Visibility.ANY);
		jsonfactory = new JsonFactory();
		jsonfactory.setCodec(mapper);
	}
	
	@Override
	public JsonParser getSeoulPublicData(String path) {
		try {
			URL url = new URL(path);
			JsonParser jsonParser = jsonfactory.createJsonParser(url);
			return jsonParser; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}

}
