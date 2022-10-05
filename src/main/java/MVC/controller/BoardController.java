package MVC.controller;

import MVC.model.Board;
import MVC.model.Pieces.Piece;
import MVC.view.BoardGUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoardController {
    BoardGUI boardGUI;
    Board board;
    boolean onDrag;

    public BoardController(BoardGUI gui, Board board) {
        this.boardGUI = gui;
        this.board = board;
    }


    public BoardController() {
    }

    public void pressed(MouseEvent event, Piece p){
        boardGUI.drawPieces();
        boardGUI.drawPieceInPlace(p, Color.GRAY);
        printMatrix();
    }

    public void dragged(MouseEvent event, Piece piece){
        onDrag = true;
        int newX = (int) (event.getX() - (Board.tileSize/2));
        int newY = (int) (event.getY() - (Board.tileSize/2));
        //System.out.println("X: " + newX + " Y: " + newY);
        boardGUI.drawPiece(piece, Color.AQUA, newX, newY);
    }

    public void dragReleased(MouseEvent event, Piece piece){
        System.out.println(piece.xPos);
        System.out.println(piece.yPos);
        int newX = (int) Math.floor(event.getX() / Board.tileSize);
        int newY = (int) Math.floor(event.getY() / Board.tileSize);
        System.out.println("REALESED DRAAAAAAAAAAAAAAAAAAAG");

        if(onDrag && true){ //replace true with: moveHandler.moveChecker(newX, newY, pieces, piece)
            onDrag = false;
            board.updateGameLayout(piece, newX, newY);
            snapToGrid(event, piece, newX, newY);
            boardGUI.drawPieceInPlace(piece, Color.GREEN);


        }
        printMatrix();

    }

    public void snapToGrid(MouseEvent event, Piece piece, int newX, int newY){

        piece.xPos = (newX);
        piece.yPos = (newY);
        piece.rect.setFill(Color.GREEN);
        //System.out.println(piece.xPos);
        //System.out.println(piece.yPos);
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
