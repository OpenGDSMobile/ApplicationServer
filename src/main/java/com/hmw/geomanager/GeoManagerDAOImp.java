package com.hmw.geomanager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTLayer;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.stereotype.Repository;


@Repository("geodao")
public class GeoManagerDAOImp implements GeoManagerDAO {

	//static String RESTURL = "http://113.198.80.60:8080/geoserver";
	static String RESTURL = "http://113.198.80.9/geoserver";
	static String RESTUSER = "admin";
	static String RESTPW = "geoserver";
	
	GeoServerRESTPublisher publisher; 
	GeoServerRESTReader reader ;
	
	JsonFactory jsonfactory;
	ObjectMapper mapper;
	
	//String path ="http://192.168.0.9/geoserver/wfs?service=WFS&version=1.1.0&request=GetFeature&typeNames=korea:Seoul_dong&outputFormat=application/json&srsname=EPSG:3857&bbox=14167144.570487704,4539747.983913189,14206280.328969715,4578883.742395199,EPSG";
	String path = RESTURL+"/wfs?service=WFS&version=1.1.0&request=GetFeature&typeNames=opengds:Seoul_dong&outputFormat=application/json";
	GeoManagerDAOImp() throws MalformedURLException{
		super();
		publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
		reader= new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
		

		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		mapper.setVisibility(JsonMethod.FIELD,Visibility.ANY);
		jsonfactory = new JsonFactory();
		jsonfactory.setCodec(mapper);
	}
			
	@Override
	public boolean geoserverCreateWorkspace(String name) {
		// TODO Auto-generated method stub
		return publisher.createWorkspace(name);
	}
	
	@Override	
	public JsonParser geoserverLoadVector(String workspace, String name){
		try {
			URL url = new URL(path);
			JsonParser jsonParser = jsonfactory.createJsonParser(url);
			return jsonParser; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	//	return reader.getLayer("korea", name);
	}

}
