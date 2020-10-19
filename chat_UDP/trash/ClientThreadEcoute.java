/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */

package stream;

import java.io.*;
import java.net.*;

public class ClientThreadEcoute
	extends Thread {
	
	private Socket clientSocket;
	private BufferedReader socIn;
	
	ClientThreadEcoute(Socket s) {
		this.clientSocket = s;
	}

 	/**
  	* receives a request from client then sends an echo to the client

  	**/
	public void run() {
    	  try {
    		BufferedReader socIn = null;
    		socIn = new BufferedReader(
    			new InputStreamReader(clientSocket.getInputStream()));    
    		while (true) {
    		  String line = socIn.readLine();
    		  System.out.println("message e de : "+ clientSocket+ " qui dit " +line);
    		}
    	} catch (Exception e) {
        	System.err.println("Error in EchoServer:" + e); 
        }
       }


  
  }

  
