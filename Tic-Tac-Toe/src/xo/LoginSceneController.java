/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import dbtask.DbTask;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author motas
 *
 *
 */
public class LoginSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField loginUserNameInput;

    @FXML
    private TextField loginPasswordInput;

    public void moveToHomeScene(ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
    }

    public void moveToMenuScene(ActionEvent event) throws IOException {
        System.out.println("login");
        String username = loginUserNameInput.getText();
        String password = loginPasswordInput.getText();
        System.out.println(loginPasswordInput.getText());
        DbTask obj = new DbTask();
        String[] params = {username, password};

        PlayingWithCompController.playerNickname = obj.getPerson(params).getNickName();
        PlayingSceneController.myNickName = obj.getPerson(params).getNickName();

        String nickname = "";
        Client client = new Client("login", username, password, nickname);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
//                    System.out.println("successsssssssss");
//                    System.out.println(client.canLogin());
                    try {
                        if (client.canLogin()) {
//                            System.out.println("successsssssssss22222222222");
                            Parent menuParent = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
                            Scene menuScene = new Scene(menuParent);
                            Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            menuStage.setScene(menuScene);
                            menuStage.show();
                            timer.cancel();
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
            }

        }, 1000, 1000);

//        new java.util.Timer().schedule(new , 3000);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
