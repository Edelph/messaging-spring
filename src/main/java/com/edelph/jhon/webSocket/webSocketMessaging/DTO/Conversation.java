package com.edelph.jhon.webSocket.webSocketMessaging.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {
    private UUID sender;
    private UUID receiver;
}
