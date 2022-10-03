package MVC.model.Pieces;

public class Pawn extends Piece{

    public Pawn(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type);
    }
    @Override
    public String getType(){
        return "Pawn";
    }
}
