package MVC.model.Pieces;

public class Knight extends Piece{
    public Knight(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type);
    }
    @Override
    public String getType(){
        return "Knight";
    }
}
