package com.edelph.jhon.webSocket.webSocketMessaging.repository;

import com.edelph.jhon.webSocket.webSocketMessaging.entity.GroupeMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupeMessageRepository extends JpaRepository<GroupeMessage, UUID> {
}
