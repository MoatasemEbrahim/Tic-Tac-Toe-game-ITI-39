/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author lenovo
 */
public class Client extends Application {

    Socket clientSocket;
    Socket internal;
    DataInputStream dis;
    PrintStream ps;
    private boolean loginFlag = false;
    private boolean registerFlag = false;
    private String msg = "";
    int count = 0;
    int tlflag = 0;
    int tcflag = 0;
    int trflag = 0;
    int clflag = 0;
    int ccflag = 0;
    int crflag = 0;
    int blflag = 0;
    int bcflag = 0;
    int brflag = 0;

    private PlayingSceneController controller;

    public void setController(PlayingSceneController controller) {
        this.controller = controller;
    }

    public Client(String msg, String userName, String password, String nickName) {
        String message = "";
        if (msg == "login") {
            message = msg + ',' + userName + ',' + password;
        } else if (msg == "register") {
            message = msg + ',' + userName + ',' + password + ',' + nickName;
        } else if (msg == "playWithComputer") {
            message = msg;
        } else if (msg == "playWithOther") {
            message = msg + ',' + userName + ',' + password + ',' + nickName;
        } else if (msg == "sendInvitation") {
            message = msg + ',' + userName + ',' + nickName;
        } else if (msg == "lockCombo") {
            message = msg + ',' + nickName;
        } else if (msg == "logout") {
            message = msg + ',' + nickName;
        } else if (msg == "sendMessage") {
            message = msg + ',' + userName + ',' + password + ',' + nickName;
        } else if (msg == "closeSocket") {
            message = msg + ',' + "";
        } else if (msg == "winingGame") {
            message = msg + ',' + nickName + ','+ userName;
        }else if (msg == "restoreGame")
        {
            message = msg + ',' + userName + ',' + password + ',' + nickName;
        }
//        System.out.println("Sent from client=>"+message);
//        System.out.println("client comes in client");
        try {
            clientSocket = new Socket("localhost", 5005);

            dis = new DataInputStream(clientSocket.getInputStream());
            ps = new PrintStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
//         try {
//            start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        this.setLayout(new FlowLayout());
//        JTextArea ta = new JTextArea(5, 150);
//        JScrollPane scroll = new JScrollPane(ta);
//        scroll.setViewportView(ta);
//        JTextField tf = new JTextField(30);
//        JButton okButton = new JButton("Send");
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                int flag = 0;
                while (true) {

                    try {
                        String msg = dis.readLine();

                        String[] reseivedMsg = msg.split(",");
                        System.out.println(msg);
                        if (reseivedMsg[0].equals("correctUser")) {
                            loginFlag = true;
                        } else {
                            loginFlag = false;
                        }

                        if (reseivedMsg[0].equals("registerUser")) {
                            registerFlag = true;
                        } else {
                            registerFlag = false;
                        }
                        
                        if(reseivedMsg[0].equals("completeToRestore"))
                        {
                                
                                PlayingSceneController.getInstance().restore(reseivedMsg[3]);
                        }
                        if (reseivedMsg[0].equals("completeToWin")) {
                            PlayingSceneController.getInstance().reset();
                            tlflag = 0;
                            tcflag = 0;
                            trflag = 0;
                            clflag = 0;
                            ccflag = 0;
                            crflag = 0;
                            blflag = 0;
                            bcflag = 0;
                            brflag = 0;
                        }
                        if (reseivedMsg[0].equals("completeToPlay")) {
                            PlayingSceneController.getInstance().enablePlaying();
//                            System.out.println("come here to receive");
//
//                            System.out.println("draw");
//                            System.out.println(reseivedMsg[1]);
//                            System.out.println(reseivedMsg[2]);
//                            FXMLLoader playersListLoader = new FXMLLoader(getClass().getResource("\"/xo/PlayingScene.fxml"));
////                            playersListLoader.setLocation(getClass().getResource("\"/xo/PlayingScene.fxml"));
//                            Parent playersListParent = playersListLoader.load();
////                            Scene playersListScene = new Scene(playersListParent);
//                            PlayingSceneController pl = (PlayingSceneController) playersListLoader.getController();

                            if (reseivedMsg[1].equals("0") && tlflag < 1) {
                                PlayingSceneController.getInstance().drawTopLeft();
//                                System.out.println(reseivedMsg[1]);
                                tlflag++;

                            }

                            if (reseivedMsg[1].equals("1") && tcflag < 1) {
                                PlayingSceneController.getInstance().drawTopCenter();
//                                System.out.println(reseivedMsg[1]);
                                tcflag++;

                            }

                            if (reseivedMsg[1].equals("2") && trflag < 1) {
                                PlayingSceneController.getInstance().drawTopRight();
//                                System.out.println(reseivedMsg[1]);
                                trflag++;

                            }

                            if (reseivedMsg[1].equals("3") && clflag < 1) {
                                PlayingSceneController.getInstance().drawCenterLeft();
//                                System.out.println(reseivedMsg[1]);
                                clflag++;

                            }

                            if (reseivedMsg[1].equals("4") && ccflag < 1) {
                                PlayingSceneController.getInstance().drawCenterCenter();
//                                System.out.println(reseivedMsg[1]);
                                ccflag++;

                            }

                            if (reseivedMsg[1].equals("5") && crflag < 1) {
                                PlayingSceneController.getInstance().drawCenterRight();
//                                System.out.println(reseivedMsg[1]);
                                crflag++;

                            }

                            if (reseivedMsg[1].equals("6") && blflag < 2) {
                                PlayingSceneController.getInstance().drawBottomLeft();
//                                System.out.println(reseivedMsg[1]);
                                blflag++;

                            }

                            if (reseivedMsg[1].equals("7") && bcflag < 1) {
                                PlayingSceneController.getInstance().drawBottomCenter();
//                                System.out.println(reseivedMsg[1]);
                                bcflag++;

                            }

                            if (reseivedMsg[1].equals("8") && brflag < 1) {
                                PlayingSceneController.getInstance().drawBottomRight();
//                                PlayingSceneController.getInstance().enablePlaying();
//                                System.out.println(reseivedMsg[1]);
                                brflag++;

                            }

//                            PlayingSceneController.getInstance().drawTopLeft();
//                            if (reseivedMsg[1] == "1") {
//                                System.out.println("here 2");
//                                PlayingSceneController.getInstance().drawTopCenter();
//                            }
                            /* hena hnstable position and value w hrnsmha  */
                        }

                        if (reseivedMsg[0].equals("completeToSendMessage")) {
//                            System.out.println("inv recieved");
                            PlayingSceneController.getInstance().chatBox.appendText(reseivedMsg[1]);
                            PlayingSceneController.getInstance().messageBox.setText("");

//                            break;
                        }

                        if (reseivedMsg[0].equals("completeSend")) {
//                            System.out.println("inv recieved");
                            PlayingSceneController.getInstance().invAlert(reseivedMsg[1]);

//                            break;
                        }
                        if (reseivedMsg[0].equals("lockCombo")) {
                            PlayingSceneController.getInstance().onlinePlayer.setDisable(true);

//                            break;
                        }
//                        System.out.println("dara from client");
//                        System.out.println("appending msg" + msg);
//                        ta.append(msg);
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    String msg = "nouur";
                    //                        System.out.println(msg);
//                        tf.
//                    String msg = "nouur";
                    //                        System.out.println(msg);
//                        tf.

                }

            }

        });
        ps.println(message);
        th.start();
    }
//        });

//        okButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ae) {
//                System.out.println("clicked on button");
//                String str = tf.getText();
//               
//               
////                 System.out.println(str);
//                
//            }
//        });
//        th.start();
////        add(scroll);
////        add(tf);
//        add(okButton);
    public boolean canLogin() {
        return loginFlag;
    }

    public boolean canRegister() {
        return registerFlag;
    }

//    public String 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Client ui = new Client("");
//        ui.setSize(400, 500);
//        ui.setVisible(true);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
