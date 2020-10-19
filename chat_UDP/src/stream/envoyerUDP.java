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
        private String nickname;
    


        envoyerUDP(InetAddress groupAdress, int groupPort, String nom) {
            
            this.groupPort=groupPort;
            this.groupAdress=groupAdress;
            this.nickname = nom;
            
            
        
        }

        public void run() {
            try {
                socketEnvoi = new MulticastSocket(groupPort);
                socketEnvoi.joinGroup(groupAdress);
                

                    String msg;
                    stdIn = new BufferedReader(new InputStreamReader(System.in));
                    while(true)
                    {
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
                System.err.println("Error in EchoServer:" + e);
                e.printStackTrace();
            }
        }
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