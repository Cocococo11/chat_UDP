    package stream;

    import java.io.PrintStream;
    import java.io.PrintWriter;
    import java.net.*;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.io.*;

    public class envoyerUDP extends Thread 
    {
        private static int groupPort ;
        private InetAddress groupAdress ;
        private MulticastSocket m;
        private BufferedReader stdIn;
        private MulticastSocket socketEnvoi;


        envoyerUDP(InetAddress groupAdress, int groupPort) {
            
            this.groupPort=groupPort;
            this.groupAdress=groupAdress;
            
            
        
        }

        public void run() {
            try {
                socketEnvoi = new MulticastSocket(groupPort);
                socketEnvoi.joinGroup(groupAdress);
                

                    String msg;
                    stdIn = new BufferedReader(new InputStreamReader(System.in));
                    while(true)
                    {
                        msg = stdIn.readLine();
                        DatagramPacket pac = new DatagramPacket(msg.getBytes(),
                        msg.length(), groupAdress, groupPort);
                        
                        if (!msg.isEmpty()) {
                            System.out.println("Message envoyé : " +msg);
                            socketEnvoi.send(pac);
                        }
                    }
                
            } catch (Exception e) {
                System.err.println("Error in EchoServer:" + e);
                e.printStackTrace();
            }
        }




        
    }