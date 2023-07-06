package com.edelph.jhon.webSocket.webSocketMessaging.util;

import java.rmi.server.UID;

public class Util {
    public static final String URL_SENDER_DESTINATION = "messages/sendTo";
    public static final String URL_RECEIVER = "/messages";
    public static final String URL_NEW_USER = "/users/new";


    public static final String PREFIX_SENDER_DESTINATION = "/app";
    public static final String ENDPOINT = "/ws";
    public static final String URL_SENDER_DESTINATION_ALL = "messages/sendTo/all";

    public static String URL_sendTo(String destination) {
        return URL_RECEIVER +"/"+ destination;
    }
    public static UID generateUID(){
        return new UID();
    }
}
