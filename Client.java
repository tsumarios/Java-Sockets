/*********************************
 *
 *  Title: Simple Java Client
 *  Author: zMrDevJ
 *  Date: 11/22/2017
 *
 ********************************/

import java.io.*;
import java.net.*;
 
public class Client {

    /* Specific request */
    public static void doRequire(BufferedReader in, PrintWriter out, String msg) throws IOException{
        /* Send message as a request */
        System.out.println("> Send message: " + msg);
        out.println(msg);

        /* Wait for reply */
        String reply = in.readLine();
        System.out.println("> Received message: " + reply);
    }

    /* Main */
    public static void main(String[] args) { 
        try{
            /* Input check */
            if (args.length != 1) {
                System.out.println("Must pass a <message> string");
                System.exit(-1);
            }
            
            /* Message to send */
            String msg = args[0];
             
            /* Create socket */
            InetAddress serverAddr = InetAddress.getByName("127.0.0.1");
            int serverPort = 3333;
            Socket socket = new Socket(serverAddr, serverPort);
            System.out.println(">> Connection created");
            Server.printSocketInfo(socket);
             
            /* Connect to input stream */
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(isr);
             
            /* Connect to output stream */
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter out = new PrintWriter(osw, true);
            
            /* Specific request */
            doRequire(in, out, msg);
             
            /* Close the socket */
            System.out.println(">> Close connection");
            socket.close();
             
        }catch(Exception ex){
            ex.printStackTrace();
        }

    } 
}
