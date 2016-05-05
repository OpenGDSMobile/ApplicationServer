package com.openGDSMobileApplicationServer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.openGDSMobileApplicationServer.webSocket.AttrInfoWebSocketHandler;

@Configuration
@EnableWebSocket
@EnableWebMvc
public class ControllerConfiguration extends WebMvcConfigurerAdapter implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new AttrInfoWebSocketHandler(), "/attr-ws.do");
		System.out.println("test");
	}

}
