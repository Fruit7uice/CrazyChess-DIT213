package MVC.view;

import MVC.model.Pieces.Piece;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoardController {
    BoardGUI boardGUI;
    Board board;

    public BoardController(BoardGUI gui, Board board) {
        this.boardGUI = gui;
        this.board = board;
    }
    public BoardController(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
        this.board = new Board(boardGUI);
        System.out.println("Controller initialized and created model(Board)");
    }

    public BoardController() {
    }

    public void pressed(MouseEvent event, Piece p){
        BoardGUI gui = new BoardGUI();

        gui.drawPiece(p, Color.GRAY);
    }

    public void dragged(MouseEvent event, Piece piece){

        int newX = (int) Math.floor(event.getX());
        int newY = (int) Math.floor(event.getY());
        piece.xPos = newX;
        piece.yPos = newY;
        BoardGUI gui = new BoardGUI();
        gui.drawPiece(piece, Color.AQUA);

    }

    public void released(MouseEvent event, Piece piece){
        System.out.println(piece.xPos);
        System.out.println(piece.yPos);
        snapToGrid(event, piece);
        BoardGUI gui = new BoardGUI();
        gui.drawPiece(piece, Color.GREEN);
    }

    public void snapToGrid(MouseEvent event, Piece piece){
        int newX = (int) Math.floor(event.getX() / Board.tileSize);
        int newY = (int) Math.floor(event.getY() / Board.tileSize);

        piece.xPos = (newX);
        piece.yPos = (newY);
        piece.rect.setFill(Color.GREEN);
        System.out.println(piece.xPos);
        System.out.println(piece.yPos);
    }




}
