package MVC.snap;

import MVC.model.Piece;
import MVC.model.PieceType;
import MVC.model.Tile;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
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
    private MoveHandler movehandler;
    private Tile[][] board = new Tile[rows][columns];



     Group tileGroup = new Group();
     Group pieceGroup = new Group();


     private Parent createContent() {
         movehandler = new MoveHandler(this);
         Pane root = new Pane();
         root.setPrefSize(rows * tileSize, columns * tileSize);
         root.getChildren().addAll(tileGroup, pieceGroup);

         for (int y = 0; y < rows; y++) {
             for (int x = 0; x < columns; x++) {
                 Tile tile;
                 if ((x + y) % 2 == 0) {
                     tile = new Tile(x, y, Color.rgb(248, 226, 184));
                 } else {
                     tile = new Tile(x, y, Color.rgb(65, 47, 44));
                 }
                 tile.setOnDragOver(event -> movehandler.dragOver(event, tile));
                 board[x][y] = tile;
                 tileGroup.getChildren().add(tile);


             }
         }
             for(int i = 0; i < 2; i++){
                 initializePieces(i, bTypeList);
             }
             for (int i = rows-1; i > rows-3; i--){
                 initializePieces(i, wTypeList);
             }



         return root;
     }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Crazy Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

 /*   for(int i = 0; i < 2; i++){
        initializePieces(i, bTypeList);
    }
    for (int i = rows-1; i > rows-3; i--){
        initializePieces(i, wTypeList);
    }

    }*/



    /**
     * Puts a piece on each tile on the given row
     * @param row the given row for the function
     */
    private void initializePieces(int row, PieceType[] typelist){
        int index = 0;
        for (int col = 0; col < columns; col++) {
            Piece p = new Piece(typelist[index], col, row);
            index++;
            pieceGroup.getChildren().add(p);

            p.setOnMousePressed(event -> movehandler.pressed(event, p));
            p.setOnDragDetected(event -> movehandler.dragDetected(event, p));
            p.setOnDragDone(event -> movehandler.dragComplete(event, p));
            p.setOnMouseReleased(event -> movehandler.released(event, p));

        }

    }
}
