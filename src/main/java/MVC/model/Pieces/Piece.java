package MVC.model.Pieces;

import javafx.scene.shape.Rectangle;


public abstract class Piece {
    public int xPos;
    public int yPos;
    public int width;
    public int height;
    public Rectangle rect;
    private String firstImagePath;
    private String secondImagePath;
    private String type;
    public boolean isPlayerOne;
    public boolean hasMoved;

    public Piece(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayerOne, boolean hasMoved) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.firstImagePath = firstImagePath;
        this.secondImagePath = secondImagePath;
        this.type = type;
        this.isPlayerOne = isPlayerOne;
        this.rect = new Rectangle(xPos, yPos, width, height);
        this.hasMoved = hasMoved;
    }


    public String getFirstImagePath() {
        return firstImagePath;
    }

    public String getSecondImagePath() {
        return secondImagePath;
    }

    public String getType() {
        return type;
    }

    public abstract boolean legalMove(int newX, int newY);

    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    void move(int newX, int newY) {

    }

}
