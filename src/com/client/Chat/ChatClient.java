package com.client.Chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This is the chat client program. Type 'bye' to terminate the program.
 *
 * 
 */
public class ChatClient {

	private int port;
	private String userName;

	public ChatClient(int port) {

		this.port = port;
	}

	public void execute() {
		try {
			Socket socket = new Socket("localhost", port);

			System.out.println("Connected to the chat server");

			new ReadThread(socket, this).start();
			new WriteThread(socket, this).start();

		} catch (UnknownHostException ex) {
			System.out.println("Server not found: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("I/O Error: " + ex.getMessage());
		}

	}

	void setUserName(String userName) {
		this.userName = userName;
	}

	String getUserName() {
		return this.userName;
	}

	public static void main(String[] args) {
		System.out.println("Begin!");

		ChatClient client = new ChatClient(8989);
		client.execute();
	}
}