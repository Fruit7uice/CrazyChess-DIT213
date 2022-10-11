package MVC.model.Pieces;

import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

// remove the javafx stuff.

public abstract class Piece {
    public int xPos;
    public int yPos;
    public int width;
    public int height;

    public List<List<Integer>> listOfLegalMoves = new ArrayList<List<Integer>>();
    public List<Integer> tupleOfCoordinates = new ArrayList<Integer>();

    private String imagePath;
    private String type;

    public boolean isPlayerOne;
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
