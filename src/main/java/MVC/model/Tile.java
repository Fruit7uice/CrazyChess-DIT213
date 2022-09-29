package MVC.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static MVC.controller.BoardController.tileSize;


public class Tile extends Rectangle {

    private Piece piece;
    private Color c;


    public boolean hasPiece(){
        return piece != null;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Tile(int x, int y, Color c){
        setWidth(tileSize);
        setHeight(tileSize);

        relocate(x * tileSize, y * tileSize);

        setFill(c);
    }

}
