package MVC.controller;

import MVC.model.Board;
import MVC.model.PieceLayoutFactory;
import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;
import MVC.view.BoardGUI;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.EventObject;
import java.util.Objects;

import static MVC.controller.MainBoard.WINDOW_HEIGHT;
import static MVC.controller.MainBoard.WINDOW_WIDTH;

/**
 * @author Johannes Höher
 * Class that holds the methods that corresponds to the buttons in the main-manu
 * Each button corresponds to an action-event.
 */



public class MenuController {


    public void startCrazy(ActionEvent e) throws IOException {
        System.out.println("Start Crazy");
        //TODO: connect this to the crazyChess implemenation.
    }


    public void startClassic(ActionEvent e) throws Exception {
        MainBoard classicGame = new MainBoard();

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        classicGame.runAfterLaunch(stage);

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







