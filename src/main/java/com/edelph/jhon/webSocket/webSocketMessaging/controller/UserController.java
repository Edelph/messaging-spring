package com.edelph.jhon.webSocket.webSocketMessaging.controller;

import com.edelph.jhon.webSocket.webSocketMessaging.DTO.UserDTO;
import com.edelph.jhon.webSocket.webSocketMessaging.entity.UserClient;
import com.edelph.jhon.webSocket.webSocketMessaging.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "users", consumes = "application/json", produces = "application/json")
    public UserClient createUser(@RequestBody UserClient userClient) {
        return userService.saveUser(userClient);
    }

    @PutMapping(value = "users/{code}", consumes = "application/json", produces = "application/json")
    public UserClient updateUser(@RequestBody UserClient userClient,@PathVariable String code) {
        return userService.saveUser(code,userClient);
    }

    @GetMapping("users/{codeClient}")
    public UserClient getUser(@PathVariable String codeClient) {
        return userService.findUser(UUID.fromString(codeClient)).
                orElseThrow(() -> new RuntimeException("User not found"));
    }
    @GetMapping("users/search/{name}")
    public List<UserClient> getAllUser(@PathVariable String name) {
        return userService.findByName(name).
                orElseThrow(() -> new RuntimeException("User not found"));
    }
    @GetMapping("users")
    public List<UserClient> getAllUser() {
        return userService.findAll().
                orElseThrow(() -> new RuntimeException("User not found"));
    }
    @DeleteMapping("users/{codeClient}")
    public void deleteUser(@PathVariable String codeClient) {
        UserClient userClient = userService.findUser(UUID.fromString(codeClient)).
                orElseThrow(() -> new RuntimeException("User not found"));
        userService.deleteUser(userClient);
    }
    @PostMapping("users/login")
    public UserClient login(
            @RequestBody UserDTO userDTO,
            HttpSession session
    ) {
        return userService.login(userDTO.getUsername(), userDTO.getPassword(), session);
    }
    @PostMapping("users/logout")
    public void logout(
            @RequestBody UserClient user,
            HttpSession session
    ) {
        userService.logout(user, session);
    }
}
