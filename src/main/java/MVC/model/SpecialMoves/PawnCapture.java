package MVC.model.SpecialMoves;

import MVC.model.Board;
import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;
import MVC.model.Player;

import java.util.Objects;

/**
 * @author Johannes Höher
 */


/**
 * Class of PawnCapture handles the behavior for when the pawn tries to capture enemy-pieces.
 */

public class PawnCapture {
    MoveHandler moveHandler;
    Board board;

    public PawnCapture(MoveHandler moveHandler, Board board){
        this.moveHandler = moveHandler;
        this.board = board;
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
     */
    public void playerOnePawnCaptures(Piece[][] pieceLayout, Piece piece, int newX, int newY){
        if(isPlayerOnePawnCapture(pieceLayout, piece, newX, newY)){
            board.updateLayout(pieceLayout, piece, newX, newY);
            board.setPieceLayout(pieceLayout);
        }
    }

    /**
     * Method that performs the capture for the black pawn if the move is of pawn-capture nature.
     * @param pieceLayout The layout of pieces.
     * @param piece The pawn that captures the enemy-piece.
     * @param newX The new x-coordinate of the capture-location.
     * @param newY The new y-coordinate of the capture-location.
     */
    public void playerTwoPawnCaptures(Piece[][] pieceLayout, Piece piece, int newX, int newY){
        if (isPlayerTwoPawnCapture(pieceLayout, piece, newX, newY)) {
            board.updateLayout(pieceLayout, piece, newX, newY);
            board.setPieceLayout(pieceLayout);
        }
    }


    /**
     * Generalizing the functions playerTwoPawnCapture and playerOnePawnCapture
     * @param player The player in question
     * @param piece the piece moved
     * @param newX the piece desired next move in x coordinates
     * @param newY the piece desired next move in y coordinates
     * @param layout
     * @return
     */
    public boolean isPlayerCapturing(Player player, Piece piece, int newX, int newY, Piece[][] layout) {
        if (Objects.equals(piece.getType(), "Pawn")){
            if (player.isPlayerOne()){
                return isPlayerOnePawnCapture(layout, piece, newX, newY);
            }
            else {
                return isPlayerTwoPawnCapture(layout, piece, newX, newY);
            }
        }
        return false;
    }

    public void performPawnCapture(Player player, Piece piece, int newX, int newY, Piece[][] pieceLayout) {
        if (player.isPlayerOne()){
            playerOnePawnCaptures(pieceLayout, piece, newX, newY);
        }
        else{
            playerTwoPawnCaptures(pieceLayout, piece, newX, newY);
        }
    }
}
