/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import dbtask.DbTask;
import dbtask.Player;
import java.awt.Color;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author motas
 */
public class PlayingSceneController implements Initializable {

    Vector<Player> playersFromDB = new Vector();
    Vector gamePlayers = new Vector();
    static Vector<ClientHandler> arr = (Vector) ClientHandler.ar.clone();
    static int flag = 0;

    Image x = new Image("assets/x.png");
    Image o = new Image("assets/o.png");
    Image img;
    //int player; //flag to detect win player

    private int xCount = 0;
    private int oCount = 0;

    int[] clickChecker = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
    int[] playerPosition = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
    int counter = 0;

    static String myNickName;

    @FXML
    private ImageView topleft;
    @FXML
    private ImageView topcenter;
    @FXML
    private ImageView topright;
    @FXML
    private ImageView centerleft;
    @FXML
    private ImageView centercenter;
    @FXML
    private ImageView centerright;
    @FXML
    private ImageView bottomleft;
    @FXML
    private ImageView bottomcenter;
    @FXML
    private ImageView bottomright;
    @FXML
    public ComboBox onlinePlayer;

    @FXML
    public TextField messageBox;
    @FXML
    public TextArea chatBox;

    @FXML
    public void sendMessage() {
        String message = messageBox.getText();
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        Client client = new Client("sendMessage", message, myNickName, gamePlayer);
//        chatBox.setText(message);
    }
    private static PlayingSceneController instance;

    public void saveGame() {
        DbTask obj = new DbTask();
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        obj.insertGame(playerPosition, myNickName, gamePlayer);
        try {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Game Saved");
            alert.setHeaderText(null);
            alert.showAndWait();

        } catch (Exception e) {
            System.out.println("error in alert");
        }
    }

    public void loadGame() {
        DbTask obj = new DbTask();
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        SavedGame game = obj.getGame(myNickName, gamePlayer);

        for (int i = 0; i < game.game.length; i++) {
            System.out.println(game.game[i]);
        }
//        restore(game.game);
        Client client = new Client("restoreGame", game.player1, game.player2, game.game.toString());
    }
    /*
            rest_count count for new counter and pass it to normal counter
     */
    int rest_count = 0;

    public int restore(String game) {
        System.out.println("restoring in here");
        System.out.println(game);
//        if (game[0]==1){
//            img = x;
//            topleft.setImage(setImage());
//            rest_count++;
//            }
//        else if (game[0]==2){
//            img = o;
//            topleft.setImage(setImage());  
//        rest_count++;    
//        }
//        if (game[1]==1){
//            img = x;
//            topleft.setImage(setImage());  
//        rest_count++;    
//        }
//        else if (game[1]==2){
//            img = o;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        if (game[2]==1){
//            img = x;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        else if (game[2]==2){
//            img = o;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        if (game[3]==1){
//            img = x;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        else if (game[3]==2){
//            img = o;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        if (game[4]==1){
//            img = x;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        else if (game[4]==2){
//            img = o;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        if (game[5]==1){
//            img = x;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        else if (game[5]==2){
//            img = o;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        if (game[6]==1){
//            img = x;
//            topleft.setImage(setImage());  
//        rest_count++; 
//        }
//        else if (game[6]==2){
//            img = o;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        if (game[7]==1){
//            img = x;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        else if (game[7]==2){
//            img = o;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        if (game[8]==1){
//            img = x;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        else if (game[8]==2){
//            img = o;
//            topleft.setImage(setImage());  
//        rest_count++;
//        }
//        return rest_count; 
        return 0;
    }

    public PlayingSceneController() {
        instance = this;
    }

    public static PlayingSceneController getInstance() {
        return instance;
    }

    @FXML
    public void sendInv() {
//        Client client = new Client("sendInvitation", "0", "1", onlinePlayer.getValue().toString());
        // onlinePlayer.getValue()    is the selected Player 
        // and this function runs when you click on (send Invitation) button
//        System.out.println(onlinePlayer.getValue());
//        for(Object client : gamePlayers){
//            if(client.equals(onlinePlayer.getValue())){
//                System.out.println(client);
//            }
//        }
//        onlinePlayer.setDisable(true);

        String playerValue = (String) onlinePlayer.getValue();

        String gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();

//        String []mm = playerValue.split(".");
//        System.out.println("8888" +mm[0]);
        Client client = new Client("sendInvitation", myNickName, "99", gamePlayer);

    }

