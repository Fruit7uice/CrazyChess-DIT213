package MVC.model.Pieces;

import MVC.model.strategies.PawnStrategy;

public class Pawn extends Piece{
    private PawnStrategy pawnStrategy;
    public Pawn(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayerOne, boolean hasMoved) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type, isPlayerOne, hasMoved);
    }

    /**
     * returns a boolean if a move is legal or not
     * @param newX the desired x position
     * @param newY the desired y position
     * @return returns a boolean if a move is legal or not
     * @author Alva Johansson
     */
    public boolean legalMove(int newX, int newY){
        if (newX != xPos || newY != yPos){// checks if we have tried to move
            if (pawnStrategy.move(xPos, yPos, newX, newY)) { //makes sure the strategy allows uss to move
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
