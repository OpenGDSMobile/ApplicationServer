package com.openGDSMobileApplicationServer.webSocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	//	users.remove(session.getId());
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
		JSONObject jsonObj = new JSONObject(message.getPayload());
		
		ObjectMapper om = new ObjectMapper();
		if (searchUser(session.getId()) == null) {
			UserListVO ul = om.readValue(message.getPayload(), new TypeReference<UserListVO>(){});
			curUserList.add((UserListVO) ul);
			ts.insertData(jsonObj);
			//log.info(json);
		} else {
			List<EditValuesVO> editList = om.readValue(message.getPayload(), 
					om.getTypeFactory().constructCollectionType(List.class, EditValuesVO.class)); 
			log.info(editList);
			// Update Data.....
			//WebSocketSession ... sendMessage
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
