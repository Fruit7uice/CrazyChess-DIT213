package MVC.model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile extends Rectangle {

    private int xPos;
    private int yPos;
    private double height;
    private double width;
    private Color c;
    /*private Rectangle r;*/


    public Tile(int x, int y, Color c){
        this.xPos = x;
        this.yPos = y;
        this.c = c;
    }

}
