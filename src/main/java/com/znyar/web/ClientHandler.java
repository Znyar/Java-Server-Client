package com.znyar.web;

import com.znyar.util.JSONMessageService;
import com.znyar.util.Logger;
import com.znyar.message.Response;
import com.znyar.message.Status;
import com.znyar.util.MessageService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Logger log;
    private final MessageService messageService = new JSONMessageService();

    public ClientHandler(Socket socket, Logger log) {
        this.clientSocket = socket;
        this.log = log;
    }

    @Override
    public void run() {
        try (BufferedReader request = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter response = new PrintWriter(clientSocket.getOutputStream(), true)) {

            log.info("Client connected. ID: " + request.readLine());

            String message;
            while ((message = request.readLine()) != null) {
                log.info("Message received: " + message);
                response.println(new Response(Status.OK, messageService.getAttribute("header", message), messageService.getAttribute("body", message) + "-requested"));
            }
        } catch (IOException e) {
            log.error("Error during client connection", e);
        }
    }
}