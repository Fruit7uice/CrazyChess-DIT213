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
    public King king;
    public Rook rook;
    public Piece[][] pieces;
    public Board board;
    public MoveHandler moveHandler;


    public Castle(King king, Rook rook, Board board, Piece[][] pieces, MoveHandler moveHandler){
        this.king = king;
        this.rook = rook;
        this.board = board;
        this.pieces = pieces;
        this.moveHandler = moveHandler;
    }




    public void isCastleAllowed(King king, Rook rook){
        if(preconditionsWhiteKingLeftRook(pieces, king, rook)){
            whiteKingLongCastle(king, rook);
        }
        else if(preconditionsWhiteKingRightRook(pieces, king, rook)){
            whiteKingShortCastle(king, rook);
        }
        else if(preconditionsBlackKingLeftRook(pieces, king, rook)){
            blackKingLongCastle(king, rook);
        }
        else if(preconditionsBlackKingRightRook(pieces, king, rook)){
            blackKingShortCastle(king, rook);
        }
    }


    /**
     * Method that switches the positions of the White King and the left white Rook into castle-positions.
     * @param king
     * @param rook
     */


    public void whiteKingLongCastle(King king, Rook rook){ // White king left white rook.
            //board.updateGameLayout(king, 2, 7);
            //board.updateGameLayout(rook, 3, 7);
        }

    /**
     * Method that switches the positions of the White King and the right whit Rook into castle-positions.
     * @param king
     * @param rook
     */

    public void whiteKingShortCastle(King king, Rook rook){ // White king right white rook.
            //board.updateGameLayout(king, 6, 7);
            //board.updateGameLayout(rook, 5, 7);
        }

    /**
     * Method that switches the positions of the Black King and the left black Rook into castle-positions.
     * @param king
     * @param rook
     */

    public void blackKingLongCastle (King king, Rook rook){ // Black king left black rook.
            //board.updateGameLayout(king, 2, 0);
            //board.updateGameLayout(rook, 3, 0);
        }

    /**
     * Method that switches the positions of the black King and the right black Rook into castle-positions.
     * @param king
     * @param rook
     */

    public void blackKingShortCastle (King king, Rook rook){ // Black King Right Black Rook
            //board.updateGameLayout(king, 6, 0);
            //board.updateGameLayout(rook, 5, 0);
        }


    /**
     * Method that makes sure that the space between the white King and the left white Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The white King
     * @param rook The left white Rook.
     * @return true if the space is not occupied between them.
     */

    public boolean preconditionsWhiteKingLeftRook(Piece[][] pieces, King king, Rook rook) {
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



    public boolean preconditionsWhiteKingRightRook(Piece[][] pieces, King king, Rook rook){
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


    public boolean preconditionsBlackKingLeftRook(Piece[][] pieces, King king, Rook rook){
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


    public boolean preconditionsBlackKingRightRook(Piece[][] pieces, King king, Rook rook){
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
