package MVC.model;

import MVC.model.Pieces.DummyPiece;
import MVC.model.Pieces.Piece;
import MVC.controller.BoardController;
import MVC.view.BoardGUI;
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
    //private DummyPiece[][] dummyPieceLayout;
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
    public Board() {
        //initPieceLayout(pieceLayout);
        initBoardTiles();
    }
    public Board(Piece[][] pieceLayout) {
        //initPieceLayout(pieceLayout);
        this.pieceLayout = pieceLayout;
        initBoardTiles();
    }
    public Board(BoardGUI gui, Piece[][] pieceLayout) {
        this.pieceLayout = pieceLayout;
        this.observers.add(gui);
        initBoardTiles();
    }

    public void initBoardTiles(){
        // MUST CALL INITPIECELAYOUT() TO MAKE TILES CONTAIN PIECES
        int counter = 0;
        //DummyPiece[][] piecesLayout = dummyPieceLayout;
        //Create grid and add pieces
        for (int i = 0; i < tiles.length; i++) {
            counter++;
            for (int j = 0; j < tiles[i].length; j++) {
                counter++;
                Color color  = (counter % 2 == 0)? light : dark;
                //DummyPiece dummyPiece = piecesLayout[i][j];
                //Tile tile = new Tile((i*tileSize), (j*tileSize), tileSize, tileSize, color, dummyPiece);
                Tile tile = new Tile((i*tileSize), (j*tileSize), tileSize, tileSize, color);
                tiles[i][j] = tile;
            }
        }
        notifyAllObservers();
    }

    private void initPieceLayout(DummyPiece[][] pieceLayout){
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {
                if (j < 2){
                    Rectangle rectangle = new Rectangle();
                    DummyPiece dp = new DummyPiece((i*tileSize), (j*tileSize), tileSize, tileSize, rectangle);
                    /*
                    rectangle.setOnMouseClicked(event -> new BoardController().pressed(event, dp));
                    rectangle.setOnMouseDragged(event -> new BoardController().dragged(event, dp));
                    rectangle.setOnMouseReleased(event -> new BoardController().released(event, dp));
                    pieceLayout[i][j] = dp;

                     */
                } else if (j > 5) {
                    Rectangle rectangle = new Rectangle();
                    DummyPiece dp = new DummyPiece((i*tileSize), (j*tileSize), tileSize, tileSize, rectangle);
                    /*
                    rectangle.setOnMouseClicked(event -> new BoardController().pressed(event, dp));
                    rectangle.setOnMouseDragged(event -> new BoardController().dragged(event, dp));
                    rectangle.setOnMouseReleased(event -> new BoardController().released(event, dp));
                    pieceLayout[i][j] = dp;

                     */
                }
            }
        }
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
                    rectangle.setOnMouseReleased(event -> ctrl.released(event, piece));

                } else if (i > 5) {
                    Piece piece = pieceLayout[i][j];
                    System.out.println("Here x: " + i + " y: " + j + " " + pieceLayout[i][j].rect);
                    Rectangle rectangle = pieceLayout[i][j].rect;
                    rectangle.setOnMouseClicked(event -> ctrl.pressed(event, piece));
                    rectangle.setOnMouseDragged(event -> ctrl.dragged(event, piece));
                    rectangle.setOnMouseReleased(event -> ctrl.released(event, piece));
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

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
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
