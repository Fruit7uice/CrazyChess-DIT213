package MVC.controller;

import MVC.view.MainBoard;
import MVC.view.SettingsMenuGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Johannes Höher
 * Class that holds the methods that corresponds to the buttons in the settings-menu.
 * Each button corresponds to an action-event in form of input from the user.
 */


public class SettingsMenuController {


    /**
     * The method that matches against the input equivalent to pressing the backToMenu-button.
     * @param e Pressing the backToMenu-button
     * @throws IOException
     */


    public void backToMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainMenu.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene menu = new Scene(root);
        stage.setScene(menu);
    }

    /**
     * The method that matches against the input equivalent to pressing the restartGame-button.
     * @param e Pressing the restartGame-button
     * @throws IOException
     */


    public void restartGame(ActionEvent e) throws Exception {
        MainBoard game = new MainBoard();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
        game.runAfterLaunch(stage);
    }

    public void undoMove(ActionEvent e) throws Exception {

    }

    /**
     * The method that matches against the input equivalent to pressing the closeSettingsMenu-button.
     * @param e Pressing the closeSettingsMenu-button
     * @throws IOException
     */

    public void closeSettingsMenu(ActionEvent e){
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setFullScreen(false);
        stage.setMaximized(true);
        stage.close();
        stage.setScene(SettingsMenuGUI.savedGame);
        stage.show();
    }
}
