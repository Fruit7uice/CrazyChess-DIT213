package MVC.model.Pieces;

import javafx.scene.shape.Rectangle;

public class DummyPiece {
    public double x;
    public double y;


    public Rectangle rect;
    public int width;
    public int height;

    public double offset = width/2;

    public DummyPiece(int x, int y, Rectangle rect, int width, int height) {
        this.x = x;
        this.y = y;
        this.rect = rect;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
