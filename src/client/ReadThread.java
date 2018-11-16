package client;
import java.io.*;
import java.net.*;


public class ReadThread extends Thread {
	private BufferedReader reader;
	private Socket socket;
	private ChatClient client;

	public ReadThread(Socket socket, ChatClient client) {
		this.socket = socket;
		this.client = client;

		try {
			InputStream input = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input));
		} catch (IOException ex) {
			System.out.println("probleme dans ma lecture l 18 " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				String response = reader.readLine();
				System.out.println("\n" + response);

				if (client.getUserName() != null) {
					System.out.print("[" + client.getUserName() + "]: ");
				}
			} catch (IOException ex) {
				System.out.println("j'abandone: " + ex.getMessage());
				ex.printStackTrace();
				break;
			}
		}
	}
}