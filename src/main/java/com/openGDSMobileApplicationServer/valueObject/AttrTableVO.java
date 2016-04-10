package com.openGDSMobileApplicationServer.valueObject;

public class AttrTableVO {
	String tableName;
	
	public AttrTableVO() {
		super();
	}
	public AttrTableVO(String tableName) {
		super();
		this.tableName = tableName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Override
	public String toString() {
		return "AttrTableVO [tableName=" + tableName + "]";
	}
}
