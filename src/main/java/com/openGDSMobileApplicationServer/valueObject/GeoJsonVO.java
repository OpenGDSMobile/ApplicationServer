package com.openGDSMobileApplicationServer.valueObject;

public class GeoJsonVO {
	String jsonName;

	public GeoJsonVO() {
		super();
	}

	public GeoJsonVO(String jsonName) {
		super();
		this.jsonName = jsonName;
	}

	public String getJsonName() {
		return jsonName;
	}

	public void setJsonName(String jsonName) {
		this.jsonName = jsonName;
	}


	@Override
	public String toString() {
		return "GeoJsonVO [jsonName=" + jsonName + "]";
	}
	
}
