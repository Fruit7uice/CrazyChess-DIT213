package MVC.model;

import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;

import java.util.HashSet;

public class Player {

    private boolean isPlayerOne;

    public Piece king;
    public HashSet<Tuple<Integer, Integer>> setOfAllMoves = new HashSet<>();

    public boolean isChecked = false;

    MoveHandler moveHandler;

    public Player(boolean isPlayerOne, MoveHandler moveHandler){
        this.isPlayerOne = isPlayerOne;
        this.moveHandler = moveHandler;
    }
    /**
     * Calculates each player's list of legal moves by looping through the board and checking for each position
     * if there is a piece there and if that player is player one or not and then adding the coordinates
     * @param pieceLayout the current board layout
     */
    public HashSet<Tuple<Integer, Integer>> updatePlayerPossibleMoves(Piece[][] pieceLayout){

        HashSet<Tuple<Integer, Integer>> possibleMoves = new HashSet<>();
        for (int row = 0; row < pieceLayout.length; row++) {
            for (int col = 0; col < pieceLayout[row].length; col++) { //loops through the whole board
                Piece piece = pieceLayout[row][col];
                if(piece != null){
                    if (piece.isPlayerOne() == this.isPlayerOne){
                        moveHandler.updatePiecePossibleMoves(piece, pieceLayout); //create the pieces list of legal moves
                        /*
                        if (piece.isPlayerOne()){
                            System.out.println("White " + piece.getType() + "'s legal moves is: " + piece.setOfMoves);
                            System.out.println("Size of White " + piece.getType() + "'s moves is: " + piece.setOfMoves.size());
                        }
                        else {
                            System.out.println("Black " + piece.getType() + "'s legal moves is: " + piece.setOfMoves);
                            System.out.println("Size of Black " + piece.getType() + "'s moves is: " + piece.setOfMoves.size());

                        }
                         */
                        possibleMoves.addAll(piece.setOfMoves);
                    }
                }
            }
        }
        return possibleMoves;
    }

    public boolean isPlayerOne() {
        return isPlayerOne;
    }
}
