package MVC.model.Pieces;

import MVC.model.DummyTile;
import MVC.model.strategies.DiagonalStrategy;

/**
 * @author Alva Johansson
 */

public class Bishop extends Piece{
    DummyTile dummyTile;
    DiagonalStrategy diagonalyStrategy;
    public Bishop(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayer1) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type, isPlayer1);
    }

    @Override
    public String getType(){
        return "Bishop";
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
     * @param newX the desired x position
     * @param newY the desired y position
     * @return returns a boolean if a move is legal or not
     * @author Alva Johansson
     */
    public boolean legalMove(int newX, int newY, Piece[][] board){
        if (newX != xPos || newY != yPos){// checks if we have tried to move
            if (diagonalyStrategy.move(xPos, yPos, newX, newY)) { //makes sure the strategy allows uss to move
                if (!isOccupied(newX, newY, board)){ //is the tile not occupied
                    return true;
                } else { // tile is occupied
                    if (isOccupiedByEnemy(newX, newY, board)) { //is the piece my enemy?
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
