package MVC.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Rectangle rectangle;

    @FXML
    public AnchorPane doggo;

    @FXML
    public AnchorPane anchorPane;


    DraggableHandler draggableMaker = new DraggableHandler();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        draggableMaker.makeDraggable(rectangle);
        draggableMaker.makeDraggable(anchorPane);
        draggableMaker.makeDraggable(doggo);
    }
}