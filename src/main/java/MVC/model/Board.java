package MVC.model;


import MVC.model.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;


public class Board implements Observable {
    public static int BOARD_SIZE = 800;


    List<Observer> observers = new ArrayList<>();

    /*
    private Color darkTile = Color.rgb(65, 47, 44);  //Lighter Color
    private Color lightTile = Color.rgb(248, 226, 184); // Darker Color
     */

    public Piece[][] pieceLayout;
    //private Tile[][] tiles = new Tile[8][8];

    public Board(List<Observer> observers){
        //initPieceLayout(pieceLayout);
        this.observers = observers;
        //initBoardTiles();
        notifyAllObservers();
    }

    public Board(Observer observer){
        observers.add(observer);
        notify(observer);
    }
    public Board(Piece[][] pieceLayout) {
        this.pieceLayout = pieceLayout;
    }



    /*
    public Tile[][] getCurrentBoardLayout() {
        Tile[][] layoutCopy = new Tile[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                layoutCopy[i][j] = tiles[i][j];
            }
        }
        return layoutCopy;
    }
     */

    public Piece[][] getPieceLayout() {
        Piece[][] layoutCopy = new Piece[pieceLayout.length][pieceLayout.length];
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {
                layoutCopy[i][j] = pieceLayout[i][j];
            }
        }
        return layoutCopy;
    }


    private void updateGameLayout(Piece piece, int newX, int newY){
        int oldX = piece.xPos; // Old x-coords for piece
        int oldY = piece.yPos; // Old y-coords for piece
        Piece tmp = pieceLayout[oldY][oldX]; // Save a reference for piece
        pieceLayout[newY][newX] = tmp; // Place saved piece in new slot
        pieceLayout[oldY][oldX] = null; // Old slot is set to null
        notifyAllObservers();
    }

    public void changePiecePosition(Piece piece, int newX, int newY){
        updateGameLayout(piece, newX, newY);
        piece.xPos = newX;
        piece.yPos = newY;
        notifyAllObservers();
    }
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notify(Observer observer) {
        observer.update(pieceLayout);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(pieceLayout);
        }
        System.out.println("all observers has been notified");

    }

}
