package server;

import java.io.*;
import java.net.*;
import java.util.*;


public class UserThread extends Thread {
	private Socket socket;
	private ChatServer server;
	private PrintWriter writer;

	public UserThread(Socket socket, ChatServer server) {
		this.socket = socket;
		this.server = server;
	}

	public void run() {
		try {
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);

			printUsers();

			String userName = reader.readLine();
			server.addUserName(userName);

			String serverMessage = "un nouveau ami : " + userName;
			server.broadcast(serverMessage, this);

			String clientMessage;

			do {
				clientMessage = reader.readLine();
				serverMessage = "[" + userName + "]: " + clientMessage;
				server.broadcast(serverMessage, this);

			} while (!clientMessage.equals("bye"));

			server.removeUser(userName, this);
			socket.close();

			serverMessage = userName + "il c barer.";
			server.broadcast(serverMessage, this);

		} catch (IOException ex) {
			System.out.println("t'as crash ma geul: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * ya du monde !!!?
	 */
	void printUsers() {
		if (server.hasUsers()) {
			writer.println("je suis la: " + server.getUserNames());
		} else {
			writer.println("t seul mon pauvre");
		}
	}


	/**
	 * envoi au client
	 */
	void sendMessage(String message) {
		writer.println(message);
	}
}