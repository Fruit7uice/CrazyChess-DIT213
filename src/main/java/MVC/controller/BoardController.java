package MVC.controller;

import MVC.model.Board;
import MVC.view.BoardGUI;
import MVC.view.Tile;
import MVC.view.WrapperPiece;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoardController {
    BoardGUI boardGUI;
    Board board;
    boolean onDrag;

    public BoardController(BoardGUI gui, Board board) {
        this.boardGUI = gui;
        this.board = board;
        System.out.println("Inside controller const: BoardGUI: " + boardGUI);
    }


    public BoardController() {
    }

    public void pressed(MouseEvent event, WrapperPiece piece){
        // CHECK REFERENCE PIECE COORDS
        System.out.println("Logic Piece X: " + piece.getRefPiece().xPos);
        System.out.println("Logic Piece Y: " + piece.getRefPiece().yPos);
        // CHECK WRAPPER PIECE COORDS
        System.out.println("Wrapper Piece X: " + piece.getX());
        System.out.println("Wrapper Piece Y: " + piece.getY());
        boardGUI.drawPieces();
        boardGUI.drawWrapperAfterIndex(piece, Color.GRAY);
        printMatrix();
    }

    public void dragged(MouseEvent event, WrapperPiece piece){
        onDrag = true;
        int newX = (int) (event.getX() - (Tile.tileSize/2));
        int newY = (int) (event.getY() - (Tile.tileSize/2));
        //System.out.println("X: " + newX + " Y: " + newY);
        boardGUI.drawWrapperPiece(piece, Color.AQUA, newX, newY);
    }

    public void released(MouseEvent event, WrapperPiece piece){

        if(onDrag && true){ //replace true with: moveHandler.moveChecker(newX, newY, pieces, piece)
            onDrag = false;
            int newX = (int) Math.floor(event.getX() / Tile.tileSize); // Index x
            int newY = (int) Math.floor(event.getY() / Tile.tileSize); // Index y

            snapPieceToGrid(piece, newX, newY);
            boardGUI.drawWrapperAfterIndex(piece, Color.GREEN);
        }
        printMatrix();

    }

    public void snapPieceToGrid(WrapperPiece piece, int newX, int newY){
        board.changePiecePosition(piece.getRefPiece(), newX, newY); // Updates board as well
    }


    void printMatrix(){
        System.out.println("\n {");
        for (int i = 0; i < board.pieceLayout.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < board.pieceLayout[i].length; j++) {
                if (board.pieceLayout[i][j] != null){
                    System.out.print(board.pieceLayout[i][j].getType() + ", ");
                }
                else{
                    System.out.print(board.pieceLayout[i][j] + ", ");
                }
            }
            System.out.print("} \n");
        }
        System.out.println("}");
    }

}
