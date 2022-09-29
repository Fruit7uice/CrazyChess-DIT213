package MVC.view;

import MVC.model.Pieces.DummyPiece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class BoardGUI extends Application implements Observer {
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 800;

    private Pane boardPane;
    private BoardController controller;

    private Tile[][] boardLayout = new Tile[8][8];

    private DummyPiece[][] pieceLayout;


    public void startGUI(){ launch(); }

    @Override
    public void start(Stage stage) throws Exception {
        this.controller = new BoardController(this);
        System.out.println(boardLayout);
        System.out.println("Start controller: " + controller);
        //initializeVariables(); // 1. Initializes a pane
        this.boardPane = new Pane();
        initBoard(); // Adds Tile layout to pane
        initPieces(); // Adds Pieces layout to pane
        drawBoard(); // Draws the board layout Graphically
        drawPieces(); // Draws the piece layout Graphically
        stage.setTitle("CrazyChess");

        Scene scene = new Scene(boardPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public void setController(BoardController controller) {
        this.controller = controller;
    }

    private void initBoard() {
        //Loops through the matrix and adds all tiles to pane
        Tile[][] layout = controller.board.getCurrentBoardLayout();

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                //System.out.println("Tile: " + layout[i][j]);
                boardPane.getChildren().add(layout[i][j]); //Adds tile at i and j
            }
        }
    }

    private void initPieces() {
        //Loops through the matrix and adds all tiles to pane
        DummyPiece[][] layout = pieceLayout;

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                //System.out.println("Tile: " + layout[i][j]);
                if (layout[i][j] != null){
                    boardPane.getChildren().add(layout[i][j].rect); //Adds tile at i and j
                }

            }
        }
    }

    private void drawPieces() {
        //------INITIALIZE DUMMY PIECE ---------
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {
                DummyPiece dp = pieceLayout[i][j];
                if (dp != null){
                    drawPiece(dp);
                    System.out.println("Drawing pieces.");
                }

            }
        }
        //--------------------------------------
    }

    private void drawBoard() {
        //Board board = new Board();
        //Tile[][] boardLayout = board.getCurrentBoardLayout();
        System.out.println("Drawing board...");
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout[i].length; j++) {
                Tile tile = boardLayout[i][j];
                tile.setFill(tile.getColor());
                tile.setStroke(Color.BLACK);
                tile.setX(tile.getX());
                tile.setY(tile.getY());
            }
        }
    }


    public void drawPiece(DummyPiece p){
        p.rect.setFill(Color.GREEN);
        p.rect.setWidth(p.width);
        p.rect.setHeight(p.height);
        p.rect.setX(p.x);
        p.rect.setY(p.y);
    }

    @Override
    public void update(Tile[][] boardState, DummyPiece[][] pieceLayout) {
        //Board board = new Board();
        this.boardLayout = boardState;
        this.pieceLayout = pieceLayout;
        drawBoard();
        drawPieces();
    }
}
