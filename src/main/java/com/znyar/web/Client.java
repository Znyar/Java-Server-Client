package com.znyar.web;

import com.znyar.util.DefaultLogger;
import com.znyar.util.Logger;
import com.znyar.message.Request;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private boolean isRunning;
    private final int serverPort;
    private final String host;
    private final long clientId;
    private final Logger log;

    public void start() {
        isRunning = true;
        log.info("Client started. Client ID: " + clientId);
        log.info("Connecting to " + host + ":" + serverPort);
        while (isRunning) {
            try (Socket socket = new Socket(host, serverPort)) {

                BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter request = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

                log.info("Connected to " + socket.getInetAddress().getHostAddress() + ":" + serverPort);
                request.println(clientId);

                String userInput;
                while ((userInput = in.readLine()) != null) {
                    request.println(new Request("client-id:" + clientId, userInput));
                    log.info("Server response: " + response.readLine());
                }
            } catch (UnknownHostException e) {
                log.error("Unknown host " + host, e);
            } catch (IOException e) {
                log.error("Can't connect to " + host, e);
            }
        }
    }

    public void terminate() {
        isRunning = false;
        log.info("Client terminated");
    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }

    private Client(ClientBuilder builder) {
        this.serverPort = builder.serverPort;
        this.host = builder.host;
        this.clientId  = builder.clientId;
        log = DefaultLogger.getLogger("client" + clientId + ".log");
    }

    public static class ClientBuilder {

        private int serverPort;
        private String host;
        private long clientId;

        public Client build() {
            return new Client(this);
        }

        public ClientBuilder serverPort(int serverPort)  {
            this.serverPort = serverPort;
            return this;
        }

        public ClientBuilder host(String host) {
            this.host = host;
            return this;
        }

        public ClientBuilder clientId(long clientId)  {
            this.clientId = clientId;
            return this;
        }

    }

}
