package MVC.model;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

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
