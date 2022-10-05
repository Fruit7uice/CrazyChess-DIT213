/**
 * Author: David Einhaus
 */

package MVC.model;

import MVC.controller.MouseHandler;
import MVC.model.*;
import MVC.view.GUI;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.stage.Stage;

import javax.imageio.plugins.tiff.ExifInteroperabilityTagSet;
import java.util.List;

public class Board implements Observable {

    @FXML
    Pane pane;

    public static final int tileSize = 100;
    public static final int rows = 8;
    public static final int columns = 8;
    public static final int boardHeight = rows * tileSize;
    public static final int boardWidth = columns * tileSize;
    private Color darkTile = Color.rgb(248, 226, 184);
    private Color lightTile = Color.rgb(65, 47, 44);
    private MouseHandler mouseHandler;
    private GUI gui;
    public Tile[][] tiles = new Tile[columns][rows];
    public Piece[][] pieces = new Piece[columns][rows];
    private List<Observer> observers;

     Group tileGroup = new Group();
     Group pieceGroup = new Group();

     private Parent createContent() {
         Pane root = new Pane();
         root.setMaxSize(boardWidth, boardHeight);
         root.getChildren().addAll(tileGroup, pieceGroup);

         for (int y = 0; y < rows; y++) {
             for (int x = 0; x < columns; x++) {
                 Tile tile;
                 if ((x + y) % 2 == 0) {
                     tile = new Tile(x, y);
                     gui.drawTile(tile, x, y, darkTile);
                 } else {
                     tile = new Tile(x, y);
                     gui.drawTile(tile, x, y, lightTile);
                 }
                 tiles[x][y] = tile;
                 tileGroup.getChildren().add(tile);
             }
         }


         return root;
     }




    /**
     * Puts a piece on each tile on the given row
     * @param row the given row for the function
     */
    private void initializePieces(int row, PieceType[] typelist, int index){

        for (int col = 0; col < columns; col++) { //TODO rewrite to implement factory method
            Piece p = new Piece(typelist[index], col, row);
            index++;
            pieceGroup.getChildren().add(p);
            pieces[row][col] = p;
            gui.drawPiece(p, col, row);

            p.setOnMousePressed(event -> mouseHandler.pressed(event, p));
            p.setOnMouseDragged(event -> mouseHandler.dragDetected(event, p));
            p.setOnDragDone(event -> mouseHandler.dragComplete(event, p));
            p.setOnMouseReleased(event -> mouseHandler.released(event, p));

        }

    }


    public void setPiece(Piece p, int x,int y){
        pieces[x][y] = p;

    }
    public boolean collisionDetection(int x, int y){
        return pieces[x][y] != null;
    }

    public void removePiece(int x, int y){
        pieces[x][y] = null;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void updateObserver(Observer o, Tile[][] tileState, Piece[][] pieceState) {
        o.update(tileState, pieceState);
    }

    @Override
    public void updateAll(Tile[][] tileState, Piece[][] pieceState) {
        for (Observer o: observers
             ) {
            o.update(tileState, pieceState);
        }
    }
}
