package MVC.model.Pieces;

import MVC.model.strategies.DiagonalStrategy;
import MVC.model.strategies.HorizontalStrategy;
import MVC.model.strategies.VerticalStrategy;

public class King extends Piece {
    private HorizontalStrategy horizontalStrategy;
    private VerticalStrategy verticalStrategy;
    private DiagonalStrategy diagonalStrategy;

    public King(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayerOne, boolean hasMoved) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type, isPlayerOne, hasMoved);
    }


    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @return returns a boolean if a move is legal or not
     * @author Alva Johansson && Johannes Höher
     */
    public boolean legalMove(int newX, int newY) {
        if (newX != xPos || newY != yPos) {// checks if we have tried to move
            if (Math.abs(xPos - newX) < 2 && Math.abs(yPos - newY) < 2) {
                if (horizontalStrategy.move(xPos, yPos, newX, newY) ^ verticalStrategy.move(xPos, yPos, newX, newY) ^ diagonalStrategy.move(xPos, yPos, newX, newY)) { //makes sure the strategy allows us to move
                    return true;
                } else {
                    return false;
                }
            }else
            return false;
        }
        return false;
    }


}
