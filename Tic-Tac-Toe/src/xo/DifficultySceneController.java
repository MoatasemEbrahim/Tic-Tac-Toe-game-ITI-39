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
public class DifficultySceneController implements Initializable {

    
    public void moveToPlayingScene(ActionEvent event) throws IOException
    {
        Parent playingParent = FXMLLoader.load(getClass().getResource("playingWithComp.fxml"));
        Scene playingScene = new Scene(playingParent);
        Stage playingStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        playingStage.setScene(playingScene);
        playingStage.show();
        PlayingWithCompController.computerIsDifficult =false;
    }
    
    public void moveToPlayingSceneHard(ActionEvent event) throws IOException
    {
        Parent playingParent = FXMLLoader.load(getClass().getResource("playingWithComp.fxml"));
        Scene playingScene = new Scene(playingParent);
        Stage playingStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        playingStage.setScene(playingScene);
        playingStage.show();
        PlayingWithCompController.computerIsDifficult =true;
    }
    
    public void moveToMenuScene(ActionEvent event) throws IOException
    {
        Parent menuParent = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage menuStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        menuStage.setScene(menuScene);
        menuStage.show();
    }
    
    public void moveToHomeScene(ActionEvent event) throws IOException
    {
         Client client = new Client("logout","", "" ,myNickName);
        Parent homeParent = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
