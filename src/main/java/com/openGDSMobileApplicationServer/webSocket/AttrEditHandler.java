package com.openGDSMobileApplicationServer.webSocket;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.openGDSMobileApplicationServer.service.TableService;

public class AttrEditHandler extends TextWebSocketHandler{
	Logger log = LogManager.getLogger("org.springframework");
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
	private List<UserListVO> curUserList = new ArrayList<UserListVO>();
	

	@Autowired
	@Qualifier("realtimeTable")
	TableService ts;
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		log.info("Disconnected:" + session.getId());
		JSONObject searchColumn = new JSONObject();
		searchColumn.put("session", session.getId());
		ts.deleteData(searchColumn);
		users.remove(session.getId());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		super.afterConnectionEstablished(session);
		log.info("Connected:" + session.getId());
		users.put(session.getId(), session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		log.info("send : " + message.getPayload());
		
		JSONObject searchColumn = new JSONObject();
		searchColumn.put("session", session.getId());
		if(ts.searchTableCount(searchColumn).equals("0")) {
			JSONObject jsonObj = new JSONObject(message.getPayload());
			jsonObj.put("session", session.getId());
			ts.insertData(jsonObj);
		} else {
			JSONArray jsonArr = new JSONArray(message.getPayload());
			String subject = jsonArr.getJSONObject(0).get("tableName").toString();
			searchColumn.put("subject", subject);
			//List<LinkedHashMap<String, Object>> resultList= ts.updateTable(jsonArr);
			log.info(jsonArr);
			List<LinkedHashMap<String, Object>> sessionid= ts.searchTableWhere(searchColumn);
			log.info(sessionid);
			/*for(int i=0; i<sessionid.size(); i++) {
				System.out.println(sessionid.get(i).get("session"));
				WebSocketSession s = users.get(sessionid.get(i).get("session").toString());
				s.sendMessage(message);
			}*/
			//ObjectMapper om = new ObjectMapper();
			//List<EditValuesVO> editList = om.readValue(message.getPayload(), 
			//		om.getTypeFactory().constructCollectionType(List.class, EditValuesVO.class)); 
			//log.info(editList);
			// Update Data.....
			//WebSocketSession ... sendMessage
			for (WebSocketSession s : users.values()) {
				s.sendMessage(message);
			}
		}
		
	//	for (WebSocketSession s : users.values()) {
	//		s.sendMessage(message);	
	//	}
	}
	
	protected UserListVO searchUser(String id){
		UserListVO findUser = null;
		for (UserListVO u : curUserList) {
			if(u.getWsId().equals(id)) {
				findUser = u;
				break;
			}
		}		
		return findUser;
	}
}
