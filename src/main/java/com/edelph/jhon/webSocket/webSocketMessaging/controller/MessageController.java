package com.edelph.jhon.webSocket.webSocketMessaging.controller;

import com.edelph.jhon.webSocket.webSocketMessaging.DTO.ChatMessage;
import com.edelph.jhon.webSocket.webSocketMessaging.DTO.Conversation;
import com.edelph.jhon.webSocket.webSocketMessaging.chat.Mssg;
import com.edelph.jhon.webSocket.webSocketMessaging.entity.Message;
import com.edelph.jhon.webSocket.webSocketMessaging.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value="/sendMessage")
    public boolean sendMessage(@RequestBody ChatMessage message) {
        return messageService.sendAll(message);
    }
    @PostMapping(value="conversations")
    public List<ChatMessage> getMessages(@RequestBody Conversation consversation){
        return this.messageService.getConversations( consversation);
    }

}
