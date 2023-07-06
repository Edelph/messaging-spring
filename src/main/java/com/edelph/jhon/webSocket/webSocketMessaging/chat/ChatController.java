package com.edelph.jhon.webSocket.webSocketMessaging.chat;

import com.edelph.jhon.webSocket.webSocketMessaging.DTO.ChatMessage;
import com.edelph.jhon.webSocket.webSocketMessaging.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;


    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    // mapping to /app/private.sendMessage
    @MessageMapping("private.sendMessage/{to}")
    public void sendToSpecificUser(@Payload Mssg chatMessage, @DestinationVariable String to) {
        // send message to specific user
        System.out.println(chatMessage.getContent());
        simpMessagingTemplate.convertAndSend(Util.URL_sendTo(to), chatMessage);
    }
    @MessageMapping("/app/new/users")
    public void newUser(@Payload ChatMessage chatMessage) {
        simpMessagingTemplate.convertAndSend(Util.URL_NEW_USER, chatMessage);
    }
}
