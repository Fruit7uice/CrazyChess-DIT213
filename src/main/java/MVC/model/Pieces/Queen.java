package MVC.model.Pieces;

public class Queen extends Piece{
    public Queen(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type);
    }

    public Queen(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayerOne) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type, isPlayerOne);
    }
}
