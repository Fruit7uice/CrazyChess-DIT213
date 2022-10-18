package MVC.model.SpecialMoves;

import MVC.model.Board;
import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;

/**
 * @author Johannes Höher
 */


/**
 * Class of PawnCapture handles the behavior for when the pawn tries to capture enemy-pieces.
 */

public class PawnMove {
    MoveHandler moveHandler;

    public PawnMove(MoveHandler moveHandler){
        this.moveHandler = moveHandler;
    }

    /**
     * Method that checks if the move is of the nature of a white pawn-capture.
     * @param pieceLayout The layout of pieces.
     * @param piece The pawn that captures the enemy-piece.
     * @param newX The new x-coordinate of the capture-location
     * @param newY The new y-coordinate of the capture-location
     * @return True if the move is of a white-pawn-capture nature.
     */



    public boolean isPlayerOnePawnCapture(Piece[][] pieceLayout, Piece piece, int newX, int newY){
        int deltaX = Math.abs(newX - piece.xPos);
        int deltaY = newY - piece.yPos;
        return ((piece.isPlayerOne()) && (deltaX == 1) && (deltaY == -1) && moveHandler.isOccupied(newX, newY, pieceLayout) &&
                moveHandler.isOccupiedByEnemy(newX, newY, piece, pieceLayout));
    }

    /**
     * Method that checks if the move is of the nature of a black pawn-capture.
     * @param pieceLayout The layout of pieces.
     * @param piece The pawn that captures the enemy-piece.
     * @param newX The new x-coordinate of the capture-location.
     * @param newY The new y-coordinate of the capture-location.
     * @return True if the move is of a black-pawn-capture nature.
     */


    public boolean isPlayerTwoPawnCapture(Piece[][] pieceLayout, Piece piece, int newX, int newY){
        int deltaX = Math.abs(newX - piece.xPos);
        int deltaY = newY - piece.yPos;
        System.out.println("isPlayerTwoPawnCapture");
        return ((!piece.isPlayerOne()) && (deltaX == 1) && (deltaY == 1) && moveHandler.isOccupied(newX, newY, pieceLayout) &&
                moveHandler.isOccupiedByEnemy(newX, newY, piece, pieceLayout));
    }


    /**
     * Method that performs the capture for the white pawn if the move is of pawn-capture nature.
     * @param pieceLayout The layout of pieces.
     * @param piece The pawn that captures the enemy-piece.
     * @param newX The new x-coordinate of the capture-location.
     * @param newY The new y-coordinate of the capture-location.
     * @param board The board that gets updated.
     */


    public void playerOnePawnCaptures(Piece[][] pieceLayout, Piece piece, int newX, int newY, Board board){
        if(isPlayerOnePawnCapture(pieceLayout, piece, newX, newY)){
            board.changePiecePosition(piece, newX, newY);
        }
    }

    /**
     * Method that performs the capture for the black pawn if the move is of pawn-capture nature.
     * @param pieceLayout The layout of pieces.
     * @param piece The pawn that captures the enemy-piece.
     * @param newX The new x-coordinate of the capture-location.
     * @param newY The new y-coordinate of the capture-location.
     * @param board The board that gets updated.
     */



    public void playerTwoPawnCaptures(Piece[][] pieceLayout, Piece piece, int newX, int newY, Board board){
        if (isPlayerTwoPawnCapture(pieceLayout, piece, newX, newY)) {
            board.changePiecePosition(piece, newX, newY);
        }
    }




    public boolean isPawnIllegalVertical(Piece[][] pieceLayout, Piece piece, int newX, int newY){
        if(moveHandler.isOccupied(newX, newY, pieceLayout)){
            return true;
        }
        return false;
    }





















}
