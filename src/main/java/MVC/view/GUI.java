package MVC.view;

import MVC.model.Board;
import MVC.model.Observer;
import MVC.model.Piece;
import javafx.application.Application;
import javafx.scene.paint.Color;
import MVC.model.Tile;

import java.util.Observable;

import static MVC.model.Board.tileSize;


public class GUI implements Observer {





    public void drawTile(Tile tile, int x, int y, Color c){
        tile.setWidth(tileSize);
        tile.setHeight(tileSize);
        tile.setFill(c);
        tile.relocate(x * tileSize, y * tileSize);
    }

    public void drawPiece(Piece p, double x, double y){
        p.setHeight(tileSize);
        p.setWidth(tileSize);
        p.relocate(x * tileSize, y * tileSize);
    }

    public void movePiece(Piece p, double x, double y){
        p.setHeight(tileSize);
        p.setWidth(tileSize);
        p.relocate(x - tileSize / 2, y - tileSize / 2);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void update(Tile[][] tileState, Piece[][] pieceState) {


    }
}


