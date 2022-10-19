package MVC.model;

import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;

import java.util.HashSet;

public class Player {
    public HashSet<Tuple<Integer, Integer>> setOfAllMoves = new HashSet<Tuple<Integer, Integer>>();

    public Player(){

    }
    /**
     * Calculates each player's list of legal moves by looping through the board and checking for each position
     * if there is a piece there and if that player is player one or not and then adding the coordinates
     * @param board the current board layout
     */
    public void calcListOfLegalMovesPlayer(Piece[][] board, MoveHandler moveHandler){
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) { //loops through the whole board
                Piece piece = board[row][col];
                if(piece == null){ // is there a piece on this pos?
                    //if not don't calculate
                } else{
                    moveHandler.createSetOfPieceMoves(piece, board); //create the pieces list of legal moves
                    setOfAllMoves.addAll(piece.setOfMoves);
                }
            }

        }
    }
}
