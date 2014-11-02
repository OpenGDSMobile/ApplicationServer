package com.openGDSMobile.PublicOpenData;
 
import org.codehaus.jackson.JsonParser;

public interface PublicDataDAO {
	
	JsonParser getSeoulPublicData(String path);

}
