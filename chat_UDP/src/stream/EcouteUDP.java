    package stream;

    import java.io.PrintStream;
    import java.io.PrintWriter;
    import java.net.*;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.io.*;

    public class EcouteUDP extends Thread
    {
        private static int groupPort ;
        private InetAddress groupAdress ;
        private MulticastSocket m;


        EcouteUDP(InetAddress groupAdress, int groupPort) {
            
            this.groupPort=groupPort;
            this.groupAdress=groupAdress;
            
        
        }

        public void run() {
            try {
                MulticastSocket m = new MulticastSocket(groupPort);
                m.joinGroup(groupAdress);
                while (true) {
                    byte[] buff = new byte[1000];
                    DatagramPacket rec = new DatagramPacket(buff, buff.length);
                    System.out.println("En attente d'un message venant des participants...");
                    m.receive(rec);
                    String line = new String(rec.getData(),0, rec.getLength());
                    if (!line.isEmpty()) {
                        System.out.println("Message recu : " +line);
                        
                    }
                }
            } catch (Exception e) {
                System.err.println("Error in EchoServer:" + e);
                e.printStackTrace();
            }
        }




        
    }