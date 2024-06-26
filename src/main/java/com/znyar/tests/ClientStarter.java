package com.znyar.tests;

import com.znyar.web.Client;

public class ClientStarter {

    public static void main(String[] args) {
        Client.builder()
                .host("localhost")
                .serverPort(8080)
                .clientId(432)
                .build()
                .start();
    }

}
