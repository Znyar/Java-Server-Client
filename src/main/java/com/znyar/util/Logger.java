package com.znyar.util;

public interface Logger extends AutoCloseable {


    void info(String message);

    void error(String message, Exception e);

}
