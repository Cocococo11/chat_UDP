    package stream;

    import java.io.PrintStream;
    import java.io.PrintWriter;
    import java.net.*;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.io.*;
    // Recommended : 
    // Address : 224.1.1.1
    // port : 3000 
    
    
    /**
 * Main to create the client's threads to join a Multicast UDP server group chat
 * @author GALERZA Javier, BEL Corentin, KERMANI Benjamin
 */

    public class UDPMain
    {
        public static int groupPort; 
        public static InetAddress groupAdress; 
        public static String nickname;


        public static void main(String args[]){
        // Main method, creating the two threads that are gonna take care of the messages exchange
            if (args.length != 3) {
                System.out.println("Usage: java UDPMain <UDP Adress> <Group port> <Nickname>");
                System.exit(1);
            }
            try {
            // We create two threads based on the ip address, port that will create a Multicast socket and join it
                groupPort = Integer.parseInt(args[1]);
                groupAdress = InetAddress.getByName(args[0]);
                nickname = args[2];
                EcouteUDP e = new EcouteUDP(groupAdress,groupPort,nickname);
                envoyerUDP en = new envoyerUDP(groupAdress,groupPort,nickname);
                // We start them
                e.start();
                en.start();
            }catch(Exception e){
                e.printStackTrace();
            }
            

            

        }




        
    }
