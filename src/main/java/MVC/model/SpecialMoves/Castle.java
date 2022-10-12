package MVC.model.SpecialMoves;


import MVC.model.Board;
import MVC.model.Pieces.King;
import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;
import MVC.model.Pieces.Rook;


/**
 * Class that holds the logic for the Special move of castle.
 * @author Johannes Höher
 */

public class Castle {



    public Castle(){
    }




    public boolean isCastleAllowed(King king, Rook rook, Piece[][] pieces){
        if(preconditionsWhiteKingLongCastle(pieces, king, rook)){
            return true;
        }else if(preconditionsWhiteKingShortCastle(pieces, king, rook)){
            return true;
        }else if(preconditionsBlackKingLongCastle(pieces, king, rook)){
            return true;
        }else if(preconditionsBlackKingShortCastle(pieces, king, rook)){
            return true;
        }
    }


    /**
     * Method that switches the positions of the White King and the left white Rook into castle-positions.
     * @param king
     * @param rook
     */


    public void whiteKingLongCastle(King king, Rook rook, Board board){ // White king left white rook.
            board.updateGameLayout(king, 2, 7);
            board.updateGameLayout(rook, 3, 7);
        }

    /**
     * Method that switches the positions of the White King and the right whit Rook into castle-positions.
     * @param king
     * @param rook
     */

    public void whiteKingShortCastle(King king, Rook rook, Board board){ // White king right white rook.
            board.updateGameLayout(king, 6, 7);
            board.updateGameLayout(rook, 5, 7);
        }

    /**
     * Method that switches the positions of the Black King and the left black Rook into castle-positions.
     * @param king
     * @param rook
     */

    public void blackKingLongCastle (King king, Rook rook, Board board){ // Black king left black rook.
            board.updateGameLayout(king, 2, 0);
            board.updateGameLayout(rook, 3, 0);
        }

    /**
     * Method that switches the positions of the black King and the right black Rook into castle-positions.
     * @param king
     * @param rook
     */

    public void blackKingShortCastle (King king, Rook rook, Board board){ // Black King Right Black Rook
            board.updateGameLayout(king, 6, 0);
            board.updateGameLayout(rook, 5, 0);
        }


    /**
     * Method that makes sure that the space between the white King and the left white Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The white King
     * @param rook The left white Rook.
     * @return true if the space is not occupied between them.
     */

    public boolean preconditionsWhiteKingLongCastle(Piece[][] pieces, King king, Rook rook) { // White King Left Rook
        if (king.isPlayerOne() && rook.isPlayerOne() && rook.xPos == 0 && rook.yPos == 7
                && notMoved(king, rook)){
                for (int row = 7; row < 8; row++) {
                    for (int col = 1; col < 4; col++) {
                        if ((pieces[row][col]) == null) {
                            return true;
                        }
                    }
                }
        }return false;
    }

    /**
     * Method that makes sure that the space between the white King and the left white Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The white King
     * @param rook The right white Rook.
     * @return true if the space is not occupied between them.
     */



    public boolean preconditionsWhiteKingShortCastle(Piece[][] pieces, King king, Rook rook){ // White King Right Rook
        if(king.isPlayerOne() && rook.isPlayerOne() && rook.xPos == 7 && rook.yPos == 7
                && notMoved(king, rook)){
                for (int row = 7; row < 8; row++) {
                    for (int col = 5; col < 7; col++) {
                        if ((pieces[row][col]) == null) {
                            return true;
                        }
                    }
                }
        }return false;
    }


    /**
     * Method that makes sure that the space between the black King and the left black Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The black King
     * @param rook The left black Rook.
     * @return true if the space is not occupied between them.
     */


    public boolean preconditionsBlackKingLongCastle(Piece[][] pieces, King king, Rook rook){ // White King Left Rook
        if(!king.isPlayerOne() && !rook.isPlayerOne() && rook.xPos == 0 && rook.yPos == 0
                && notMoved(king, rook)){
                for (int row = 0; row < 1; row++) {
                    for (int col = 1; col < 4; col++) {
                        if ((pieces[row][col]) == null){
                            return true;
                        }
                    }
                }
        }return false;
    }

    /**
     * Method that makes sure that the space between the black King and the right black Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The black King
     * @param rook The right black Rook.
     * @return true if the space is not occupied between them.
     */


    public boolean preconditionsBlackKingShortCastle(Piece[][] pieces, King king, Rook rook){ // Black King Right Rook
        if(!king.isPlayerOne() && !rook.isPlayerOne() && rook.xPos == 0 && rook.yPos == 7
                && notMoved(king, rook)){
                for (int row = 0; row < 1; row++) {
                    for (int col = 5; col < 7; col++) {
                        if ((pieces[row][col]) == null){
                            return true;
                        }
                    }
                }
        }return false;
    }


    /**
     * Method that checks if both the king and rook has moved or not.
     * @param king
     * @param rook
     * @return true if both of them has not moved.
     */

    public boolean notMoved(King king, Rook rook) {
        return !king.hasMoved && !rook.hasMoved;
    }




}
