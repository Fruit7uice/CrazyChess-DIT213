package MVC.view;

import MVC.model.Pieces.DummyPiece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;


public class Board implements Observable{
    public static int BOARD_SIZE = 800;

    public static int tileSize = BOARD_SIZE/8;

    List<Observer> observers = new ArrayList<>();

    private Color dark = Color.rgb(65, 47, 44);  //Lighter Color
    private Color light = Color.rgb(248, 226, 184); // Darker Color


    private static String[][] NormalPieceLayout =
            {
                    {"BR", "BKn", "BB", "BQ", "BK", "BB", "BKn", "BR" },
                    {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {"WP", "WP", "WP", "WP", "WP", "WP", "WP", "WP"},
                    {"WR", "WKn", "WB", "WQ", "WK", "WB", "WKn", "WR"}
            };

    private DummyPiece[][] pieceLayout = new DummyPiece[8][8];
    private Tile[][] initTileLayout = new Tile[8][8];

    public Board(List<Observer> observers){
        initPieceLayout(pieceLayout);
        initBoardTiles(initTileLayout);
        notifyAll(observers);
    }

    public Board(Observer observer){
        initPieceLayout(pieceLayout);
        initBoardTiles(initTileLayout);
        notify(observer);
    }
    public Board() {
        initPieceLayout(pieceLayout);
        initBoardTiles(initTileLayout);
    }

    private void initBoardTiles(Tile[][] tiles){
        // MUST CALL INITPIECELAYOUT() TO MAKE TILES CONTAIN PIECES
        int counter = 0;
        DummyPiece[][] piecesLayout = getPieceLayout();
        //Create grid and add pieces
        for (int i = 0; i < tiles.length; i++) {
            counter++;
            for (int j = 0; j < tiles[i].length; j++) {
                counter++;
                Color color  = (counter % 2 == 0)? light : dark;
                DummyPiece dummyPiece = piecesLayout[i][j];
                Tile tile = new Tile((i*tileSize), (j*tileSize), tileSize, tileSize, color, dummyPiece);
                tiles[i][j] = tile;
            }
        }
    }

    private void initPieceLayout(DummyPiece[][] pieceLayout){
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {
                if (j < 2){
                    Rectangle rectangle = new Rectangle();
                    DummyPiece dp = new DummyPiece((i*tileSize), (j*tileSize), tileSize, tileSize, rectangle);
                    rectangle.setOnMouseClicked(event -> new BoardController().pressed(event, dp));
                    rectangle.setOnMouseDragged(event -> new BoardController().dragged(event, dp));
                    rectangle.setOnMouseReleased(event -> new BoardController().released(event, dp));
                    pieceLayout[i][j] = dp;
                } else if (j > 5) {
                    Rectangle rectangle = new Rectangle();
                    DummyPiece dp = new DummyPiece((i*tileSize), (j*tileSize), tileSize, tileSize, rectangle);
                    rectangle.setOnMouseClicked(event -> new BoardController().pressed(event, dp));
                    rectangle.setOnMouseDragged(event -> new BoardController().dragged(event, dp));
                    rectangle.setOnMouseReleased(event -> new BoardController().released(event, dp));
                    pieceLayout[i][j] = dp;
                }
            }
        }
    }

    public Tile[][] getCurrentBoardLayout() {
        Tile[][] layoutCopy = new Tile[initTileLayout.length][initTileLayout.length];
        for (int i = 0; i < initTileLayout.length; i++) {
            for (int j = 0; j < initTileLayout[i].length; j++) {
                layoutCopy[i][j] = initTileLayout[i][j];
            }
        }
        return layoutCopy;
    }

    public DummyPiece[][] getPieceLayout() {
        DummyPiece[][] layoutCopy = new DummyPiece[pieceLayout.length][pieceLayout.length];
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
        observer.update(initTileLayout, pieceLayout);
    }

    @Override
    public void notifyAll(List<Observer> observers) {
        for (Observer observer : observers) {
            observer.update(initTileLayout, pieceLayout);
        }
    }

}
