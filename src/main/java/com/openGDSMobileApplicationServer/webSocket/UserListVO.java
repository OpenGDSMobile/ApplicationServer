package com.openGDSMobileApplicationServer.webSocket;

public class UserListVO {
	String wsId;
	String subject;
	String userId;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWsId() {
		return wsId;
	}
	public void setWsId(String wsId) {
		this.wsId = wsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserListVO [subject=" + subject + ", userId=" + userId + "]";
	}

}
