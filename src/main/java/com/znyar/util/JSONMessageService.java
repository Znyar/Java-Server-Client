package com.znyar.util;

import com.znyar.message.Message;

import java.lang.reflect.Field;

public class JSONMessageService implements MessageService {

    @Override
    public Object getAttribute(String key, Message message) {
        Field[] fields = message.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(key)) {
                field.setAccessible(true);
                try {
                    return field.get(message);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public String getAttribute(String key, String message) {
        if (message.contains(key)) {
            int ddot = message.indexOf(key) + key.length() + 1;
            return message.substring(ddot, message.indexOf(",", ddot));
        }
        return null;
    }

}
