package com.znyar.tests;

import com.znyar.web.Server;

public class ServerStarter {

    public static void main(String[] args) {
        Server.builder()
                .port(8080)
                .build()
                .start();
    }

}