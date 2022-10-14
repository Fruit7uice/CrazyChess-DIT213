package MVC.model;

import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<Tuple<Integer, Integer>> playerOneListOfLegalMoves = new ArrayList<Tuple<Integer, Integer>>();
    public List<Tuple<Integer, Integer>> playerTwoListOfLegalMoves = new ArrayList<Tuple<Integer, Integer>>();

    public Player(){

    }
    /**
     * Calculates each player's list of legal moves by looping through the board and checking for each position
     * if there is a piece there and if that player is player one or not and then adding the coordinates
     * @param board the current board layout
     */
    public void calcListOfLegalMovesPlayer(Piece[][] board, MoveHandler moveHandler){

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) { //loops through the whole board
                Piece piece = board[x][y];
                if(piece == null){ // is there a piece on this pos?
                    //if not don't calculate
                } else{
                    moveHandler.createListOfLegalMoves(piece, board); //create the pieces list of legal moves
                    if(piece.isPlayerOne()){
                        // add the tuples from the pieces list to player ones list
                        playerOneListOfLegalMoves.addAll(piece.listOfLegalMoves);
                    }else {
                        // add the tuples from the pieces list to player twos list
                        playerTwoListOfLegalMoves.addAll(piece.listOfLegalMoves);
                    }

                }
            }

        }
    }
}
