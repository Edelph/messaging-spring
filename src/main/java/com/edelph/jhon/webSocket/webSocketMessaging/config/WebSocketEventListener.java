package com.edelph.jhon.webSocket.webSocketMessaging.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageTemplates;

//    @EventListener
//    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//        log.info("User Disconnected");
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        if(username != null) {
//            log.info("User Disconnected : {} " , username);
//            ChatMessage chatMessage = ChatMessage.builder()
//                    .sender(username)
//                    .build();
//            messageTemplates.convertAndSend("/topic/public", chatMessage);
//        }
//    }
}
