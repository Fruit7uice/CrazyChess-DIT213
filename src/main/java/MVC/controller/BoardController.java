package MVC.controller;

import MVC.model.Board;
import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;
import MVC.view.BoardGUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoardController {
    BoardGUI boardGUI;
    Board board;
    MoveHandler moveHandler;

    public BoardController(BoardGUI gui, Board board, MoveHandler moveHandler) {
        this.boardGUI = gui;
        this.board = board;
        this.moveHandler = moveHandler;
    }


    public BoardController() {
    }

    public void pressed(MouseEvent event, Piece p){
        boardGUI.drawPieces();
        boardGUI.drawPieceInPlace(p, Color.GRAY);
    }

    public void dragged(MouseEvent event, Piece piece){
        int newX = (int) (event.getX() - (Board.tileSize/2));
        int newY = (int) (event.getY() - (Board.tileSize/2));
        //System.out.println("X: " + newX + " Y: " + newY);
        boardGUI.drawPiece(piece, Color.AQUA, newX, newY);
    }

    public void released(MouseEvent event, Piece piece){
        System.out.println(piece.xPos);
        System.out.println(piece.yPos);
        int newX = (int) Math.floor(event.getX() / Board.tileSize);
        int newY = (int) Math.floor(event.getY() / Board.tileSize);
        if(moveHandler.moveChecker(newX, newY, board.pieceLayout, piece)){
           snapToGrid(event, piece);
           boardGUI.drawPieceInPlace(piece, Color.GREEN);
        }


    }

    public void snapToGrid(MouseEvent event, Piece piece){
        int newX = (int) Math.floor(event.getX() / Board.tileSize);
        int newY = (int) Math.floor(event.getY() / Board.tileSize);
        piece.xPos = (newX);
        piece.yPos = (newY);
        piece.rect.setFill(Color.GREEN);
        //System.out.println(piece.xPos);
        //System.out.println(piece.yPos);
    }


}
