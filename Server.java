/*********************************
 *
 *  Title: Simple Java Server
 *  Author: zMrDevJ
 *  Date: 11/22/2017
 *
 ********************************/

import java.io.*;
import java.net.*;
 
public class Server {
 
    /* Print socket info */
    public static void printSocketInfo(Socket socket) {
        System.out.println(">> Socket Info:");
        System.out.println(" Local Address: " + socket.getLocalAddress());
        System.out.println(" Local Port: " + socket.getLocalPort());
        System.out.println(" Remote Address: " + socket.getInetAddress());
        System.out.println(" Remote Port: " + socket.getPort());
    }

    /* Specific service */
    public static void doServe(BufferedReader in, PrintWriter out) throws IOException{
        System.out.println(">> Serving ...");
        String request = in.readLine();
        System.out.println("> Received message: " + request);
        if(request.equals("Hello")) out.println("Welcome!");
        else out.println("Bye!");
    }

    /* Main */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try{
            /* Create server socket */
            int serverPort = 3333;
            serverSocket = new ServerSocket(serverPort);

            /* Loop */
            while(true){
                System.out.println(">> Waiting for connection on port " + serverPort + " ...");

                /* Wait for a connection */
                Socket clientSocket = serverSocket.accept();
                System.out.println(">> Connection received!");
                 
                /* Connect to input stream */
                InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader in = new BufferedReader(isr);
                 
                /* Connect to output stream */
                OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
                PrintWriter out = new PrintWriter(osw, true);
 
                /* Specific service */
                doServe(in, out);
                
                /* Close server socket */
                System.out.println(">> Close connection");
                clientSocket.close();
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