    public void invAlert(String nickName) {
        try {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    System.out.println("debug0");
                    ButtonType buttonTypeOne = new ButtonType("Yes");
                    ButtonType buttonTypeTwo = new ButtonType("No");

                    Alert alert = new Alert(AlertType.INFORMATION);

                    alert.setTitle("Playing Request");
                    alert.setHeaderText(nickName + " wants to play with you");

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == buttonTypeOne) {
                        // ... user chose "yes"
                        onlinePlayer.setValue(nickName);
                        onlinePlayer.setDisable(true);
                        enablePlaying();
                        Client client = new Client("lockCombo", "99", "99", nickName);
                        System.out.println("User Accepted challenge");
                    } else if (result.get() == buttonTypeTwo) {
                        // ... user chose "no"
                        System.out.println("User Refused challenge");

                    } else {

                    }
                }
            });

        } catch (Exception e) {
            System.out.println("error in alert");
        }

    }

//    public void move() throws IOException {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(() -> {
//                    System.out.println("successsssssssss");
//
//                    if (true) {
//
//                        System.out.println(PlayingSceneController.arr.isEmpty());
//                        if (!PlayingSceneController.arr.isEmpty()) {
//                            System.out.println("successsssssssss333");
//                        }
//                        System.out.println("successsssssssss3337");
//                        System.out.println(PlayingSceneController.flag);
//
//                    }
//
//                });
//            }
//
//        }, 1000, 1000);
//
//    }
    private void displayPlayers() {
        DbTask db = new DbTask();
        playersFromDB = db.getDbAll();
        for (Player mc : playersFromDB) {
//            System.out.println(mc.getNickName());
//            gamePlayers.add(mc.getNickName());
            String status = "";
            if (mc.getFlag() == 1) {
                status = " (online) ";

            }
            if (!mc.getNickName().equals(myNickName)) {
                gamePlayers.add(mc.getNickName() + "," + status + "( points is " + mc.getPoints() + ")");

            }

        }

        onlinePlayer.setItems(FXCollections.observableArrayList(gamePlayers));
        onlinePlayer
                .setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        final ListCell<String> cell = new ListCell<String>() {
                            {
                                super.setPrefWidth(100);
                            }

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);
                                    if (item.contains("online")) {
                                        this.setStyle("-fx-background-color: #f9d900;-fx-color: #000;");
                                    } else {
                                        this.setStyle("-fx-background-color: #fff;-fx-color: #000;");
                                    }
                                } else {
                                    setText(null);
                                }
                            }

                        };
                        return cell;
                    }
                });
