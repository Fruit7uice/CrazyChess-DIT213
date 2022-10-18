package MVC.model;

import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<Tuple<Integer, Integer>> playerOneListOfLegalMoves = new ArrayList<Tuple<Integer, Integer>>();
    public List<Tuple<Integer, Integer>> playerTwoListOfLegalMoves = new ArrayList<Tuple<Integer, Integer>>();
    private boolean isPlayerOne;
    public Player(boolean isPlayerOne){
        this.isPlayerOne = isPlayerOne;
    }
    /**
     * Calculates each player's list of legal moves by looping through the pieceLayout and checking for each position
     * if there is a piece there and if that player is player one or not and then adding the coordinates
     * @param pieceLayout the current pieceLayout layout
     */
    public void calcListOfLegalMovesPlayer(Piece[][] pieceLayout, MoveHandler moveHandler){
        for (int x = 0; x < pieceLayout.length; x++) {
            for (int y = 0; y < pieceLayout.length; y++) { //loops through the whole pieceLayout
                Piece piece = pieceLayout[x][y];
                if(piece == null){ // is there a piece on this pos?
                    //if not don't calculate
                } else{
                    moveHandler.createListOfLegalMoves(piece, pieceLayout); //create the pieces list of legal moves
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

    public boolean isPlayerOne() {
        return isPlayerOne;
    }
}
