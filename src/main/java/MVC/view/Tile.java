package MVC.view;

import MVC.model.Pieces.DummyPiece;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Color color;
    private DummyPiece piece;

    public Tile() {}
    public Tile(double x, double y, double width, double height, Color color) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.color = color;
    }

    public Tile(double x, double y, double width, double height, Color color, DummyPiece dummyPiece) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.color = color;
        this.piece = dummyPiece;
    }

    public Color getColor() {
        return color;
    }

    public DummyPiece getPiece() {
        return piece;
    }

    public void setPiece(DummyPiece piece) {
        this.piece = piece;
    }
}
