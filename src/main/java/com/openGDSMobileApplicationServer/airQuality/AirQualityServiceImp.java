package com.openGDSMobileApplicationServer.airQuality;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service; 


@Service
public class AirQualityServiceImp implements AirQualityService{
	
	@Autowired
	@Qualifier("airQualityData")
	AirQualityDataDAO airQualityDataobj; 
	
	@Override
	public Object requestAirQualityMapCreate(String JSONData) { 
	    airQualityDataobj.createMap(JSONData);
		return "OK";
	}
}
