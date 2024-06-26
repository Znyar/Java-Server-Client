package com.znyar.util;

import com.znyar.message.Message;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JSONConverter implements Converter {
    @Override
    public String convert(Message message) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Field field : Message.class.getDeclaredFields()) {
            try {
                Method method = Message.class.getMethod(field.getName());
                sb.append(field.getName()).append(":").append(method.invoke(message)).append(",");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

}
