/**
 * Author David Einhaus
 */
package MVC.controller;

import MVC.model.Board;
import MVC.model.Piece;
import java.lang.Math;

import MVC.view.GUI;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import static MVC.model.Board.tileSize;

public class MouseHandler {


    private int initX;
    private int initY;
    private Board model;
    private GUI gui;

    public MouseHandler(Board model, GUI gui){
        this.model = model;
        this.gui = gui;
    }

    public void pressed(MouseEvent event, Piece p) {
        p.setFill(Color.PINK); //TODO remove this, replace with different "marked piece" indicator(?)
        initX = (int) Math.floor(event.getSceneX() / tileSize);
        initY = (int) Math.floor(event.getSceneY() / tileSize);
    }

    public void dragDetected(MouseEvent event, Piece p) {
        double x = event.getSceneX();
        double y = event.getSceneY();
        p.move(x, y);
        gui.movePiece(p, x, y); // special case of direct call to GUI as we aren't changing anything in the model


    }

    public void released(MouseEvent event, Piece p) {
            snapToGrid(event, p);
            event.consume();

    }

    // TODO implement checks for the different piece types, and collision return true if move is legal, otherwise return false
    private boolean tryMove(Piece p, int x, int y) {
        if(model.collisionDetection(x, y)){
            if(p.player1 != model.pieces[x][y].getPlayer()){
                //TODO implement capture method
            }
            return false;
        }

        return true;
    }

    private void snapToGrid(MouseEvent event, Piece p) {
        int newX = (int) Math.floor(event.getSceneX() / tileSize);
        int newY = (int) Math.floor(event.getSceneY() / tileSize);
        if(tryMove(p, newX, newY)) {
            p.move(newX, newY);
            p.setFill(Color.BLUE);
            model.setPiece(p, newX, newY);
            model.removePiece(initX, initY);// TODO remove this
            event.consume();
        }else{
            p.move(initX, initY);
            event.consume();
        }


    }

    public void dragComplete(DragEvent event, Piece p) {
        event.consume();


    }

    public void dragOver(DragEvent event, Rectangle r) {


        event.consume();
    }

    public void dragDropped(DragEvent event, Rectangle r) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if(db.hasString()){

        }
    }

}
