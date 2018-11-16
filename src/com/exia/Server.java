package com.exia;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public volatile String tmp = null;

    public static void main(String[] args) throws IOException {

        int port = 3000;
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Start the server
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while(true) {
            // Connect to client
            Socket client = server.accept();
            //Thread t = new Thread(new Handler(client));
            //t.start();
            executor.execute(new Handler(client));
        }

    }

}