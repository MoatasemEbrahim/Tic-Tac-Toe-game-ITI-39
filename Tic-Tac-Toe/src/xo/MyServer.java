package xo;
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// Server class 
public class MyServer {
    // Vector to store active clients 

    // counter for clients 
    static int i = 0;

    ServerSocket ss;
    Socket s;
    
    
    public MyServer() {
//        System.out.println("running server");
//            System.out.println("Server Started");
        try {
//            System.out.println("running server 1");
            ss = new ServerSocket(5005);
//            System.out.println("i want it"+ss);
            while (true) {
//                System.out.println("new client comes");
                s = ss.accept();
//                System.out.println("New client request received : " + s);
//                DataInputStream dis = new DataInputStream(s.getInputStream());
//                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
//                PrintStream dos = new PrintStream(s.getOutputStream());
//                new ChatHandler(s);
//                System.out.println("Creating a new handler for this client...");
                new ClientHandler(s, "client " + i);
//              
//                System.out.println("Adding this client to active client list");
                
                

                // add this client to active clients list 
                // increment i for new client. 
                // i is used for naming only, and can be replaced 
                // by any naming scheme 
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws IOException {
        new MyServer();
        
        // server is listening on port 1234 

        // running infinite loop for getting 
        // client request 
//        while (true)  
//        { 
//            // Accept the incoming request 
//            s = ss.accept(); 
//  
//            System.out.println("New client request received : " + s); 
//              
//            // obtain input and output streams 
//            DataInputStream dis = new DataInputStream(s.getInputStream()); 
//            DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
//              
//            System.out.println("Creating a new handler for this client..."); 
//  
//            // Create a new handler object for handling this request. 
//            ClientHandler mtch = new ClientHandler(s,"client " + i, dis, dos); 
//  
//            // Create a new Thread with this object. 
//            Thread t = new Thread(mtch); 
//              
//            System.out.println("Adding this client to active client list"); 
//  
//            // add this client to active clients list 
//            ar.add(mtch); 
//  
//            // start the thread. 
//            t.start(); 
//  
//            // increment i for new client. 
//            // i is used for naming only, and can be replaced 
//            // by any naming scheme 
//            i++; 
//        } 

    }

    

   
    
  
}