//        onlinePlayer.get
//        onlinePlayer.getButtonCell().setStyle("-fx-background-color: #f9d900");

    }

    private Image setImage() {

        if (counter % 2 == 0) {
            img = x;
        } else {
            img = o;
        }
        counter++;
        return img;

    }

    private void alertMsg(String w) {

        try {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            if (w == "X" || w == "O") {
                alert.setContentText("play " + w + " is winner \n score X : " + xCount + "\n score O : " + oCount);

            } else {

                alert.setContentText(w);
            }
            alert.showAndWait();

        } catch (Exception e) {
            System.out.println("error in alert");
        }
    }

    public void reset() {
        try {
            topleft.setImage(null);
            topcenter.setImage(null);
            topright.setImage(null);

            centerleft.setImage(null);
            centercenter.setImage(null);
            centerright.setImage(null);

            bottomleft.setImage(null);
            bottomcenter.setImage(null);
            bottomright.setImage(null);

            counter = 0;
            // Arrays.fill(clickChecker,1);
            for (int i = 0; i < 9; i++) {
                clickChecker[i] = 1;

            }

        } catch (Exception e) {
            System.out.println("error in reset");
        }
    }

    private void winningGame() {
        Image b1 = topleft.getImage();
        Image b2 = topcenter.getImage();
        Image b3 = topright.getImage();

        Image b4 = centerleft.getImage();
        Image b5 = centercenter.getImage();
        Image b6 = centerright.getImage();

        Image b7 = bottomleft.getImage();
        Image b8 = bottomcenter.getImage();
        Image b9 = bottomright.getImage();
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        try {
            if (b1 == x && b2 == x && b3 == x) {
                xCount++;
                alertMsg("X");
                DbTask obj = new DbTask();
                obj.updatePoints(myNickName);
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();

                //   gameScore();
            } else if (b4 == x && b5 == x && b6 == x) {

                xCount++;
                alertMsg("X");
                DbTask obj = new DbTask();
                obj.updatePoints(myNickName);
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();

                //gameScore();
            } else if (b7 == x && b8 == x && b9 == x) {
                xCount++;
                alertMsg("X");
                DbTask obj = new DbTask();
                obj.updatePoints(myNickName);
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();

                //gameScore();
            } else if (b1 == x && b4 == x && b7 == x) {
                xCount++;
                alertMsg("X");
                DbTask obj = new DbTask();
                obj.updatePoints(myNickName);
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();

                //gameScore();
            } else if (b2 == x && b5 == x && b8 == x) {
                xCount++;
                alertMsg("X");
                DbTask obj = new DbTask();
                obj.updatePoints(myNickName);
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();

                //gameScore();
            } else if (b3 == x && b6 == x && b9 == x) {
                xCount++;
                alertMsg("X");
                DbTask obj = new DbTask();
                obj.updatePoints(myNickName);
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();

                //gameScore();
            } else if (b1 == x && b5 == x && b9 == x) {
                xCount++;
                alertMsg("X");
                DbTask obj = new DbTask();
                obj.updatePoints(myNickName);
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();
                //gameScore();
            } else if (b3 == x && b5 == x && b7 == x) {
                xCount++;
                alertMsg("X");
                DbTask obj = new DbTask();
                obj.updatePoints(myNickName);
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();
                //gameScore();
            } //player O
            else if (b1 == o && b2 == o && b3 == o) {
                oCount++;
                alertMsg("O");
                DbTask obj = new DbTask();
                obj.updatePoints(onlinePlayer.getValue().toString());
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();
                //gameScore()
            } else if (b4 == o && b5 == o && b6 == o) {
                oCount++;
                alertMsg("O");
                DbTask obj = new DbTask();
                obj.updatePoints(onlinePlayer.getValue().toString());
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();
                //gameScore()
            } else if (b7 == o && b8 == o && b9 == o) {
                oCount++;
                alertMsg("O");
                DbTask obj = new DbTask();
                obj.updatePoints(onlinePlayer.getValue().toString());
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();
                //gameScore()
            } else if (b1 == o && b4 == o && b7 == o) {
                oCount++;
                alertMsg("O");
                DbTask obj = new DbTask();
                obj.updatePoints(onlinePlayer.getValue().toString());
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();
                //gameScore()
            } else if (b2 == o && b5 == o && b8 == o) {
                oCount++;
                alertMsg("O");
                DbTask obj = new DbTask();
                obj.updatePoints(onlinePlayer.getValue().toString());
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();
                //gameScore()
            } else if (b3 == o && b6 == o && b9 == o) {
                oCount++;
                alertMsg("O");
                DbTask obj = new DbTask();
                obj.updatePoints(onlinePlayer.getValue().toString());
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();
                //gameScore()
            } else if (b1 == o && b5 == o && b9 == o) {
                oCount++;
                alertMsg("O");
                DbTask obj = new DbTask();
                obj.updatePoints(onlinePlayer.getValue().toString());
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();
                //gameScore();
            } else if (b3 == o && b5 == o && b7 == o) {
                oCount++;
                alertMsg("O");
                DbTask obj = new DbTask();
                obj.updatePoints(onlinePlayer.getValue().toString());
                Client client = new Client("winingGame", myNickName, "", gamePlayer);
                reset();
                //gameScore()
            } else if (clickChecker[0] == 0 && clickChecker[1] == 0 && clickChecker[2] == 0 && clickChecker[3] == 0 && clickChecker[4] == 0 && clickChecker[5] == 0 && clickChecker[6] == 0 && clickChecker[7] == 0 && clickChecker[8] == 0) {
                alertMsg("NO player wins");
                reset();
            }
        } catch (Exception e) {
            System.out.println("clicked");
        }

    }

    public void disablePlaying() {
        topleft.setDisable(true);
        topcenter.setDisable(true);
        topright.setDisable(true);
        centerleft.setDisable(true);
        centerright.setDisable(true);
        bottomleft.setDisable(true);
        bottomcenter.setDisable(true);
        bottomright.setDisable(true);
        System.out.println("playing disabled");
    }

    public void enablePlaying() {
        topleft.setDisable(false);
        topcenter.setDisable(false);
        topright.setDisable(false);
        centerleft.setDisable(false);
        centerright.setDisable(false);
        bottomleft.setDisable(false);
        bottomcenter.setDisable(false);
        bottomright.setDisable(false);
        System.out.println("playing enabled");
    }

    @FXML
    public void drawTopLeft() throws FileNotFoundException, IOException {
//        System.out.println(onlinePlayer.getValue().toString() + "nameInv Moatasem");
//   String[] playerValue = onlinePlayer.getValue().toString().split(".");
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }

        Client client = new Client("playWithOther", "0", "1", gamePlayer);

        if (clickChecker[0] == 1) {
            topleft.setImage(setImage());
            if (img == x) {
                playerPosition[0] = 1;

            } else {
                playerPosition[0] = 2;
            }

            clickChecker[0] = 0;
            winningGame();
        }
        disablePlaying();
    }

    @FXML
    public void drawTopCenter() throws FileNotFoundException {
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }

        Client client = new Client("playWithOther", "1", "1", gamePlayer);
        if (clickChecker[1] == 1) {
            topcenter.setImage(setImage());
            if (img == x) {
                playerPosition[1] = 1;

            } else {
                playerPosition[1] = 2;
            }
            clickChecker[1] = 0;
            winningGame();
        }
        disablePlaying();
    }

    @FXML
    public void drawTopRight() throws FileNotFoundException {
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        Client client = new Client("playWithOther", "2", "1", gamePlayer);
        if (clickChecker[2] == 1) {
            topright.setImage(setImage());
            if (img == x) {
                playerPosition[2] = 1;

            } else {
                playerPosition[2] = 2;
            }
            clickChecker[2] = 0;
            winningGame();
        }
        disablePlaying();
    }

    @FXML
    public void drawCenterLeft() throws FileNotFoundException {
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        Client client = new Client("playWithOther", "3", "1", gamePlayer);
        if (clickChecker[3] == 1) {
            centerleft.setImage(setImage());
            if (img == x) {
                playerPosition[3] = 1;

            } else {
                playerPosition[3] = 2;
            }
            clickChecker[3] = 0;
            winningGame();
        }
        disablePlaying();
    }

    @FXML
    public void drawCenterCenter() throws FileNotFoundException {
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        Client client = new Client("playWithOther", "4", "1", gamePlayer);
        if (clickChecker[4] == 1) {
            centercenter.setImage(setImage());
            if (img == x) {
                playerPosition[4] = 1;

            } else {
                playerPosition[4] = 2;
            }
            clickChecker[4] = 0;
            winningGame();
        }
        disablePlaying();
    }

    @FXML
    public void drawCenterRight() throws FileNotFoundException {
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        Client client = new Client("playWithOther", "5", "1", gamePlayer);
        if (clickChecker[5] == 1) {
            centerright.setImage(setImage());
            if (img == x) {
                playerPosition[5] = 1;

            } else {
                playerPosition[5] = 2;
            }
            clickChecker[5] = 0;
            winningGame();
        }
        disablePlaying();
    }

    @FXML
    public void drawBottomLeft() throws FileNotFoundException {
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        Client client = new Client("playWithOther", "6", "1", gamePlayer);
        if (clickChecker[6] == 1) {
            bottomleft.setImage(setImage());
            if (img == x) {
                playerPosition[6] = 1;

            } else {
                playerPosition[6] = 2;
            }
            clickChecker[6] = 0;
            winningGame();
        }
        disablePlaying();
    }

    @FXML
    public void drawBottomCenter() throws FileNotFoundException {
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        Client client = new Client("playWithOther", "7", "1", gamePlayer);
        if (clickChecker[7] == 1) {
            bottomcenter.setImage(setImage());
            if (img == x) {
                playerPosition[7] = 1;

            } else {
                playerPosition[7] = 2;
            }
            clickChecker[7] = 0;
            winningGame();
        }
        disablePlaying();
    }

    @FXML
    public void drawBottomRight() throws FileNotFoundException {
        String playerValue = (String) onlinePlayer.getValue();
        boolean isFound = playerValue.contains(",");
        String gamePlayer;
        if (isFound) {
            gamePlayer = playerValue.subSequence(0, playerValue.indexOf(",")).toString();
        } else {
            gamePlayer = playerValue;
        }
        Client client = new Client("playWithOther", "8", "1", gamePlayer);
        if (clickChecker[8] == 1) {
            bottomright.setImage(setImage());
            if (img == x) {
                playerPosition[8] = 1;

            } else {
                playerPosition[8] = 2;
            }
            clickChecker[8] = 0;
            winningGame();

        }
        disablePlaying();
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Parent DifficultyParent = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
        Scene DifficultyScene = new Scene(DifficultyParent);
        Stage DifficultyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DifficultyStage.setScene(DifficultyScene);
        DifficultyStage.show();
    }

    @FXML
    public void signOut(ActionEvent event) throws IOException {
        Client client = new Client("logout", "", "", myNickName);
        Parent DifficultyParent = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        Scene DifficultyScene = new Scene(DifficultyParent);
        Stage DifficultyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DifficultyStage.setScene(DifficultyScene);
        DifficultyStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayPlayers();
        disablePlaying();
    }

}
