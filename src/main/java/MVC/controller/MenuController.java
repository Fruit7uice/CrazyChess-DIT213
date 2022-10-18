package MVC.controller;

import MVC.view.MainBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Johannes Höher
 * Class that holds the methods that corresponds to the buttons in the main-manu
 * Each button corresponds to an action-event.
 */



public class MenuController {


    public void startCrazy(ActionEvent e) throws Exception {
        MainBoard.isCrazy = false;
        MainBoard game = new MainBoard();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
        game.runAfterLaunch(stage);
    }


    public void startClassic(ActionEvent e) throws Exception {
        MainBoard.isCrazy = false;
        MainBoard game = new MainBoard();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
        game.runAfterLaunch(stage);

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







