package com.edelph.jhon.webSocket.webSocketMessaging.repository;

import com.edelph.jhon.webSocket.webSocketMessaging.entity.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserClientRepository extends JpaRepository<UserClient, UUID> {

    @Query("SELECT u FROM UserClient u WHERE lower(u.nameClient) like lower(concat( '%',:name,'%')) or lower(u.lastNameClient) like lower(concat( '%',:name,'%'))")
    List<UserClient> finByName(@Param("name") String nameParam);

    @Modifying
    @Query("update UserClient u set u.onLine = true where u.codeClient = :id ")
    void  setOnLine(@Param("id") UUID codeClient);

    @Modifying
    @Query("update UserClient u set u.onLine = false where u.codeClient = :id ")
    void setOffLine(@Param("id") UUID codeClient);

    @Query("SELECT u FROM UserClient u WHERE u.emailClient = :email and u.passWordClient = :password")
    List<UserClient> login(@Param("email") String username,@Param("password") String password);
}
