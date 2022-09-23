package MVC.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public AnchorPane whitePawn;
    DraggableHandler draggableMaker = new DraggableHandler();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        draggableMaker.makeDraggable(whitePawn);
    }
}