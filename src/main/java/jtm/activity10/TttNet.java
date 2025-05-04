// File: TttNet.java
package jtm.activity10;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Wrapper for TttCli that uses TCP sockets instead of standard I/O.
 */
public class TttNet {
    static final int port = 7700;
    static final String host = "localhost";

    public static void main(String[] args) {
        int size = 3;
        if (args != null && args.length == 1) {
            size = Integer.parseInt(args[0]);
        }

        // 1. Try server mode
        try (ServerSocket server = new ServerSocket(port);
             Socket socket = server.accept()) {

            InputStream input  = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            TttCli tttCli = new TttCli(input, output, size);
            tttCli.play();
            return;

        } catch (BindException be) {
            // Port already in use → another instance is server
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // 2. Otherwise, run in client mode
        try (Socket client = new Socket(host, port);
             BufferedReader srvin = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter stdout = new PrintWriter(System.out, true);
             BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter srvout    = new PrintWriter(client.getOutputStream(), true)) {

            String line;
            while ((line = srvin.readLine()) != null) {
                stdout.println(line);
                if (line.contains("Game ended!")) {
                    break;
                }
                if (line.endsWith("Enter place:")) {
                    String userInput = stdin.readLine();
                    srvout.println(userInput);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
