package MVC.model;

import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import static MVC.snap.BoardController.tileSize;

public class Piece extends Rectangle {

    private double oldX, oldY;
    public PieceType type;


    public Piece(PieceType type, double x, double y) {
        this.type = type;

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

    }

    public void abortMove(){
        relocate(oldX, oldY);
    }

    public void draw(double x, double y) {
        setWidth(tileSize);
        setHeight(tileSize);
        relocate(x / tileSize, y / tileSize);


    }
}
