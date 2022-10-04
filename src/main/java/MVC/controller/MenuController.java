package MVC.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.EventObject;
import java.util.Objects;

public class MenuController {


    public void startCrazy(ActionEvent e) throws IOException {
        System.out.println("Start Crazy");
        //TODO: connect this to the crazyChess implemenation.
    }


    public void startClassic(ActionEvent e) {

        System.out.println("startClassic");

        //TODO: connect this to the classicChess implemenation.
    }


    public void exitGame(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainMenu.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Game");
        alert.setHeaderText("You're about to exit the game");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }
}







