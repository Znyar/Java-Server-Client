package com.znyar.web;

import com.znyar.util.DefaultLogger;
import com.znyar.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port;
    private boolean isRunning;
    private final Logger log = DefaultLogger.getInstance();

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            isRunning = true;
            log.info("Server started on port " + port);

            while (isRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(new ClientHandler(clientSocket, log)).start();
                } catch (IOException e) {
                    log.error("Error during client connection", e);
                }
            }

        } catch (IOException e) {
            log.error("Error during server connection", e);
        }

    }

    public void terminate() {
        isRunning = false;
        log.info("Server terminated");
    }

    public static ServerBuilder builder() {
        return new ServerBuilder();
    }

    private Server(ServerBuilder builder) {
        this.port = builder.port;
    }

    public static class ServerBuilder {

        private int port;

        public Server build() {
            return new Server(this);
        }

        public ServerBuilder port(int port)  {
            this.port = port;
            return this;
        }

    }

}
