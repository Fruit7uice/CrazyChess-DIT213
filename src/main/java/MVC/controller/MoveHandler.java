package MVC.controller;

import MVC.model.Piece;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import static MVC.controller.Chess.tileSize;

public class MoveHandler {


    private int initX;
    private int initY;
    private Chess controller;

    public MoveHandler(Chess controller){
        this.controller = controller;
    }

    public void pressed(MouseEvent event, Piece p) {
        p.setFill(Color.PINK);
        System.out.println(p.getType().toString());
        initX = (int) Math.floor(event.getSceneX() / tileSize);
        initY = (int) Math.floor(event.getSceneY() / tileSize);
    }

    public void dragDetected(MouseEvent event, Piece p) {
        double x = event.getSceneX();
        double y = event.getSceneY();
        p.move(x, y);
        controller.movePiece(p, x, y);


    }

    public void released(MouseEvent event, Piece p) {
            snapToGrid(event, p);
            event.consume();

    }

    // TODO implement checks for the different piece types, and collision return true if move is legal, otherwise return false
    private boolean tryMove(Piece p, int x, int y) {
        if(controller.collisionDetection(x, y)){
            if(p.player1 != controller.board[x][y].getPieceColor()){
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
            controller.setPiece(p, newX, newY);
            controller.removePiece(initX, initY);// TODO remove this
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
