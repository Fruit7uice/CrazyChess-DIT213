package MVC.model.SpecialMoves;


import MVC.model.Board;
import MVC.model.PieceLayoutFactory;
import MVC.model.Pieces.King;
import MVC.model.Pieces.Piece;
import MVC.model.Pieces.Rook;
import MVC.model.Pieces.MoveHandler;

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

    /**
     * FIXA VARIABEL FÖR VILKEN ROOK SOM ÄR VILKEN OCH MATCHA MOT DEN
     * @param pieces
     * @param king
     * @param rook
     * @return
     */


    public boolean freeSpaceBetweenWKLWR(Piece[][] pieces, King king, Rook rook){
        if(king.isPlayerOne() && rook.isPlayerOne()){
            for (int row = 7; row < 8; row++) {
                for (int col = 1; col < 4; col++) {
                    if ((pieces[row][col]) == null){
                        return true;
                    }
                }
            }
        }return false;
    }


    public boolean freeSpaceBetweenWKRWR(Piece[][] pieces, King king, Rook rook){
        if(king.isPlayerOne() && rook.isPlayerOne()){
            for (int row = 7; row < 8; row++) { // Coordinates need to be fixed
                for (int col = 1; col < 4; col++) { // Coordinates need to be fixed
                    if ((pieces[row][col]) == null){
                        return true;
                    }
                }
            }
        }return false;
    }

    public boolean freeSpaceBetweenBKLBR(Piece[][] pieces, King king, Rook rook){
        if(king.isPlayerOne() && rook.isPlayerOne()){
            for (int row = 7; row < 8; row++) { // Coordinates need to be fixed
                for (int col = 1; col < 4; col++) { // Coordinates need to be fixed
                    if ((pieces[row][col]) == null){
                        return true;
                    }
                }
            }
        }return false;
    }

    public boolean freeSpaceBetweenBKRBR(Piece[][] pieces, King king, Rook rook){
        if(king.isPlayerOne() && rook.isPlayerOne()){
            for (int row = 7; row < 8; row++) { // Coordinates need to be fixed
                for (int col = 1; col < 4; col++) { // Coordinates need to be fixed
                    if ((pieces[row][col]) == null){
                        return true;
                    }
                }
            }
        }return false;
    }







    public boolean haveNotMoved(King king, Rook rook) {
        if (!king.hasMoved && !rook.hasMoved) {
            return true;
        } else {
            return false;
        }
    }




}
