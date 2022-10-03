package MVC.model;

import MVC.model.Pieces.Piece;

public class DummyTile {
    private boolean isOccupied;
    private Piece piece;

    public boolean getIsPlayer1(){
        return piece.isPlayer1();
    }
    public Piece getPieceType() {
        return piece;
    }

    public boolean getIsOccupied(){
        isOccupied = false;
        return isOccupied;
    }
}
