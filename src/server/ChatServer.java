package server;
import java.io.*;
import java.net.*;
import java.util.*;


public class ChatServer {
	private int port = 3000 ;
	private Set<String> userNames = new HashSet<>();
	private Set<UserThread> userThreads = new HashSet<>();

	public ChatServer(int port) {
		this.port = port;
	}

	public void execute() {
		try (ServerSocket serverSocket = new ServerSocket(port)) {

			System.out.println("je suis l'espion du port  " + port);

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("ooo un ami");

				UserThread newUser = new UserThread(socket, this);
				userThreads.add(newUser);
				newUser.start();

			}

		} catch (IOException ex) {
			System.out.println("fait chier : " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ChatServer server = new ChatServer(3000);
		server.execute();
	}

	/**
	 * flood mon ami flood
	 */
	void broadcast(String message, UserThread excludeUser) {
		for (UserThread aUser : userThreads) {
			if (aUser != excludeUser) {
				aUser.sendMessage(message);
			}
		}
	}

	/**
	 * memoire
	 */
	void addUserName(String userName) {
		userNames.add(userName);
	}

	/**
	 * adieu
	 */
	void removeUser(String userName, UserThread aUser) {
		boolean removed = userNames.remove(userName);
		if (removed) {
			userThreads.remove(aUser);
			System.out.println("le con " + userName + " c'est barer ");
		}
	}

	Set<String> getUserNames() {
		return this.userNames;
	}

	/**
	 * oui du monde
	 */
	boolean hasUsers() {
		return !this.userNames.isEmpty();
	}
}