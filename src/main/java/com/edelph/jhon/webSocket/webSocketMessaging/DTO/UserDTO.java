package com.edelph.jhon.webSocket.webSocketMessaging.DTO;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class UserDTO {
    private String username;
    private String password;
}
