package MVC.model.Pieces;

import MVC.model.strategies.DiagonalStrategy;

public class Bishop extends Piece{
    private DiagonalStrategy diagonalStrategy;
    public Bishop(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayer1) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type, isPlayer1);
    }

    /**
     * method for retrieving the type of the piece
     * @return the type of the piece
     */
    @Override
    public String getType(){
        return "Bishop";
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
