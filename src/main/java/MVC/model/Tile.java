package MVC.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static MVC.controller.Chess.tileSize;


public class Tile extends Rectangle {

    private Piece piece;
    private Color c;


    public boolean hasPiece(){
        return piece != null;
    }

    public Piece getPiece(){
        return piece;
    }

    public boolean getPieceColor(){
        return piece.player1;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public void removePiece(){
        this.piece = null;
    }

    public Tile(int x, int y){
        relocate(x * tileSize, y * tileSize);
    }

}
