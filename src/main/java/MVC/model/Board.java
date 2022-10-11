package MVC.model;


import MVC.model.Pieces.Piece;
import MVC.controller.BoardController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;


public class Board implements Observable {
    public static int BOARD_SIZE = 800;

    public static int tileSize = BOARD_SIZE/8;
    List<Observer> observers = new ArrayList<>();

    private Color dark = Color.rgb(65, 47, 44);  //Lighter Color
    private Color light = Color.rgb(248, 226, 184); // Darker Color

    public Piece[][] pieceLayout;
    private Tile[][] tiles = new Tile[8][8];

    public Board(List<Observer> observers){
        //initPieceLayout(pieceLayout);
        this.observers = observers;
        initBoardTiles();
        notifyAllObservers();
    }

    public Board(Observer observer){
        //initPieceLayout(pieceLayout);
        initBoardTiles();
        observers.add(observer);
        notify(observer);
    }
    public Board(Piece[][] pieceLayout) {
        //initPieceLayout(pieceLayout);
        this.pieceLayout = pieceLayout;
        initBoardTiles();
    }

    public void initBoardTiles(){
        int counter = 0;
        //Create grid and add pieces
        for (int i = 0; i < tiles.length; i++) {
            counter++;
            for (int j = 0; j < tiles[i].length; j++) {
                counter++;
                Color color  = (counter % 2 == 0)? light : dark;
                Tile tile = new Tile((i*tileSize), (j*tileSize), tileSize, tileSize, color);
                tiles[i][j] = tile;
            }
        }
        notifyAllObservers();
    }

    public void initMouseEventForPiece(Piece[][] pieceLayout, BoardController ctrl){
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {
                if (i < 2){
                    Piece piece = pieceLayout[i][j];
                    System.out.println("Here x: " + i + " y: " + j + " " + pieceLayout[i][j].rect);
                    Rectangle rectangle = pieceLayout[i][j].rect;
                    rectangle.setOnMouseClicked(event -> ctrl.pressed(event, piece));
                    rectangle.setOnMouseDragged(event -> ctrl.dragged(event, piece));
                    rectangle.setOnMouseReleased(event -> ctrl.dragReleased(event, piece));

                } else if (i > 5) {
                    Piece piece = pieceLayout[i][j];
                    System.out.println("Here x: " + i + " y: " + j + " " + pieceLayout[i][j].rect);
                    Rectangle rectangle = pieceLayout[i][j].rect;
                    rectangle.setOnMouseClicked(event -> ctrl.pressed(event, piece));
                    rectangle.setOnMouseDragged(event -> ctrl.dragged(event, piece));
                    rectangle.setOnMouseReleased(event -> ctrl.dragReleased(event, piece));
                }
            }
        }
    }

    public Tile[][] getCurrentBoardLayout() {
        Tile[][] layoutCopy = new Tile[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                layoutCopy[i][j] = tiles[i][j];
            }
        }
        return layoutCopy;
    }

    public Piece[][] getPieceLayout() {
        Piece[][] layoutCopy = new Piece[pieceLayout.length][pieceLayout.length];
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {
                layoutCopy[i][j] = pieceLayout[i][j];
            }
        }
        return layoutCopy;
    }

    public Color getDarkColor() {
        return dark;
    }

    public Color getLightColor() {
        return light;
    }

    public void updateGameLayout(Piece piece, int newX, int newY){
        int oldX = piece.xPos;
        int oldY = piece.yPos;
        Piece tmp = pieceLayout[oldY][oldX];
        pieceLayout[newY][newX] = tmp;
        pieceLayout[oldY][oldX] = null;
        notifyAllObservers();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }


    public void updateGameLayout(Piece piece, int newX, int newY){
        int oldX = piece.xPos;
        int oldY = piece.yPos;
        Piece tmp = pieceLayout[oldY][oldX];
        pieceLayout[newY][newX] = tmp;
        pieceLayout[oldY][oldX] = null;
        notifyAllObservers();
    }


    @Override
    public void notify(Observer observer) {
        observer.update(tiles, pieceLayout);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(tiles, pieceLayout);
        }
    }

}
