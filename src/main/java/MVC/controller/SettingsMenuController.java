package MVC.controller;

import MVC.view.SettingsMenuGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SettingsMenuController {


    public void backToMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainMenu.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene menu = new Scene(root);
        stage.setScene(menu);
    }



    public void restartGame(ActionEvent e){
        System.out.println("restart");
        //TODO make something happen
    }
    public void closeSettingsMenu(ActionEvent e){
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        stage.setFullScreen(false);
        stage.setMinWidth(815);
        stage.setMinHeight(830);
        stage.setMaximized(true);
        stage.close();
        stage.setScene(SettingsMenuGUI.savedGame);
        stage.show();
        //System.out.println("close");
        //TODO make something happen
    }
}
