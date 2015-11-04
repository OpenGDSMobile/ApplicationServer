package com.openGDSMobileApplicationServer.webSocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{

	@Override
	public void afterHandshake(ServerHttpRequest request, 
			ServerHttpResponse response, 
			WebSocketHandler wsHandler,
			Exception ex) {
		// TODO Auto-generated method stub
		super.afterHandshake(request, response, wsHandler, ex);
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest arg0, 
			ServerHttpResponse arg1, 
			WebSocketHandler arg2,
			Map<String, Object> arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(arg0.getHeaders().get("Sec-WebSocket-Extensions"));
		arg0.getHeaders().remove("Sec-WebSocket-Extensions");
		return super.beforeHandshake(arg0, arg1, arg2, arg3);
	}
	
	

}
