package MVC.model.Pieces;

import MVC.model.Tuple;

import java.util.HashSet;

/**
 * The class of Piece is a superclass to all the classes that extends it. It acts as a template for how
 * the subclasses should be defined.
 */


public abstract class Piece {
    public int xPos;
    public int yPos;
    public int width;
    public int height;

    private String imagePath;
    public HashSet<Tuple<Integer, Integer>> setOfMoves = new HashSet<>();
    private String type;

    private boolean isPlayerOne;
    public boolean hasMoved;

    public Piece(int xPos, int yPos, int width, int height, String imagePath, String type, boolean isPlayerOne, boolean hasMoved) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.imagePath = imagePath;
        this.type = type;
        this.isPlayerOne = isPlayerOne;
        this.hasMoved = hasMoved;
    }

    public String getImagePath() {
        return imagePath;

    }

    public String getType() {
        return type;
    }

    public abstract boolean legalMove(int newX, int newY);

    public boolean isPlayerOne() {
        return isPlayerOne;
    }
}
