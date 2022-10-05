package MVC.model.SpecialMoves;


import MVC.model.PieceLayoutFactory;
import MVC.model.Pieces.King;
import MVC.model.Pieces.Piece;
import MVC.model.Pieces.Rook;

/**
 * Class that holds the logic for the castle-move.
 * @author Johannes Höher
 */



public class Castle {


    public boolean tryCastle(King king, Rook rook){
        return true;
    }



    public void switchKingRook(King king, Rook rook){

    }


    public boolean freeSpaceBetweenWKLR(Piece[][] pieces, King king, Rook rook)





    public boolean haveNotMoved(King king, Rook rook) {
        if (!king.hasMoved && !rook.hasMoved) {
            return true;
        } else {
            return false;
        }
    }




}
