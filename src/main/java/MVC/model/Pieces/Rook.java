package MVC.model.Pieces;

import MVC.model.strategies.HorizontalStrategy;
import MVC.model.strategies.VerticalStrategy;
/**
 * @author Alva Johansson
 */
public class Rook extends Piece{
    HorizontalStrategy horizontalStrategy;
    VerticalStrategy verticalStrategy;
    public Rook(int xPos, int yPos, int width, int height, String imagePath, String type, boolean isPlayerOne) {
        super(xPos, yPos, width, height, imagePath, type, isPlayerOne);
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
            if (horizontalStrategy.move(xPos, yPos, newX, newY) ^ verticalStrategy.move(xPos, yPos, newX, newY)) { //makes sure the strategy allows uss to move
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
