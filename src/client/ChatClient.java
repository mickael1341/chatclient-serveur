package client;
import java.net.*;
import java.io.*;


public class ChatClient {
	private static String hostname = "localhost";
	private static int port = 3000;
	private String userName;

	public ChatClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void execute() {
		try {
			Socket socket = new Socket(hostname, port);

			System.out.println("bienvenu mon ami");

			new ReadThread(socket, this).start();
			new WriteThread(socket, this).start();

		} catch (UnknownHostException ex) {
			System.out.println("je suis con " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("probleme !!!!!! " + ex.getMessage());
		}

	}

	void setUserName(String userName) {
		this.userName = userName;
	}

	String getUserName() {
		return this.userName;
	}


	public static void main(String[] args) {


		ChatClient client = new ChatClient(hostname, port);
		client.execute();
	}
}