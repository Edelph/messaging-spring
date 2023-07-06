package com.edelph.jhon.webSocket.webSocketMessaging.service;

import com.edelph.jhon.webSocket.webSocketMessaging.entity.UserClient;
import com.edelph.jhon.webSocket.webSocketMessaging.repository.UserClientRepository;
import com.edelph.jhon.webSocket.webSocketMessaging.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserClientRepository userClientRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;


    @Autowired
    public UserService(
            UserClientRepository userClientRepository,
            SimpMessagingTemplate simpMessagingTemplate
    ) {
        this.userClientRepository = userClientRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public UserClient login(String username, String password, HttpSession session) {
        List<UserClient> userClient =  userClientRepository.login(username, password);
        System.out.println(userClient.size());
        if(userClient.size() == 1){
            newUser(userClient.get(0));
            session.setAttribute(userClient.get(0).getCodeClient().toString(), userClient);
            return userClient.get(0);
        }
        return null;
    }

    public void logout(UserClient user, HttpSession session) {
        user.setOnLine(false);
        userClientRepository.save(user);
        session.removeAttribute(user.getCodeClient().toString());
    }

    public Optional<UserClient> findUser(UUID userUID) {
        return userClientRepository.findById(userUID);
    }

    public UserClient saveUser(UserClient userClient){
        return userClientRepository.save(userClient);
    }
    public UserClient saveUser(String code, UserClient userClient){
        userClient.setCodeClient(UUID.randomUUID());
        if(code.equals(userClient.getCodeClient().toString())
                &&
                userClientRepository.findById(UUID.fromString(code)).isPresent()
        ){
            userClient.setCodeClient(UUID.fromString(code));
        }
        return userClientRepository.save(userClient);
    }

    public void deleteUser(UserClient userClient){
        userClientRepository.delete(userClient);
    }

    public Optional<List<UserClient>> findAll(){
        return Optional.of(userClientRepository.findAll());
    }
    public Optional<List<UserClient>> findByName(String nameParam){
        return Optional.of(userClientRepository.finByName(nameParam));
    }
    public void newUser(UserClient userClient){
        userClient.setOnLine(true);
        userClientRepository.save(userClient);
        simpMessagingTemplate.convertAndSend(Util.URL_NEW_USER, userClient);
    }
}
