package MVC.model.SpecialMoves;

import MVC.model.Board;
import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;
import MVC.model.Pieces.Pawn;

public class PawnCapture {
    MoveHandler moveHandler;

    public PawnCapture(MoveHandler moveHandler){
        this.moveHandler = moveHandler;
    }




    public boolean isPlayerOnePawnCapture(Piece[][] pieceLayout, Piece piece, int startX, int startY, int newX, int newY){
        int deltaX = Math.abs(newX - startX);
        int deltaY = newY - startY;
        return ((piece.isPlayerOne()) && (deltaX == 1) && (deltaY == -1) && moveHandler.isOccupied(newX, newY, pieceLayout) && moveHandler.isOccupiedByEnemy(newX, newY, piece, pieceLayout));
    }



    public boolean isPlayerTwoPawnCapture(Piece[][] pieceLayout, Piece piece, int startX, int startY, int newX, int newY){
        int deltaX = Math.abs(newX - startX);
        int deltaY = newY - startY;
        return ((!piece.isPlayerOne()) && (deltaX == 1) && (deltaY == -1) && moveHandler.isOccupied(newX, newY, pieceLayout) && moveHandler.isOccupiedByEnemy(newX, newY, piece, pieceLayout));
    }


    public void playerOnePawnCapture(){


    }

    public void playerTwoPawnCapture(){


    }














}
