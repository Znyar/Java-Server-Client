package com.znyar.util;

import com.znyar.message.Message;

public interface MessageService {

    Object getAttribute(String key, Message message);

    String getAttribute(String key, String message);

}
