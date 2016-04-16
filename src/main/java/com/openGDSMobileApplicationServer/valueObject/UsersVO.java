package com.openGDSMobileApplicationServer.valueObject;

public class UsersVO {
	String column;
	String userid;
	String subject;
	public UsersVO() {
		super();
	}
	public UsersVO(String column, String userId, String subject) {
		super();
		this.column = column;
		this.userid = userId;
		this.subject = subject;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "UsersVO [column=" + column + ", userid=" + userid
				+ ", subject=" + subject + "]";
	}
}
