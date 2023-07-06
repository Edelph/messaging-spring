package com.edelph.jhon.webSocket.webSocketMessaging.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Calendar;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeMessage;

    private String content;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_code")
    private UserClient sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_code")
    private UserClient receiver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_groupe")
    private GroupeMessage receiverGroupe;
}
