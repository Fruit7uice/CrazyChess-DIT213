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

    public void changePiecePosition(Piece piece, int x, int y){
            piece.xPos = x;
            piece.yPos = y;
            piece.hasMoved = true;
            notifyAllObservers();
    }

    public Piece[][] updateLayout(Piece[][] layout, Piece piece, int newX, int newY){
        int oldX = piece.xPos;
        int oldY = piece.yPos;
        changePiecePosition(piece, newX, newY);
        layout[oldY][oldX] = null;
        layout[newY][newX] = piece;
        return layout;
    }

    public void placePieceAt(Piece p, int x, int y) {
        this.pieceLayout[y][x] = p;
    }

    public Piece[][] getCopiedLayout(){
        Piece[][] copy = new Piece[pieceLayout.length][pieceLayout.length];
        for (int row = 0; row < pieceLayout.length; row++) {
            for (int col = 0; col < pieceLayout[row].length; col++) {
                copy[row][col] = pieceLayout[row][col];
            }
        }
        return copy;

    }

    public void setPieceLayout(Piece[][] layout) {
        this.pieceLayout = layout;
        notifyAllObservers();
    }

    public static void printMatrix(Piece[][] pieceLayout){
        System.out.println("\n {");
        for (int i = 0; i < pieceLayout.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < pieceLayout[i].length; j++) {
                if (pieceLayout[i][j] != null){
                    System.out.print(pieceLayout[i][j].getType() + ", ");
                }
                else{
                    System.out.print(pieceLayout[i][j] + ", ");
                }
            }
            System.out.print("} \n");
        }
        System.out.println("}");
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
        //System.out.println("All observers has been notified");
    }

}
