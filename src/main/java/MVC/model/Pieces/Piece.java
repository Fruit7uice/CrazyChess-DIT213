package MVC.model.Pieces;

import MVC.model.Tuple;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

// remove the javafx stuff.

public abstract class Piece {
    public int xPos;
    public int yPos;
    public int width;
    public int height;
    public List<Tuple<Integer, Integer>> listOfLegalMoves = new ArrayList<Tuple<Integer, Integer>>();
    //public Tuple tupleOfCoordinates = new Tuple(Integer, Integer );
    public Rectangle rect;
    private String firstImagePath;
    private String secondImagePath;
    private String type;

    public boolean isPlayerOne;

    public Piece(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type, boolean isPlayerOne) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.firstImagePath = firstImagePath;
        this.secondImagePath = secondImagePath;
        this.type = type;
        this.isPlayerOne = isPlayerOne;
        this.rect = new Rectangle(xPos, yPos, width, height);
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
}
