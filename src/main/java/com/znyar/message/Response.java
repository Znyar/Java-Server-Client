package com.znyar.message;

import java.util.Date;

public class Response extends Message {

    public Response(Status status, String header, String body) {
        super(status, header, body, new Date().toString());
    }

}
