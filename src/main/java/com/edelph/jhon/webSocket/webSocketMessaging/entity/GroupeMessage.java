package com.edelph.jhon.webSocket.webSocketMessaging.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupeMessage {
    @Id
    @GeneratedValue
    private UUID codeGroupeMessage;
    private String descriptionGroupeMessage;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "groupe_message_client",
            joinColumns = @JoinColumn(name = "groupe_code"),
            inverseJoinColumns = @JoinColumn(name = "client_code")
    )
    private List<UserClient> userClients;
}
