package com.edelph.jhon.webSocket.webSocketMessaging.DTO;

import com.edelph.jhon.webSocket.webSocketMessaging.entity.Message;
import lombok.*;

import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String message;
    private UUID sender;
    private UUID receiver;
}
