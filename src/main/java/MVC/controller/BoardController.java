package MVC.controller;

import MVC.model.Piece;
import MVC.model.PieceType;
import MVC.model.Tile;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.stage.Stage;
import static MVC.model.PieceType.*;

public class BoardController extends Application {

    @FXML
    Pane pane;

    PieceType[] wTypeList = {WROOK,WKNIGHT,WBISHOP,WQUEEN,WKING,WBISHOP,WKNIGHT,WROOK,WPAWN,WPAWN,WPAWN,WPAWN,WPAWN,WPAWN,WPAWN,WPAWN};
    PieceType[] bTypeList = {BROOK,BKNIGHT,BBISHOP,BQUEEN,BKING,BBISHOP,BKNIGHT,BROOK,BPAWN,BPAWN,BPAWN,BPAWN,BPAWN,BPAWN,BPAWN,BPAWN};

    public static final int tileSize = 100;
    public static final int rows = 8;
    public static final int columns = 8;
    public static final int boardHeight = rows * tileSize;
    public static final int boardWidth = columns * tileSize;
    private MoveHandler movehandler;
    private Tile[][] board = new Tile[rows][columns];

     Group tileGroup = new Group();
     Group pieceGroup = new Group();

     private Parent createContent() {
         movehandler = new MoveHandler(this);
         Pane root = new Pane();
         root.setMaxSize(boardWidth, boardHeight);
         root.getChildren().addAll(tileGroup, pieceGroup);

         for (int y = 0; y < rows; y++) {
             for (int x = 0; x < columns; x++) {
                 Tile tile;
                 if ((x + y) % 2 == 0) {
                     tile = new Tile(x, y, Color.rgb(248, 226, 184));

                 } else {
                     tile = new Tile(x, y, Color.rgb(65, 47, 44));
                 }
                 board[x][y] = tile;
                 tileGroup.getChildren().add(tile);
             }
         }

         // Place major black pieces
         initializePieces(0, bTypeList, 0);
         // Place black pawns
         initializePieces(1, bTypeList, 8);
         // Place major white pieces
         initializePieces(rows-1, wTypeList, 0);
         // Place white pawns
         initializePieces(rows-2, wTypeList, 8);

         return root;
     }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Crazy Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Puts a piece on each tile on the given row
     * @param row the given row for the function
     */
    private void initializePieces(int row, PieceType[] typelist, int index){

        for (int col = 0; col < columns; col++) {
            Piece p = new Piece(typelist[index], col, row);
            index++;
            pieceGroup.getChildren().add(p);
            board[row][col].setPiece(p);

            p.setOnMousePressed(event -> movehandler.pressed(event, p));
            p.setOnMouseDragged(event -> movehandler.dragDetected(event, p));
            p.setOnDragDone(event -> movehandler.dragComplete(event, p));
            p.setOnMouseReleased(event -> movehandler.released(event, p));

        }

    }

    // TODO move this function to a draw class?
    public void drawPiece(Piece p, double x, double y) {
        p.draw(x, y);
    }

    public void setPiece(Piece p, int x,int y){
        board[x][y].setPiece(p);

    }

    public void removePiece(int x, int y){
        board[x][y].removePiece();
    }
}
