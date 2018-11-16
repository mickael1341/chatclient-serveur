package client;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WriteThread extends Thread {
	private PrintWriter writer;
	private Socket socket;
	private ChatClient client;
	private String userName;
	private String texte;

	public WriteThread(Socket socket, ChatClient client) {
		this.socket = socket;
		this.client = client;

		try {
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);
		} catch (IOException ex) {
			System.out.println("la blague : " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void run() {

		//Console console = System.console();

		//String userName = console.readLine("\nEnter your name: ");

		System.out.println("c quoi ton nom ");
		Scanner sc = new Scanner(System.in);
		userName = sc.nextLine();


		client.setUserName(userName);
		writer.println(userName);

		String text;

		do {
			texte = sc.nextLine();
			text = texte ;
			writer.println(text);


			//text = console.readLine("[" + userName + "]: ");
		}
		while (!text.equals("stop"));

		try {
			socket.close();
		} catch (IOException ex) {

		}
	}
}