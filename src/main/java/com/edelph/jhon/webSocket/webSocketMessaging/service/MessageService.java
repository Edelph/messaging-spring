package com.edelph.jhon.webSocket.webSocketMessaging.service;

import com.edelph.jhon.webSocket.webSocketMessaging.DTO.ChatMessage;
import com.edelph.jhon.webSocket.webSocketMessaging.DTO.Conversation;
import com.edelph.jhon.webSocket.webSocketMessaging.chat.Mssg;
import com.edelph.jhon.webSocket.webSocketMessaging.entity.Message;
import com.edelph.jhon.webSocket.webSocketMessaging.entity.UserClient;
import com.edelph.jhon.webSocket.webSocketMessaging.repository.MessageRepository;
import com.edelph.jhon.webSocket.webSocketMessaging.repository.UserClientRepository;
import com.edelph.jhon.webSocket.webSocketMessaging.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserClientRepository userClientRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;


    @Autowired
    public MessageService(SimpMessagingTemplate simpMessagingTemplate, MessageRepository messageRepository, UserClientRepository userClientRepository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageRepository = messageRepository;
        this.userClientRepository = userClientRepository;
    }

    public void sendAll(Message message){
        simpMessagingTemplate.convertAndSend(Util.URL_RECEIVER+"/all", message);
    }
    public boolean sendAll(ChatMessage message){
        Message mes = getMessage(message);
        if(mes!= null){
            saveMessage(mes);
            simpMessagingTemplate.convertAndSend(Util.URL_SENDER_DESTINATION_ALL, message);
            return true;
        }
        return false;
    }

    private void saveMessage(Message message){
        messageRepository.save(message);
    }
    private void deleteMessage(Message message){
        messageRepository.delete(message);
    }
    private Message getMessage(ChatMessage message){
        Optional<UserClient> senderopt = userClientRepository.findById(message.getSender());
        Optional<UserClient>  receiveropt = userClientRepository.findById(message.getReceiver());
        if(senderopt.isPresent() && receiveropt.isPresent()){
            Message m = new Message();
            m.setReceiver(receiveropt.get());
            m.setSender(senderopt.get());
            m.setContent(message.getMessage());
            return m;
        }
        return null;
    }

    public List<ChatMessage> getConversations(Conversation conversation){
         List<Message> messages = messageRepository.getMessages(conversation.getSender(),conversation.getReceiver());

         return messages.stream().map(m->{
             return ChatMessage.builder()
                     .message(m.getContent())
                     .sender(m.getSender().getCodeClient())
                     .receiver(m.getReceiver().getCodeClient())
                     .build();
         }).toList();
    }
}
