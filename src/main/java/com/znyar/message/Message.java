package com.znyar.message;

import com.znyar.util.Converter;
import com.znyar.util.JSONConverter;

public abstract class Message {

    private final Status status;
    private final String header;
    private final String body;
    private final String footer;

    public Message(Status status, String header, String body, String footer) {
        this.status = status;
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    @Override
    public String toString() {
        return MessageConverter.CONVERTER.convert(this);
    }

    public Status status() {
        return status;
    }

    public String header() {
        return header;
    }

    public String body() {
        return body;
    }

    public String footer() {
        return footer;
    }

    private static class MessageConverter {

        private static final Converter CONVERTER = new JSONConverter();

    }

}
