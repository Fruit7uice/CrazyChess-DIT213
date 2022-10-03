package MVC.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import MVC.model.Tile;


public class GUIRenderer {


    public void drawTile(Tile tile, int x, int y, Color c, int size){
        tile.setWidth(size);
        tile.setHeight(size);
        tile.setFill(c);
        tile.relocate(x * size, y * size);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


