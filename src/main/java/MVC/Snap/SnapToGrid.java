package MVC.Snap;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.util.Objects;

public class SnapToGrid extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root =
             FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/test2.fxml")));
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