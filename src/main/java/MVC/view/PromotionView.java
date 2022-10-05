package MVC.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class PromotionView extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root =
                    FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Promotion.fxml")));
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