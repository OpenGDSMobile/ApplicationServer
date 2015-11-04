package com.openGDSMobileApplicationServer.webSocket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
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
	
	@Override	//WebSocket Close : session id delete / DB table content delete
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		log.info("Disconnected:" + session.getId());
		JSONObject searchColumn = new JSONObject();
		searchColumn.put("session", session.getId());
		ts.deleteData(searchColumn);
		users.remove(session.getId());
	}

	@Override	//WebSocket Connect : session id Save
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		super.afterConnectionEstablished(session);
		log.info("Connected:" + session.getId());
		System.out.println(session.getHandshakeHeaders());
		users.put(session.getId(), session);
	}

	@Override	// client send message 
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
			
			ts.updateTable(jsonArr);
			
			List<LinkedHashMap<String, Object>> sessionid= ts.searchTableWhere(searchColumn);
			log.info(sessionid);
			for (WebSocketSession s : users.values()) {
				String id = s.getId();
				ListIterator<LinkedHashMap<String, Object>> listItr = sessionid.listIterator();
				while(listItr.hasNext()){
					LinkedHashMap<String, Object> tmp = listItr.next();
					String searchId = tmp.get("session").toString().trim();
					System.out.println(searchId + "eee");
					if (id.equals(searchId)) {
						s.sendMessage(message);
						break;
					}
				}
			}
		}
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
