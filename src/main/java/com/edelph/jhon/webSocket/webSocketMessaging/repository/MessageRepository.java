package com.edelph.jhon.webSocket.webSocketMessaging.repository;

import com.edelph.jhon.webSocket.webSocketMessaging.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m " +
            "where m.sender.codeClient=?1 and m.receiver.codeClient=?2  " +
            " or m.sender.codeClient=?2  and m.receiver.codeClient=?1 " +
            "order by m.codeMessage asc ")
    List<Message> getMessages(UUID sender,UUID receiver);
}
