package com.znyar.tests;

import com.znyar.web.Client;

public class ClientStarter2 {

    public static void main(String[] args) {
        Client.builder()
                .host("localhost")
                .serverPort(8080)
                .clientId(67832)
                .build()
                .start();
    }

}
