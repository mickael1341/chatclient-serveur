package com.exia;

import java.io.*;
import java.net.Socket;

public class Handler implements Runnable {

    private Socket client;
    private String tmp;
    public Handler(Socket client ) {
        this.client = client;

    }

    @Override
    public void run() {
        try {
            // Communication
            //  from server to client
            OutputStream out = client.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            //  from client to server
            InputStream in = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));


            // Print messages
            String s = null;
            while ((s = reader.readLine()) != null) {
                writer.write(s + "\n");
                writer.flush();
                System.out.println("Received from client: " + s);
                tmp = s;

            }





            writer.close();
            reader.close();
        } catch (Exception e) {

        }
    }
}
