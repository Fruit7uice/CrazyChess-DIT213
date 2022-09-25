package MVC.view;

import MVC.model.Pieces.DummyPiece;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoardController {
    BoardGUI boardGUI;
    Board board;

    public BoardController(BoardGUI boardGUI, Board board) {
        this.boardGUI = boardGUI;
        this.board = board;
    }
    public BoardController(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }
    public BoardController() {
        //System.out.println("Controller has been initialized");
        //System.out.println(this.toString());
    }

    public void pressed(MouseEvent event, DummyPiece dp){
        dp.rect.setFill(Color.RED);
    }

    public void dragged(MouseEvent event, DummyPiece dp){
        dp.setX(event.getX()-50);
        dp.setY(event.getY()-50);
        new BoardGUI().drawPiece(dp);
    }

    public void released(MouseEvent event, DummyPiece dp){
        System.out.println(dp.getX());
        snapToGrid(event, dp);
    }

    public void snapToGrid(MouseEvent event, DummyPiece dp){
        int newX = (int) Math.floor(event.getX() / 100);
        int newY = (int) Math.floor(event.getY() / 100);
        dp.setX(newX*100);
        dp.setY(newY*100);
        dp.rect.setFill(Color.GREEN);
        System.out.println(dp.getX());
        new BoardGUI().drawPiece(dp);
    }
}
