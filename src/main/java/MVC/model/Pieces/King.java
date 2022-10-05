package MVC.model.Pieces;

import MVC.model.strategies.KingStrategy;



public class King extends Piece{
    public boolean hasMoved = false;
    private KingStrategy kingStrategy;
    public King(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, Boolean isPlayer1, Boolean hasMoved) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type, isPlayer1);
        this.hasMoved = hasMoved;
    }
    /**
     * method for retrieving the type of the piece
     * @return the type of the piece
     */
    @Override
    public String getType(){
        return "King";
    }

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @return returns a boolean if a move is legal or not
     * @author Alva Johansson
     */
    public boolean legalMove(int newX, int newY){
        if (newX != xPos || newY != yPos){// checks if we have tried to move
            if (kingStrategy.move(xPos, yPos, newX, newY)) { //makes sure the strategy allows uss to move
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
