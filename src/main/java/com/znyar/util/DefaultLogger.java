package com.znyar.util;

import java.io.PrintWriter;
import java.util.Date;

public class DefaultLogger implements Logger {

    private static class LoggerHolder {
        private static final DefaultLogger INSTANCE = new DefaultLogger(new PrintWriter(System.out, true));

    }

    public static DefaultLogger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    private final PrintWriter out;

    private DefaultLogger(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void info(String msg) {
        out.println(new Date() + " : " + msg);
    }

    @Override
    public void error(String msg, Exception e)  {
        out.println(new Date() + " : " + msg + " : " + e.getMessage());
    }

}
