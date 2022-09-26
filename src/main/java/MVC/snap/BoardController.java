package MVC.snap;

import MVC.model.Piece;
import MVC.model.Tile;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.event.EventDispatcher;
import javafx.scene.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BoardController extends Application {

    @FXML
    Pane pane;

    public static final int tileSize = 100;
    public static final int rows = 8;
    public static final int columns = 8;

     Group tileGroup = new Group();
     Group pieceGroup = new Group();

     private Parent createContent() {
         Pane root = new Pane();
         root.setPrefSize(rows * tileSize, columns * tileSize);
         root.getChildren().addAll(tileGroup, pieceGroup);

         for (int y = 0; y < rows; y++) {
             for (int x = 0; x < columns; x++) {
                 Tile tile;
                 if ((x + y) % 2 == 0) {
                     tile = new Tile(x, y, Color.BLACK);
                 } else {
                     tile = new Tile(x, y, Color.WHITE);
                 }
                 tileGroup.getChildren().add(tile);


             }
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



    @FXML
    public void initializeBoard(){







    }



 /*   for(int i = 0; i < 2; i++){
        initializePieces(i);
    }
    for (int i = rows-1; i > rows-3; i--){
        initializePieces(i);
    }

    }*/



    /**
     * Puts a piece on each tile on the given row
     * @param row the given row for the function
     */
    private void initializePieces(int row){
        for (int col = 0; col < columns; col++) {
            Circle c = new Circle();
            c.setFill(Color.GREEN); //TODO make color "Color.TRANSPARENT" when everything is working as intended
            double radius = tileSize / 10.0;
            Piece p = new Piece(row, col, radius, c);


            c.setOnMousePressed(event -> EventHandler.pressed(event, p));
            c.setOnDragDetected(event -> EventHandler.dragDetected(event, p));
            c.setOnDragDone(event -> EventHandler.dragComplete(event, p));
            c.setOnMouseReleased(event -> EventHandler.released(event, p));


            GridPane.setHalignment(c, HPos.CENTER);

            p.draw();
        }

    }
}
