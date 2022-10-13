package MVC.model;


import MVC.model.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;


public class Board implements Observable {
    public static int BOARD_SIZE = 800;
    List<Observer> observers = new ArrayList<>();
    public Piece[][] pieceLayout;

    public Board(List<Observer> observers){
        this.observers = observers;
        notifyAllObservers();
    }

    public Board(Observer observer){
        observers.add(observer);
        notify(observer);
    }
    public Board(Piece[][] pieceLayout) {
        this.pieceLayout = pieceLayout;
    }


    private void updateGameLayout(Piece piece, int newRow, int newCol){
        int oldCol = piece.xPos; // Old x-coords for piece
        int oldRow = piece.yPos; // Old y-coords for piece
        Piece tmp = pieceLayout[oldRow][oldCol]; // Save a reference for piece
        pieceLayout[newRow][newCol] = tmp; // Place saved piece in new slot
        pieceLayout[oldRow][oldCol] = null; // Old slot is set to null
        notifyAllObservers();
    }


    public void changePiecePosition(Piece piece, int newRow, int newCol){
            updateGameLayout(piece, newRow, newCol);
            piece.xPos = newCol;
            piece.yPos = newRow;
            notifyAllObservers();
            //updateListOfLegalMoves();
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
        System.out.println("All observers has been notified");

    }

}
