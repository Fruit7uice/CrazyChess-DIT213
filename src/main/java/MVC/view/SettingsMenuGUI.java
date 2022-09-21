package MVC.view;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.File;

public class SettingsMenuGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = FXMLLoader.load(new File("src/main/resources/SettingsMenuGUI.fxml").toURI().toURL());
            primaryStage.setScene(new Scene(root));
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
}