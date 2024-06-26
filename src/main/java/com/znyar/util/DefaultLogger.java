package com.znyar.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class DefaultLogger implements Logger {

    public static Logger getLogger(String fileName) {
        try {
            return new DefaultLogger(new PrintWriter(new FileWriter(fileName, true)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize logger", e);
        }
    }

    private final PrintWriter out;

    private DefaultLogger(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void info(String msg) {
        out.println(new Date() + " : " + msg);
        out.flush();
    }

    @Override
    public void error(String msg, Exception e)  {
        out.println(new Date() + " : " + msg + " : " + e.getMessage());
        out.flush();
    }

    @Override
    public void close() {
        out.close();
    }

}
