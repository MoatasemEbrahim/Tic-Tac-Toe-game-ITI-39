/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author motas
 */
public class Server extends Application {
    
    Thread thread = new Thread(new Runnable() {

    @Override
    public void run() {
        new MyServer();  
    }
            
});
        


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ServerScene.fxml"));
        Scene scene = new Scene(root);      
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        thread.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
