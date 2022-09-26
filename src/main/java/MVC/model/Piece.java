package MVC.model;

import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {

    private double x;
    private double y;
    private double radius;
    public Circle c;

    public Piece(double x, double y, double radius, Circle c) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.c = c;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setColor(Color color){
        c.setFill(color);
    }

    public void draw(){
        c.setRadius(radius);
    }

    public Dragboard StartDragAndDrop(TransferMode[] any) {
        return null;
    }
}
