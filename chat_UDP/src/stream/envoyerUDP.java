    package stream;

    import java.io.PrintStream;
    import java.io.PrintWriter;
    import java.net.*;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.io.*;

	/**
	 * Sender thread for the UDP Multicast CLient
	 * @author GALERZA Javier, BEL Corentin, KERMANI Benjamin
	 */
    public class envoyerUDP extends Thread 
    {
        private static int groupPort ;
        private InetAddress groupAdress ;
        private MulticastSocket m;
        private BufferedReader stdIn;
        private MulticastSocket socketEnvoi;
        private String nickname;
    

	/**
     	* Sender thread constructor
     	* @param groupAdress adress on which Multicast will be created
     	* @param groupPort port on which Multicast will be created
     	* @param nom nickname the client will be sending messages as
     	
     	*/
     	
        envoyerUDP(InetAddress groupAdress, int groupPort, String nom) {
            
            this.groupPort=groupPort;
            this.groupAdress=groupAdress;
            this.nickname = nom;
            
            
        
        }

	/**
	     	* Listener thread run method
	     	This is the part where when we start the sender thread, it creates Datagram packets depending on what it will send from the UDP group it joined
	     	The package will contain what was typed by the client via his terminal

     	*/
        public void run() {
            try {
                socketEnvoi = new MulticastSocket(groupPort);
                socketEnvoi.joinGroup(groupAdress);
                

                    String msg;
                    stdIn = new BufferedReader(new InputStreamReader(System.in));
                    while(true)
                    {
                    	// Wait for a message written by the user to create a DatagramPacket containing it and then send it to the group
                        msg= nickname + " :";
                        msg += stdIn.readLine();
                        DatagramPacket pac = new DatagramPacket(msg.getBytes(),
                        msg.length(), groupAdress, groupPort);
                        
                        if (!msg.isEmpty()) {
                            if(msg.contains("quit"))
                            {
                                this.disconnect(socketEnvoi);
                                System.out.println("Exiting chat room now");
                                System.exit(0);
                                
                            }
                            System.out.println("Message envoyé par "+ nickname+": " +msg);
                            socketEnvoi.send(pac);
                        }

                    }
                
            } catch (Exception e) {
                System.err.println("Error in envoyerUDP:" + e);
                e.printStackTrace();
            }
        }
        /**
	     	* Listener thread disconnect method
	     	This method allows one to disconnect by simply typing 'quit'
	     	@param socket socket will be the socket the client wants to disconnect from

     	*/
         synchronized public void disconnect(MulticastSocket socket) 
         {
            String msg = nickname + " has left :(";
            DatagramPacket pac = new DatagramPacket(msg.getBytes(),
            msg.length(), groupAdress, groupPort); 
            try {
                socket.send(pac);
                socket.leaveGroup(groupAdress);
                socket.close();
            } catch (Exception e) {
                System.err.println("Error in envoyerUDP:" + e);
                e.printStackTrace();
            }

            
         }




        
    }
