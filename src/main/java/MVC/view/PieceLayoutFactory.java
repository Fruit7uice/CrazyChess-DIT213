package MVC.view;

import MVC.model.Pieces.Piece;

public class PieceLayoutFactory {

    String[] blacks = {
                        "Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook",
                        "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn"
                        };
    String[] whites = {
                        "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn",
                        "Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"
                        };


    PieceFactory pieceFactory;

    void PieceLayoutFactory(){
        pieceFactory = new PieceFactory();
    }

}
