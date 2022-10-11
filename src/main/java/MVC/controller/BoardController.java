package MVC.controller;

import MVC.model.Board;
import MVC.model.Pieces.Piece;
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
        System.out.println("Logic Piece X: " + piece.getRefPiece().xPos);
        System.out.println("Logic Piece Y: " + piece.getRefPiece().yPos);
        boardGUI.drawPieces();
        boardGUI.drawPieceToPlace(piece, Color.GRAY);
        //printMatrix();
    }

    public void dragged(MouseEvent event, WrapperPiece piece){
        //int oldX = (int) piece.getX();
        //int oldY = (int) piece.getY();
        onDrag = true;
        int newX = (int) (event.getX() - (Tile.tileSize/2));
        int newY = (int) (event.getY() - (Tile.tileSize/2));
        //System.out.println("X: " + newX + " Y: " + newY);
        boardGUI.drawPiece(piece, Color.AQUA, newX, newY);
    }

    public void dragReleased(MouseEvent event, WrapperPiece piece){
        System.out.println("Wrapper X: " + piece.getX());
        System.out.println("Wrapper Y: " + piece.getY());
        int newX = (int) Math.floor(event.getX() / Tile.tileSize);
        int newY = (int) Math.floor(event.getY() / Tile.tileSize);


        if(onDrag && true){ //replace true with: moveHandler.moveChecker(newX, newY, pieces, piece)
            onDrag = false;
            board.updateGameLayout(piece.getRefPiece(), newX, newY);
            snapToGrid(event, piece, newX, newY);
            boardGUI.drawPieceToPlace(piece, Color.GREEN);
        }
        //printMatrix();

    }

    public void snapToGrid(MouseEvent event, WrapperPiece piece, int newX, int newY){

        piece.setX(newX);
        piece.setY(newY);
        //System.out.println(piece.xPos);
        //System.out.println(piece.yPos);
    }

    /*
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
     */

/*
    Movehandler:
    moveChecker(newX, newY, Piece p){

        if(isKingChecked()){
            warnPlayer();
        }
        else{
            if(isKing(p)){
                if(castle.isMoveCastle(newX, newY, p)){
                    if(castle.isCastleAllowed(newX, newY, p)){// this method could identify the type of castle
                        castle.doCastle(newX, newY, p); // This will update the board!
                    }
                }
            }
            else{

            }
        }
        }

 */





}
