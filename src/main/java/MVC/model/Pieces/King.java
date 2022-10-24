package MVC.model.Pieces;

import MVC.model.strategies.DiagonalStrategy;
import MVC.model.strategies.HorizontalStrategy;
import MVC.model.strategies.VerticalStrategy;


/**
 * This class holds the logic for the King and how it is supposed to be represented in our application.
 */

public class King extends Piece {

    public King(int xPos, int yPos, int width, int height, String imagePath, String type, boolean isPlayerOne, boolean hasMoved) {
        super(xPos, yPos, width, height, imagePath, type, isPlayerOne, hasMoved);
    }


    /**
     * This method makes sure that the King only moves according to its strategies.
     * @param newX the desired x position
     * @param newY the desired y position
     * @return returns a boolean if a move is legal or not
     * @author Alva Johansson && Johannes Höher
     */
    public boolean legalMove(int newX, int newY) {
        if (newX != xPos || newY != yPos) {// checks if we have tried to move
            if (Math.abs(xPos - newX) < 2 && Math.abs(yPos - newY) < 2) {
                //makes sure the strategy allows us to move
                if (HorizontalStrategy.move(xPos, yPos, newX, newY)
                        ^ VerticalStrategy.move(xPos, yPos, newX, newY) ^ DiagonalStrategy.move(xPos, yPos, newX, newY)){
                    return true;
                }
            }
            else return false;
        }
        return false;
    }


}
