/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */

package stream;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

public class EchoServerMultiThreaded  {

	/**
  	* main method
	 *
  	**/
	private static List<ServersideClientThread> clients;
	private static ServerThread serverTh;


       public static void main(String args[]){ 
        ServerSocket listenSocket;
        
  	if (args.length != 1) {
          System.out.println("Usage: java EchoServer <EchoServer port>");
          System.exit(1);
  	}
	try {

		clients = new LinkedList<ServersideClientThread>();
		serverTh = new ServerThread(Integer.parseInt(args[0]));
		serverTh.start();
		System.out.println("Server ready..."); 

        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
      }


      public static synchronized void envoyerMessage(String msg)
	  {
		  synchronized(clients){
			  for(ServersideClientThread c : clients) {
				  c.sendMessage(c + " : " + msg);
			  }
		  }



	  }
	  public static synchronized void ajouterClient(ServersideClientThread s)
	     {
			 clients.add(s);
		 }
  }

  
