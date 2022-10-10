package MVC.model.Pieces;

import MVC.model.strategies.DiagonalStrategy;

public class Bishop extends Piece{
    private DiagonalStrategy diagonalStrategy;
    public Bishop(int xPos, int yPos, int width, int height, String imagePath, String type, boolean isPlayerOne) {
        super(xPos, yPos, width, height, imagePath, type, isPlayerOne);
    }


    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @return returns a boolean if a move is legal or not
     * @author Alva Johansson
     */
    public boolean legalMove(int newX, int newY){
        if (newX != xPos || newY != yPos){// checks if we have tried to move
            if (diagonalStrategy.move(xPos, yPos, newX, newY)) { //makes sure the strategy allows uss to move
                return  true;
            } else {
                return false;
            }
        }
        return false;
    }
}
