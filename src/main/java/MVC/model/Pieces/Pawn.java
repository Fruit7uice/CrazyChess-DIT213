package MVC.model.Pieces;

import MVC.model.strategies.PawnStrategy;

public class Pawn extends Piece{
    public Pawn(int xPos, int yPos, int width, int height, String imagePath, String type, boolean isPlayerOne, boolean hasMoved) {
        super(xPos, yPos, width, height, imagePath, type, isPlayerOne, hasMoved);
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
            if (PawnStrategy.move(xPos, yPos, newX, newY, hasMoved, isPlayerOne())) { //makes sure the strategy allows us to move
                hasMoved = true;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
