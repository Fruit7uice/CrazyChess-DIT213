package MVC.model.Pieces;

import MVC.model.DummyTile;
import MVC.model.strategies.HorizontalStrategy;
import MVC.model.strategies.VerticalStrategy;
/**
 * @author Alva Johansson
 */
public class Rook extends Piece{
    DummyTile tile;
    HorizontalStrategy horizontalStrategy;
    VerticalStrategy verticalStrategy;
    public Rook(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayer1) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type, isPlayer1);
    }

    /**
     * method for retrieving the type of the piece
     * @return the type of the piece
     */
    @Override
    public String getType(){
        return "Rook";
    }

    /**
     * updates the position of a piece
     * @param newX the desired x position
     * @param newY the desired y position
     */


    /**
     * returns a boolean if a move is legal or not
     * @param newX the desired x position
     * @param newY the desired y position
     * @return returns a boolean if a move is legal or not
     */
    public boolean legalMove(int newX, int newY, Piece[][] board){
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
