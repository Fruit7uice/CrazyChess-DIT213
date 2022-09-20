package MVC.view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public class TestGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL url = new File("src/main/resources/TestGUI.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
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