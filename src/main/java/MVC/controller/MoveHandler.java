package MVC.controller;

import MVC.controller.BoardController;
import MVC.model.Piece;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import static MVC.controller.BoardController.tileSize;

public class MoveHandler {


    private int initX;
    private int initY;
    private BoardController controller;

    public MoveHandler(BoardController controller){
        this.controller = controller;
    }

    public void pressed(MouseEvent event, Piece p) {
        p.setFill(Color.PINK);
        System.out.println(p.getType().toString());
        initX = (int) Math.floor(event.getSceneX());
        initY = (int) Math.floor(event.getSceneY());
    }

    public void dragDetected(MouseEvent event, Piece p) {
        //TODO make piece follow mouse
        double x = event.getSceneX();
        double y = event.getSceneY();
        p.move(x, y);
        controller.drawPiece(p, x, y);


    }

    public void released(MouseEvent event, Piece p) {
            snapToGrid(event, p);
            event.consume();

    }

    private static boolean tryMove(Piece p, double x,double y) {
        // TODO implement checks for the different piece types, and collision return true if move is legal, otherwise return false
        return true;
    }

    private void snapToGrid(MouseEvent event, Piece p) {
        int newX = (int) Math.floor(event.getSceneX() / tileSize);
        int newY = (int) Math.floor(event.getSceneY() / tileSize);
        if(tryMove(p, newX, newY)) {
            p.move(newX, newY);
            p.setFill(Color.BLUE);
            controller.setPiece(p, newX, newY);
            controller.removePiece(initX, initY);// TODO remove this
            event.consume();
        }else{
            p.abortMove();
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
