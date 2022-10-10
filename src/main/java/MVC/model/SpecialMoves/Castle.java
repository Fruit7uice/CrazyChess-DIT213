package MVC.model.SpecialMoves;


import MVC.model.Board;
import MVC.model.PieceLayoutFactory;
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


    public boolean tryCastle(King king, Rook rook){

        // if newX && newY == the positions of the rook and they have not moved


        return true;
    }





    public void castleWKLWR (Board board, King king, Rook rook){ // White King Left White Rook
        if (preconditionsCastleWKLWR(pieces, king, rook)) { // måste jag ta in en matris i denna metoden?
            board.updateGameLayout(king, 2, 7);
            board.updateGameLayout(rook, 3, 7);
        }
    }

    public void castleWKRWR (Board board, King king, Rook rook){ // White King Right White Rook
        if (preconditionsCastleWKLWR(pieces, king, rook)){ // måste jag ta in en matris i denna metoden?
            board.updateGameLayout(king, 6, 7);
            board.updateGameLayout(rook, 5, 7);
        }
    }

    public void castleBKLBR (Piece[][] pieces, King king, Rook rook){ // White King Right White Rook
        if (preconditionsCastleWKLWR(pieces, king, rook)){ // måste jag ta in en matris i denna metoden?
            board.updateGameLayout(king, 2, 0);
            board.updateGameLayout(rook, 3, 0);
        }
    }

    public void castleBKRBR (Piece[][] pieces, King king, Rook rook){ // White King Right White Rook
        if (preconditionsCastleWKLWR(pieces, king, rook)){ // måste jag ta in en matris i denna metoden?

            board.updateGameLayout(king, 6, 0);
            board.updateGameLayout(rook, 5, 0);

        }
    }

    public boolean whiteKingMovesLeft(King king, Piece[][] pieces){
        if(moveHandler.movePiece(0, 7, pieces, king)){
            return true;
        }
        return false;
    }

    // whiteKingMovesLeft

    // whiteKingMovesRight

    // blackKingMovesLeft

    // blackKingMovesRight




    /**
     * Method that makes sure that the space between the white King and the left white Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The white King
     * @param rook The left white Rook.
     * @return true if the space is not occupied between them.
     */


    public boolean preconditionsCastleWKLWR(Piece[][] pieces, King king, Rook rook) { // White King Left White Rook
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



    public boolean preconditionsCastleWKRWR(Piece[][] pieces, King king, Rook rook){ // White King Right White Rook
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


    public boolean preconditionsCastleBKLBR(Piece[][] pieces, King king, Rook rook){ // Black King Left Black Rook
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


    public boolean preconditionsCastleBKRBR(Piece[][] pieces, King king, Rook rook){ // Black King Right Black Rooks
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
     * Mehtod that checks if both the king and rook has moved or not.
     * @param king
     * @param rook
     * @returns true if both of them has not moved.
     */

    public boolean notMoved(King king, Rook rook) {
        return !king.hasMoved && !rook.hasMoved;
    }




}
