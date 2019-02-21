/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author motas
 */
public class RegisterSceneController implements Initializable {

    @FXML
    private TextField RegisterUserNameInput;

    @FXML
    private TextField RegisterNickNameInput;

    @FXML
    private TextField RegisterPasswordInput;

    @FXML
    private TextField RegisterPortInput;

    @FXML
    private TextField RegisterIPInput;

    public void moveToHomeScene(ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
    }

    public void moveToMenuScene(ActionEvent event) throws IOException {
//        String username = RegisterUserNameInput.getText();
//        String nickName = RegisterNickNameInput.getText();
//        String password = RegisterPasswordInput.getText();
//        System.out.println(loginPasswordInput.getText());
//        Client client = new Client("register", username, password, nickName);
        System.out.println("register");
        String username = RegisterUserNameInput.getText();
        
        String password = RegisterPasswordInput.getText();
        System.out.println("88888888");
        System.out.println(password);
        System.out.println("88888888888");
//        System.out.println(loginPasswordInput.getText());
        String nickname = RegisterNickNameInput.getText();
        if(username.length()>1 && password.length()>1 && nickname.length()>1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("You have Registered Successfully");
            alert.showAndWait();
            
            
        Client client = new Client("register", username, password, nickname);
        Parent menuParent = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        menuStage.setScene(menuScene);
        menuStage.show();
        }
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
