    package stream;

    import java.io.PrintStream;
    import java.io.PrintWriter;
    import java.net.*;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.io.*;
    
    /**
 * Listening thread for the UDP Multicast CLient
 * @author GALERZA Javier, BEL Corentin, KERMANI Benjamin
 */

    public class EcouteUDP extends Thread
    {
        private static int groupPort ;
        private InetAddress groupAdress ;
        private MulticastSocket m;
        private String nickname;

	/**
     	* Listener thread constructor
     	* @param groupAdress adress on which Multicast will be created
     	* @param groupPort port on which Multicast will be created
     	* @param nom nickname the client will be sending messages as
     	
     	*/
        EcouteUDP(InetAddress groupAdress, int groupPort, String nom) {
            
            this.groupPort=groupPort;
            this.groupAdress=groupAdress;
            this.nickname=nom;
            
        
        }

	/**
	     	* Listener thread run method
	     	This is the part where when we start the listener thread, it creates Datagram packets depending on what it receives from the UDP group it joined

     	*/
        public void run() {
            try {
            // We join the group
                MulticastSocket m = new MulticastSocket(groupPort);
                m.joinGroup(groupAdress);
                System.out.println("En attente d'un message venant des participants...");
                // We start listening to datagrampackets that could be send to the Multicast group
                while (true) {
                    byte[] buff = new byte[1000];
                    DatagramPacket rec = new DatagramPacket(buff, buff.length);
                    
                    m.receive(rec);
                    String line = new String(rec.getData(),0, rec.getLength());
                    if (!line.isEmpty()) {
                        System.out.println(line);
                        
                    }
                }
            } catch (Exception e) {
                System.err.println("Error in EcouteUDP:" + e);
                e.printStackTrace();
            }
        }




        
    }
