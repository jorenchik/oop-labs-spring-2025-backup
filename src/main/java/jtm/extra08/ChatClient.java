package jtm.extra08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient implements Runnable {
    private static BufferedReader stdin, srvin;
    private static PrintWriter srvout;
    private static Socket server;
    private static Thread t;

    static final int port = 7700;

    public static void main(String[] args) {
        try {
            // Connect to server on localhost
            server = new Socket("localhost", port);

            // Standard input (keyboard)
            stdin = new BufferedReader(new InputStreamReader(System.in));

            // Writer to server
            srvout = new PrintWriter(server.getOutputStream(), true); // auto-flush

            // Client that reads messages from server
            ChatClient sc = new ChatClient(server);
            t = new Thread(sc);
            t.start();

            // Read input from user and send to server
            String line;
            while ((line = stdin.readLine()) != null) {
                srvout.println(line);
                if (line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("quit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error in ChatClient: " + e.getMessage());
            System.exit(1);
        } finally {
            try {
                if (stdin != null) stdin.close();
                if (srvin != null) srvin.close();
                if (srvout != null) srvout.close();
                if (server != null && !server.isClosed()) server.close();
            } catch (IOException e) {
                System.err.println("Error closing ChatClient resources: " + e.getMessage());
            }
        }
    }

    public ChatClient(Socket server) {
        try {
            srvin = new BufferedReader(new InputStreamReader(server.getInputStream()));
        } catch (IOException e) {
            System.err.println("Error creating server input reader: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = srvin.readLine()) != null) {
                System.out.println(line); // Print messages received from server
            }
        } catch (IOException e) {
            System.err.println("Error reading from server: " + e.getMessage());
        }
    }
}
