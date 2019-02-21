/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import dbtask.*;
import xo.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author motas
 */
public class ServerSceneController implements Initializable {

    @FXML
    private ComboBox players;

    public void onBtnAction() throws FileNotFoundException {
        System.out.println("On");
    }

    public void offBtnAction() throws FileNotFoundException {
        System.out.println("Off");
         Client client = new Client("closeSocket","", "" ,"");
    }

    Vector<Player> playersFromDB = new Vector();
    Vector serverPlayers = new Vector();

    private void displayServerPlayers() {
        DbTask db = new DbTask();
        playersFromDB = db.getDbAll();
        for (Player mc : playersFromDB) {
//            System.out.println(mc.getNickName());
            String status = "";
            if (mc.getFlag() == 1) {
                status = " (online) ";
            }
           
            serverPlayers.add(mc.getNickName() + status  + "( points is " + mc.getPoints()+ ")");
        }

        players.setItems(FXCollections.observableArrayList(serverPlayers));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayServerPlayers();
    }

}
