/***
 * ClientThread Post
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */

package stream;

import java.io.*;
import java.net.*;

public class ClientThreadEcrire
	extends Thread {
	
	private Socket clientSocket;
	private PrintStream socOut;
	
	ClientThreadEcrire(Socket s) {
		this.clientSocket = s;
	}

 	/**
  	* receives a request from client then sends an echo to the client

  	**/
	public void run() {
    	  try {
    		PrintStream socOut = new PrintStream(clientSocket.getOutputStream());
    		while (true) {
    		  socOut.println(line);
    		}
    	} catch (Exception e) {
        	System.err.println("Error in EchoServer:" + e); 
        }
       }


  
  }

  
