    package stream;

    import java.io.PrintStream;
    import java.io.PrintWriter;
    import java.net.*;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.io.*;

    public class UDPMain
    {
        public static int groupPort; 
        public static InetAddress groupAdress; 


        public static void main(String args[]){
            
            try {
                groupPort = 8080;
                groupAdress = InetAddress.getByName("224.1.1.1");
                EcouteUDP e = new EcouteUDP(groupAdress,groupPort);
                envoyerUDP en = new envoyerUDP(groupAdress,groupPort);
                e.start();
                en.start();
            }catch(Exception e){
                e.printStackTrace();
            }
            

            

        }




        
    }