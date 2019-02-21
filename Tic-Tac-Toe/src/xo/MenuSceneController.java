/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static xo.PlayingSceneController.myNickName;

/**
 * FXML Controller class
 *
 * @author motas
 */
public class MenuSceneController implements Initializable {

    public void moveToHomeScene(ActionEvent event) throws IOException {
        System.out.println("log out");
        Client client = new Client("logout","", "" ,myNickName);
        Parent homeParent = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
    }

    /* islam */
    public void moveToDifficultyScene(ActionEvent event) throws IOException {
        /* Computer */
 /* create client */

        Parent DifficultyParent = FXMLLoader.load(getClass().getResource("DifficultyScene.fxml"));
        Scene DifficultyScene = new Scene(DifficultyParent);
        Stage DifficultyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DifficultyStage.setScene(DifficultyScene);
        DifficultyStage.show();
    }

    public void startOnline(ActionEvent event) throws IOException {
        Parent playingParent = FXMLLoader.load(getClass().getResource("PlayingScene.fxml"));
        Scene playingScene = new Scene(playingParent);
        Stage playingStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        playingStage.setScene(playingScene);
        playingStage.show();
        Client.main(new String[0]);
//        ClientSide.main(new String[0]);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
