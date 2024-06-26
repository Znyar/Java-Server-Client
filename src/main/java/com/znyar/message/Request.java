package com.znyar.message;

import java.util.Date;

public class Request extends Message {

    public Request(String header, String body) {
        super(Status.OK, header, body, new Date().toString());
    }

}
