
package MVC.view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.util.Objects;

/**
 * This class runs the main menu which is connected to the fxml
 * document MainMenu.fxml, made in SceneBuilder.
 */
public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root =
             FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainMenu.fxml")));
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


