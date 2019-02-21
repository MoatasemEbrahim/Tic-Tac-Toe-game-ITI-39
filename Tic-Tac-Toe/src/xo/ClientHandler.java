// ClientHandler class 
package xo;

import dbtask.DbTask;
import dbtask.Player;
import java.awt.AWTEventMulticaster;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static xo.MyServer.i;

public class ClientHandler extends Thread {

    Scanner scn = new Scanner(System.in);
    private String name;
    DataInputStream dis;
    PrintStream dos;
    Socket s;
    boolean isloggedin;
    int count = 0;
    static Vector<ClientHandler> ar = new Vector<>();
    static Vector<ClientHandler> returnedAr = new Vector<>();
    static Vector<Player> players = new Vector<>();
    static Vector<Player> onlinePlayers = new Vector<>();
    DbTask obj = new DbTask();
    public int id;
    String nickname;

    // constructor 
    public ClientHandler(Socket s, String name) throws IOException {

        this.dis = new DataInputStream(s.getInputStream());;
        this.dos = new PrintStream(s.getOutputStream());;
        this.name = name;
        this.isloggedin = true;
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                received = dis.readLine();

                String[] reseivedMsg = received.split(",");
               

                if (reseivedMsg[0].equals("closeSocket")) {
                    ar.clear();
                    break;
                }

                if (reseivedMsg[0].equals("logout")) {

                    for (ClientHandler mc : ar) {
                        if (mc.nickname.equals(reseivedMsg[1])) {
//                            count++;
//                            mc.dos.println("completeSend" + ',' + reseivedMsg[1] + ",wants to play with you");
//                            System.out.println(this.nickname+"nicktessssssst");
//                            updateOfline
                            DbTask obj = new DbTask();
                            obj.updateOfline(mc.id);
//                            mc.s.close();
//                            break;
                        }
                    }

                }

                if (reseivedMsg[0].equals("sendMessage")) {

                    for (ClientHandler mc : ar) {
                        if (mc.nickname.equals(reseivedMsg[2]) || mc.nickname.equals(reseivedMsg[3])) {
                            mc.dos.println("completeToSendMessage" + ',' + reseivedMsg[1]);

                        }
                    }

                }

                /*login*/
                if (reseivedMsg[0].equals("login")) {
                    DbTask obj = new DbTask();
                    String[] params = {reseivedMsg[1], reseivedMsg[2]};
                    Boolean check = obj.checkUser(params);
                    if (check) {
                        this.dos.println("correctUser");
                        this.id = obj.getPerson(params).getId();
                        this.nickname = obj.getPerson(params).getNickName();

                        obj.update(id);
                        ar.add(this);
                        i++;
//                        System.out.println(ar.isEmpty());
//                        setVector(ar);
//                       PlayingSceneController.arr.addAll(ar);

                        /**
                         * ****************
                         * Parent homeParent =
                         * FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
                         * Scene homeScene = new Scene(homeParent); Stage
                         * homeStage = (Stage) ((Node)
                         * event.getSource()).getScene().getWindow();
                         * homeStage.setScene(homeScene); homeStage.show();
                         */
                    }
                }
                /*play with computer */
                if (reseivedMsg[0].equals("playWithOther")) {

                    /* msgto send index /value /other player */
                    // break the string into message and recipient part 
//                    System.out.println("comes in play with other");
//                    System.out.println(reseivedMsg[3]);

                    for (ClientHandler mc : ar) {
//                        System.out.println(mc.name);
//                        System.out.println("what i want" + reseivedMsg[3]);
//                         if (mc.nickname.equals(reseivedMsg[3]) || mc.nickname.equals(this.nickname)) {
                        if (mc.nickname.equals(reseivedMsg[3])) {
//                             if (mc.nickname.equals("nourhan")) {
                            count++;
                            mc.dos.println("completeToPlay" + ',' + reseivedMsg[1] + "," + reseivedMsg[2]);
//                            System.out.println(mc.nickname + " : ");
//                            System.out.println("Data received");
                            if (count == 1) {
//                                break;
                            }
                        }
                    }
                }
                if (reseivedMsg[0].equals("restoreGame")) {

                    /* msgto send index /value /other player */
                    // break the string into message and recipient part 
//                    System.out.println("comes in play with other");
//                    System.out.println(reseivedMsg[3]);

                    for (ClientHandler mc : ar) {
//                        System.out.println(mc.name);
//                        System.out.println("what i want" + reseivedMsg[3]);
//                         if (mc.nickname.equals(reseivedMsg[3]) || mc.nickname.equals(this.nickname)) {
                        if (mc.nickname.equals(reseivedMsg[1]) || mc.nickname.equals(reseivedMsg[2])) {
//                             if (mc.nickname.equals("nourhan")) {
                            count++;
                            mc.dos.println("completeToRestore" + ',' + reseivedMsg[3]);
//                            System.out.println(mc.nickname + " : ");
//                            System.out.println("Data received");
                            if (count == 1) {
//                                break;
                            }
                        }
                    }
                }
                 if (reseivedMsg[0].equals("winingGame")) {

                    /* msgto send index /value /other player */
                    // break the string into message and recipient part 
//                    System.out.println("comes in play with other");
//                    System.out.println(reseivedMsg[3]);

                    for (ClientHandler mc : ar) {
//                        System.out.println(mc.name);
//                        System.out.println("what i want" + reseivedMsg[3]);
//                         if (mc.nickname.equals(reseivedMsg[3]) || mc.nickname.equals(this.nickname)) {
                        if (mc.nickname.equals(reseivedMsg[1])|| mc.nickname.equals(reseivedMsg[2])) {
//                             if (mc.nickname.equals("nourhan")) {
                            count++;
                            mc.dos.println("completeToWin");
//                            System.out.println(mc.nickname + " : ");
//                            System.out.println("Data received");
                            if (count == 1) {
//                                break;
                            }
                        }
                    }
                }
                
                if (reseivedMsg[0].equals("sendInvitation")) {
                    /* msgto send index /value /other player */
                    // break the string into message and recipient part 
                    System.out.println(reseivedMsg[1]);

                    for (ClientHandler mc : ar) {
//                        System.out.println(mc.name);
//                        System.out.println("what i want" + reseivedMsg[3]);
                        if (mc.nickname.equals(reseivedMsg[2])) {
                            count++;
                            mc.dos.println("completeSend" + ',' + reseivedMsg[1] + ",wants to play with you");
//                            System.out.println(this.nickname + "nicktessssssst");
//                               System.out.println(mc.nickname + " : ");
//                               System.out.println("Data received");
//                            if (count == 2) {
//                                break;
//                            }
                        }
                    }
                }

                if (reseivedMsg[0].equals("lockCombo")) {
                    /* msgto send index /value /other player */
                    // break the string into message and recipient part 
                    System.out.println(reseivedMsg[1]);

                    for (ClientHandler mc : ar) {
//                        System.out.println(mc.name);
//                        System.out.println("what i want" + reseivedMsg[3]);
                        if (mc.nickname.equals(reseivedMsg[1])) {
                            count++;
                            mc.dos.println("lockCombo" + ',' + reseivedMsg[1]);
                        }
                    }
                }
                /*play with computer */
//                if (reseivedMsg[0].equals("playWithComputer")) {
//                  
//                            // break the string into message and recipient part 
//                       StringTokenizer st = new StringTokenizer(received, "#");
//                       String MsgToSend = st.nextToken();
//                       String recipient = st.nextToken();
//                       System.out.println("gohary");
//
//                       for (ClientHandler mc : ar) {
//                           System.out.println(mc.name);
//                           System.out.println("what i want" + this.name);
//                           if (mc.name.equals(recipient) || mc.name.equals(this.name)) {
//                               count++;
//                               mc.dos.println(MsgToSend);
//                               System.out.println(mc.name + " : " + MsgToSend);
//                               System.out.println("Data received");
//                               if (count == 2) {
//                                   break;
//                               }
//                           }
//                       }
//                }
                /*register*/
                if (reseivedMsg[0].equals("register")) {
                    DbTask obj = new DbTask();
//                    System.out.println(reseivedMsg[3]);
//                    String[] params = {reseivedMsg[1], reseivedMsg[2], reseivedMsg[3]};
                    String[] params = {reseivedMsg[1], reseivedMsg[2], reseivedMsg[3]};
                    Boolean check = obj.insert(params);
                    if (check) {
                        this.dos.println("registerUser");
//                        System.out.println(ar.isEmpty());
//                        setVector(ar);
//                       PlayingSceneController.arr.addAll(ar);

                        /**
                         * ****************
                         * Parent homeParent =
                         * FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
                         * Scene homeScene = new Scene(homeParent); Stage
                         * homeStage = (Stage) ((Node)
                         * event.getSource()).getScene().getWindow();
                         * homeStage.setScene(homeScene); homeStage.show();
                         */
                    }

                }
                /*view online players*/
                if (received.equals("players")) {
                    players = obj.getAll();
                    for (Player player : players) {
                        int playerId = player.getId();
                        for (ClientHandler client : ar) {
                            if (playerId == client.id) {
                                onlinePlayers.add(player);
                            }
                        }
                    }
                    for (Player mc : onlinePlayers) {
//                        System.out.println(mc.getUsername());
                    }

                }
//                System.out.println("checking Vector outside" + ar.isEmpty());
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
//        try {
//            // closing resources 
//            this.dis.close();
//            this.dos.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public static void setVector(Vector<ClientHandler> ar) {
        System.out.println("array in set" + ar.isEmpty());
        returnedAr = ar;
        System.out.println("returned array in set" + returnedAr.isEmpty());
    }

    public static Vector<ClientHandler> getVector() {
        System.out.println("returned array" + returnedAr.isEmpty());

        return returnedAr;
    }

}
