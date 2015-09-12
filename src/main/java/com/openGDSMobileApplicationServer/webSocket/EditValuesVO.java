package com.openGDSMobileApplicationServer.webSocket;

public class EditValuesVO {
	String tableName;
	String column;
	String srcData;
	String dstData;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getSrcData() {
		return srcData;
	}
	public void setSrcData(String srcData) {
		this.srcData = srcData;
	}
	public String getDstData() {
		return dstData;
	}
	public void setDstData(String dstData) {
		this.dstData = dstData;
	}
	
	@Override
	public String toString() {
		return "EditValuesVO [tableName=" + tableName + ", column=" + column
				+ ", srcData=" + srcData + ", dstData=" + dstData + "]";
	}
}
