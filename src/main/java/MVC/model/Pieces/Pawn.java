package MVC.model.Pieces;

import MVC.model.DummyTile;
import MVC.model.strategies.PawnStrategy;

public class Pawn extends Piece{
    PawnStrategy pawnStrategy;
    DummyTile tile;
    public Pawn(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayer1) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type, isPlayer1);
    }
    @Override
    public String getType(){
        return "Pawn";
    }

    /**
     * returns a boolean if a move is legal or not
     * @param newX the desired x position
     * @param newY the desired y position
     * @return returns a boolean if a move is legal or not
     */
    public boolean legalMove(int newX, int newY){
        if (newX != xPos || newY != yPos){// checks if we have tried to move
            if (pawnStrategy.move(xPos, yPos, newX, newY)) { //makes sure the strategy allows uss to move
                if (!isOccupied(tile)){ //is the tile not occupied
                    return true;
                } else { // tile is occupied
                    if (isOccupiedByEnemy(tile)) { //is the piece my enemy?
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
