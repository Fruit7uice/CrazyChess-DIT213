package MVC.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static MVC.controller.BoardController.tileSize;

public class Piece extends Rectangle {

    private double oldX, oldY;
    private double xIndex, yIndex;
    public PieceType type;


    public Piece(PieceType type, double x, double y) {
        this.type = type;
        this.xIndex = x;
        this.yIndex = y;

        setHeight(tileSize);
        setWidth(tileSize);
        setFill(Color.GREEN); //TODO make transparent when done.
        relocate(x * tileSize, y * tileSize);

    }

    public PieceType getType(){
        return this.type;
    }

    public void move(double x, double y) {
        oldX = x * tileSize;
        oldY = y * tileSize;
        this.relocate(oldX, oldY);
        xIndex = x / tileSize;
        yIndex = y / tileSize;

    }

    public double getxIndex(){
        return xIndex;
    }

    public double getyIndex(){
        return yIndex;
    }

    public void abortMove(){
        relocate(oldX, oldY);
    }


}
