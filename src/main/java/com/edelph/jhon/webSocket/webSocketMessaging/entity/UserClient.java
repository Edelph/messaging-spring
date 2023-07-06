package com.edelph.jhon.webSocket.webSocketMessaging.entity;

import jakarta.annotation.Nullable;
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
@ToString
public class UserClient {
    @Id
    @GeneratedValue
    private UUID codeClient;
    private String nameClient;
    private String lastNameClient;
    @Column(unique = true, nullable = false)
    private String emailClient;
    @Column(nullable = false)
    private String passWordClient;
    @ManyToMany(
            mappedBy = "userClients",
            fetch = FetchType.LAZY
    )
    private List<GroupeMessage> groupeMessage;
    @Column(columnDefinition = "true")
    private boolean onLine = true;
}
