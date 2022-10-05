package MVC.model.Pieces;

import MVC.model.strategies.KnightStrategy;

public class Knight extends Piece{
    private KnightStrategy knightStrategy;
    public Knight(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayerOne) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type, isPlayerOne);
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
            if (knightStrategy.move(xPos, yPos, newX, newY)) { //makes sure the strategy allows uss to move
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Knight(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayerOne) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type, isPlayerOne);
    }
}
