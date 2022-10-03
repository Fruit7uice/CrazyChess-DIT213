package MVC.model.Pieces;

import MVC.model.strategies.DiagonalyStrategy;

public class Bishop extends Piece{
    DiagonalyStrategy diagonalyStrategy;
    public Bishop(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type);
    }

    @Override
    public String getType(){
        return "Bishop";
    }
}
