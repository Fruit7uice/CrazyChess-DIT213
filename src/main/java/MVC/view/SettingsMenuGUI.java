package MVC.view;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.File;
import java.util.Objects;

/**
 * This class runs the settings menu which is connected to the fxml
 * document SettingsMenuGui.fxml
 * field savedGame makes saves the current game and makes it possible
 * to return to the same game.
 * Made in SceneBuilder.
 */
public class SettingsMenuGUI extends Application {

    public static Scene savedGame;
    @Override
    public void start(Stage primaryStage) {

        try {
            AnchorPane root =
                    FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/SettingsMenuGUI.fxml")));
            primaryStage.setScene(new Scene(root));
            primaryStage.centerOnScreen();
            primaryStage.show();
            root.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void setSavedGame(Scene scene){
        savedGame = scene;
    }
}
