package MVC.controller;

import MVC.model.Board;
import MVC.model.Pieces.MoveHandler;

import MVC.view.BoardGUI;
import MVC.view.WrapperPiece;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static MVC.view.Tile.tileSize;

public class BoardController {
    BoardGUI boardGUI;
    Board board;
    MoveHandler moveHandler;
    boolean onDrag;
    Pane pane;


    public BoardController(BoardGUI gui, Board board, MoveHandler moveHandler, Pane boardPane) {
        this.boardGUI = gui;
        this.board = board;
        this.moveHandler = moveHandler;
        this.pane = boardPane;
    }


    /**
     * Redraws all pieces and then redraw the piece pressed on.
     * @param piece is the WrapperPiece used to represent the logical Piece
     */
    public void pressed(WrapperPiece piece){
        // CHECK REFERENCE PIECE COORDS
        System.out.println("Logic Piece Col: " + piece.getRefPiece().xPos);
        System.out.println("Logic Piece Row: " + piece.getRefPiece().yPos);
        // CHECK WRAPPER PIECE COORDS
        System.out.println("Wrapper Piece Col: " + piece.getX());
        System.out.println("Wrapper Piece Row: " + piece.getY());
        boardGUI.drawPieces();
        boardGUI.drawWrapperAfterIndex(piece, Color.GRAY, Color.RED);
        printMatrix(); // Here for testing and making sure the model is updated when gui sends an event
    }

    /**
     * Calculates and draws the WrapperPiece position after the mouse's position
     * @param event is the Mouse Event which is used to get x and y of the mouse position
     * @param piece is the WrapperPiece used to represent the logical Piece
     */
    public void dragged(MouseEvent event, WrapperPiece piece){
        if (!onDrag){
            Group g = (Group) pane.getChildren().get(1);
            int i = g.getChildren().indexOf(piece);
            g.getChildren().get(i).toFront();
        }
        onDrag = true;
        int newX = (int) (event.getX() - (tileSize/2));
        int newY = (int) (event.getY() - (tileSize/2));

        //System.out.println("X: " + newX + " Y: " + newY);
        boardGUI.drawWrapperPiece(piece, Color.AQUA, Color.AQUA, newX, newY);
    }
    /**
     * If the release is after a drag event, it will move the piece and update the model.
     * @param event is the Mouse Event which is used to get x and y of the mouse position
     * @param piece is the WrapperPiece used to represent the logical Piece
     */
    public void released(MouseEvent event, WrapperPiece piece){
        int newX = (int) Math.floor(event.getX() / tileSize); // Index x
        int newY = (int) Math.floor(event.getY() / tileSize); // Index y
        // moveHandler.moveChecker(newCol, newRow, piece, board.pieceLayout)
        if(onDrag && true){ //replace true with: moveHandler.moveChecker(newCol, newRow, pieces, piece)
            onDrag = false;
            snapPieceToGrid(piece, newX, newY);
            boardGUI.drawWrapperAfterIndex(piece, Color.GREEN, Color.rgb(1,1,1,0));
        }
        printMatrix(); // Here for testing and making sure the model is updated when gui sends an event
    }

    public void snapPieceToGrid(WrapperPiece piece, int newX, int newY){
        if (!(piece.getRefPiece().xPos == newX && piece.getRefPiece().yPos == newY)){
            board.changePiecePosition(piece.getRefPiece(), newX, newY); // Updates board as well
        }

    }



    /**
     * Used to Test and debug the relations between gui -> controller -> model,
     * making sure when an event has happened it should update the model.
     */
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
