/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;


/**
 *
 * @author motas
 */
public class PlayingMode extends Thread {

    DataInputStream dis1;
    PrintStream ps1;
    DataInputStream dis2;
    PrintStream ps2;
    Socket player1;
    Socket player2;
    Thread th1;
    Thread th2;
    public String player;

    static Vector<PlayingMode> players
            = new Vector<PlayingMode>();

    public PlayingMode(Socket plr1,  Socket plr2) {
        player1 = plr1;
        player2 = plr2;
        
        // inputs and outputs for player1
        try {
            dis1 = new DataInputStream(player1.getInputStream());
            System.out.println("DataInputStream created for player 1");
        } catch (IOException ex) {
            System.out.println("couldn't create DataInputStream");
            ex.printStackTrace();
        }
        try {
            ps1 = new PrintStream(player1.getOutputStream());
            System.out.println("PrintStream created for player 1");
        } catch (IOException ex) {
            System.out.println("couldn't create PrintStream");
            ex.printStackTrace();
        }
        
        // inputs and outputs for player2
        try {
            dis2 = new DataInputStream(player2.getInputStream());
            System.out.println("DataInputStream created for player 2");
        } catch (IOException ex) {
            System.out.println("couldn't create DataInputStream");
            ex.printStackTrace();
        }
        try {
            ps2 = new PrintStream(player2.getOutputStream());
            System.out.println("PrintStream created for player 2");
        } catch (IOException ex) {
            System.out.println("couldn't create PrintStream");
            ex.printStackTrace();
        }
        
        

        players.add(this);
        th1 = new Thread(this);
        th2 = new Thread(this);
        th1.start();
        th2.start();
    }
    
    // how to make 2 threads listening
    
    @Override
    public void run() {
            
        try {
            while (true) {
                String str = dis1.readLine(); // lestining from other player
                
                // handling closing the client window
                if (str.equals("Client closed app")) {
                    System.out.println("client closed00000000000");
                    ps1.close();
                    dis1.close();
                    player1.close(); // will be handeled (closing the window)
                    break;
                }

                System.out.println("I listened a message");
                System.out.println(str);
                sendMessage(str);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    void sendMessage(String msg) {
        
           
        
        System.out.println("message sent to user");
    }
}
