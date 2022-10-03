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
     * method for retriving the type of the piece
     * @return
     */
    @Override
    public String getType(){
        return "Rook";
    }

    /**
     * updates the position of a piece
     * @param newX
     * @param newY
     */
    @Override
    public void move(int newX, int newY){
        if(legalMove(newX, newY)){ // updates the position if the move is legal
            xPos = newX;
            yPos = newY;
        }
    }
    public boolean legalMove(int newX, int newY){
        if (newX != xPos || newY != yPos){// checks if we have tried to move
            if (horizontalStrategy.move(xPos, yPos, newX, newY) ^ horizontalStrategy.move(xPos, yPos, newX, newY)) { //makes sure the strategy allows uss to move
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

    public boolean isOccupied(DummyTile dummyTile){
           if(dummyTile.getIsOccupied()){
               return true;
           }
           return false;
        }
    public boolean isOccupiedByEnemy(DummyTile dummyTile){
        boolean piecePlayer1 = dummyTile.getIsPlayer1();
        if(piecePlayer1 == this.isPlayer1()){
            return false;
        }
        return true;
    }
    public void killEnemyPiece (){

    }



}
