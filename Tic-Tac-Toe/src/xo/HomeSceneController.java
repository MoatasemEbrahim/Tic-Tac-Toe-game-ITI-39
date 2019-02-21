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

/**
 * FXML Controller class
 *
 * @author motas
 */
public class HomeSceneController implements Initializable {

    
    
    public void moveToLoginScene(ActionEvent event) throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage loginStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        loginStage.setScene(loginScene);
        loginStage.show();
    }
    
    public void moveToRegisterScene(ActionEvent event) throws IOException
    {
        Parent registerParent = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
        Scene registerScene = new Scene(registerParent);
        Stage registerStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        registerStage.setScene(registerScene);
        registerStage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
