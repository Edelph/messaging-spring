package com.edelph.jhon.webSocket.webSocketMessaging.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class BienvenueController {

    @GetMapping
    public String bienvenue() {
        return "Bienvenue sur le serveur de messagerie";
    }
}
