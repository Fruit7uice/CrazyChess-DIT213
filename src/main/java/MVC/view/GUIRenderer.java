package MVC.view;

import MVC.controller.BoardController;
import MVC.model.Piece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import MVC.model.Tile;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static MVC.controller.BoardController.tileSize;


public class GUIRenderer {

    private BoardController controller;

    public GUIRenderer(BoardController controller){
        this.controller = controller;
    }

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
}


