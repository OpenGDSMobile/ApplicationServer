package com.openGDSMobileApplicationServer.webSocket;

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

public class AttrInfoWebSocketHandler extends TextWebSocketHandler{

	Logger log = LogManager.getLogger("org.springframework");
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();	
	

	@Autowired
	@Qualifier("realtimeTable")
	TableService tableService;
	
	@Override	//WebSocket Connect : session id Save
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		super.afterConnectionEstablished(session);
		log.info("Connected:" + session.getId());
		users.put(session.getId(), session);
	}
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("sended : " + message.getPayload());    	
    	JSONObject searchObj = new JSONObject();
    	searchObj.put("session", session.getId());    	
    	if (tableService.searchTableCount(searchObj).equals("0")) {
    		searchObj = null;
    		searchObj = new JSONObject(message.getPayload());
        	searchObj.put("session", session.getId());
    		tableService.insertData(searchObj);
    	} else {
    		JSONArray obj = new JSONArray(message.getPayload());
			String subSubject = obj.getJSONObject(0).get("tableName").toString();
			searchObj.put("subject", subSubject);
			
			tableService.updateTable(obj);

			List<LinkedHashMap<String, Object>> sessionid= tableService.searchTableWhere(searchObj);
			
			for (WebSocketSession s : users.values()) {
				String id = s.getId();
				ListIterator<LinkedHashMap<String, Object>> sessionObjs = sessionid.listIterator();
				while(sessionObjs.hasNext()){
					LinkedHashMap<String, Object> sessionObj = sessionObjs.next();
					String curId = sessionObj.get("SESSION").toString();
					if (id.equals(curId)) {
						s.sendMessage(message);
					}
				}
			}
    	}
    }
    
	@Override	//WebSocket Close : session id delete / DB table content delete
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		log.info("Disconnected:" + session.getId());
		JSONObject searchColumn = new JSONObject();
		searchColumn.put("session", session.getId());
		tableService.deleteData(searchColumn);
		users.remove(session.getId());
	}
}
