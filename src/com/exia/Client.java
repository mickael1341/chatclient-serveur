package com.exia;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        Socket client = new Socket("localhost", 3000);
        System.out.println("Client started");

        // Communication
        //  from client to server
        OutputStream out = client.getOutputStream();
        PrintWriter writer = new PrintWriter(out);

        //  from server to client
        InputStream in = client.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        System.out.print("Message: ");
        String message = input.nextLine() + "\n";

        writer.write(message);
        writer.flush();

        // Print messages
        String s = null;
        while((s = reader.readLine()) != null) {
            System.out.println("Received from server: " + s);
        }

        reader.close();
        writer.close();
    }

}