package com.edelph.jhon.webSocket.webSocketMessaging.config;

import com.edelph.jhon.webSocket.webSocketMessaging.util.Util;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(Util.ENDPOINT).
                setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(Util.PREFIX_SENDER_DESTINATION);
        registry.enableSimpleBroker(Util.URL_RECEIVER,Util.URL_NEW_USER,Util.URL_SENDER_DESTINATION_ALL, Util.URL_SENDER_DESTINATION);
    }
}
