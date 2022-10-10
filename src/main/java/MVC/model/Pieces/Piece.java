package MVC.model.Pieces;

import javafx.scene.shape.Rectangle;


public abstract class Piece {
    public int xPos;
    public int yPos;
    public int width;
    public int height;
    public int[][] listOfLegalMoves;
    public Rectangle rect;
    private String firstImagePath;
    private String type;
    public boolean isPlayerOne;

    public Piece(int xPos, int yPos, int width, int height, String firstImagePath, String type, boolean isPlayerOne) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.firstImagePath = firstImagePath;
        this.type = type;
        this.isPlayerOne = isPlayerOne;
        this.rect = new Rectangle(xPos, yPos, width, height);

    }


    public String getFirstImagePath() {
        return firstImagePath;
    }

    public String getType() {
        return type;
    }

    public abstract boolean legalMove(int newX, int newY);

    public boolean isPlayerOne() {
        return isPlayerOne;
    }
}
