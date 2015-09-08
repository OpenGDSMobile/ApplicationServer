package com.openGDSMobileApplicationServer.webSocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.openGDSMobileApplicationServer.service.TableService;

public class AttrEditHandler extends TextWebSocketHandler{
	Logger log = LogManager.getLogger("org.springframework");
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		log.info("Disconnected:" + session.getId());
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
		//ats.editAttr();
		for (WebSocketSession s : users.values()) {
			s.sendMessage(message);
		}
	}
}
