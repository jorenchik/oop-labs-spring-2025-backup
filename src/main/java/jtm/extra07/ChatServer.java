package jtm.extra07;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class ChatServer implements Runnable {

    static ServerSocket server;
    static Vector<ChatServer> connections;
    static final int port = 7700;

    private Socket client;
    private Scanner in;
    private PrintWriter out;

    public ChatServer(Socket client) {
        try {
            this.client = client;
            in = new Scanner(client.getInputStream());
            out = new PrintWriter(client.getOutputStream(), true); // auto-flush

            synchronized (connections) {
                connections.add(this);
            }
        } catch (IOException e) {
            System.err.println("Error initializing client connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        connections = new Vector<>();
        try {
            server = new ServerSocket(port);
            System.out.println("ChatServer started on port " + port);
        } catch (IOException e) {
            System.err.println("Could not start server on port " + port + ": " + e.getMessage());
            System.exit(1);
        }

        while (true) {
            try {
                Socket socket = server.accept();
                Thread t = new Thread(new ChatServer(socket));
                t.start();
            } catch (IOException e) {
                System.err.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }

	@Override
	public void run() {
		try {
			while (in.hasNextLine()) {
				String msg = in.nextLine();

				// Handle exit/quit before broadcasting
				if (msg.equalsIgnoreCase("quit") || msg.equalsIgnoreCase("exit")) {
					break;
				}

				String formattedMsg = "> " + msg;
				synchronized (connections) {
					for (ChatServer cs : connections) {
						cs.sendMsg(formattedMsg);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error during client communication: " + e.getMessage());
		} finally {
			try {
				if (in != null) in.close();
				if (out != null) out.close();
				if (client != null && !client.isClosed()) client.close();
			} catch (IOException e) {
				System.err.println("Error closing client resources: " + e.getMessage());
			}
			synchronized (connections) {
				connections.remove(this);
			}
		}
	}

    public void sendMsg(String msg) {
        if (out != null) {
            out.println(msg);
        }
    }
}
