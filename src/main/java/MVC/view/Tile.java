package MVC.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static MVC.model.Board.BOARD_SIZE;

/**
 * This class represents how a tile is represented on the board
 */
public class Tile extends Rectangle {

    public static int tileSize = BOARD_SIZE/8;
    private Color color;

    public Tile() {}
    public Tile(double x, double y, double width, double height, Color color) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.color = color;
    }


    public Color getColor() {
        return color;
    }

}
