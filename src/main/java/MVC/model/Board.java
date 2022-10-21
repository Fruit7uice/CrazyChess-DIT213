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

    /**
     * Changes a piece's x and y position.
     * @param piece that is going to be changed
     * @param x coordinate
     * @param y coordinate
     */
    public void changePiecePosition(Piece piece, int x, int y){
            piece.xPos = x;
            piece.yPos = y;
            piece.hasMoved = true;
            notifyAllObservers();
    }

    /**
     * Updates the position of the piece in the matrix and also changes the piece's Coordinates.
     * @param layout a matrix containing pieces
     * @param piece the piece to be changed
     * @param newX The new X position
     * @param newY The new Y position
     * @return the changed layout
     */
    public Piece[][] updateLayout(Piece[][] layout, Piece piece, int newX, int newY){
        int oldX = piece.xPos;
        int oldY = piece.yPos;
        changePiecePosition(piece, newX, newY);
        layout[oldY][oldX] = null;
        layout[newY][newX] = piece;
        return layout;
    }

    /**
     * Forces a piece to be put at a specified position in the matrix.
     * @param p is the piece that is forced into the matrix
     * @param x is the desired column
     * @param y is the desired row
     */
    public void placePieceAt(Piece p, int x, int y) {
        this.pieceLayout[y][x] = p;
    }

    /**
     * Creates a new matrix with the same pieces as the original matrix.
     * @return the newly created matrix.
     */
    public Piece[][] getCopiedLayout(){
        Piece[][] copy = new Piece[pieceLayout.length][pieceLayout.length];
        for (int row = 0; row < pieceLayout.length; row++) {
            for (int col = 0; col < pieceLayout[row].length; col++) {
                copy[row][col] = pieceLayout[row][col];
            }
        }
        return copy;

    }

    /**
     * Overwrites the original layout.
     * @param layout that overwrites.
     */
    public void setPieceLayout(Piece[][] layout) {
        this.pieceLayout = layout;
        notifyAllObservers();
    }

    /**
     * Displays the matrix in the Terminal.
     * @param pieceLayout matrix to be displayed.
     */
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

    /**
     * Adds an observer to list.
     * @param o
     */
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Calls for the observers update method.
     * @param observer to be notified.
     */
    @Override
    public void notify(Observer observer) {
        observer.update(pieceLayout);
    }

    /**
     * Loops through list of observers and notifies them.
     */
    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(pieceLayout);
        }
        //System.out.println("All observers has been notified");
    }

}
