/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */

package stream;

import java.io.*;
import java.net.*;

public class ServersideClientThread
        extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    ServersideClientThread(Socket s) {
        this.clientSocket = s;
    }

    /**
     * receives a request from client then sends an echo to the client

     **/
    public void run() {

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            if(in.getLine()!=null)
            {
                EchoServerMultiThreaded.envoyerMessage(in.getLine());
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }


    public synchronized void sendMessage(String msg) {
        out.println(msg);
    }



}


