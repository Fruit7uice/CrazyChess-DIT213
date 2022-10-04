package MVC.model.Pieces;

import MVC.model.DummyTile;
import MVC.model.strategies.HorizontalStrategy;
import MVC.model.strategies.VerticalStrategy;

public class Rook extends Piece{
    DummyTile dummyTile;
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
    @Override
    public void move(int newX, int newY){
        if(legalMove(newX, newY)){ // updates the position if the move is legal
            xPos = newX;
            yPos = newY;
        }
    }

    /**
     * returns a boolean if a move is legal or not
     * @param newX the desired x position
     * @param newY the desired y position
     * @return returns a boolean if a move is legal or not
     */
    public boolean legalMove(int newX, int newY){
        if (newX != xPos || newY != yPos){// checks if we have tried to move
            if (horizontalStrategy.move(xPos, yPos, newX, newY) ^ verticalStrategy.move(xPos, yPos, newX, newY)) { //makes sure the strategy allows uss to move
                if (!isOccupied(dummyTile)){ //is the tile not occupied
                    return true;
                } else { // tile is occupied
                    if (isOccupiedByEnemy(dummyTile)) { //is the piece my enemy?
                        killEnemyPiece();
                        return true;
                    } else {
                        return false; //cant move because my teammate is in the way
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }
}
