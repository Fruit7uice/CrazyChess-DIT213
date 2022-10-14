package MVC.model.Pieces;

import MVC.model.strategies.DiagonalStrategy;
import MVC.model.strategies.HorizontalStrategy;
import MVC.model.strategies.VerticalStrategy;

public class Queen extends Piece{
    public Queen(int xPos, int yPos, int width, int height, String imagePath, String type, boolean isPlayerOne, boolean hasMoved) {
        super(xPos, yPos, width, height, imagePath, type, isPlayerOne, hasMoved);
    }

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @return returns a boolean if a move is legal or not
     * @author Alva Johansson
     */
    public boolean legalMove(int newX, int newY){

        if (newX != xPos || newY != yPos){// checks if we have tried to move
            //makes sure the strategy allows uss to move
            if (HorizontalStrategy.move(xPos, yPos, newX, newY) || VerticalStrategy.move(xPos, yPos, newX, newY)
                    || DiagonalStrategy.move(xPos, yPos, newX, newY)){
                return true;
            }
            else {
                return false;
            }

        }
        return false;
    }
}
