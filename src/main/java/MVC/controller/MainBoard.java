package MVC.controller;

import MVC.model.Board;
import MVC.model.Pieces.Piece;
import MVC.view.BoardGUI;
import MVC.model.PieceLayoutFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainBoard extends Application {
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 800;


    Board board;
    BoardController controller;
    BoardGUI gui;
    private Pane boardPane;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init(){
        Piece[][] pieces = PieceLayoutFactory.createMatrixLayout();
        this.gui = new BoardGUI();
        this.board = new Board(pieces);
        this.controller = new BoardController(gui, board);
        board.initMouseEventForPiece(pieces, controller);



        board.addObserver(gui);
        //board.notifyAllObservers();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Start controller: " + controller);
        board.notifyAllObservers(); // Notify observers and update their state.
        this.boardPane = new Pane(); // Creates a new pane
        System.out.println("Gui: " + gui);
        System.out.println("Board: " + board);
        //System.out.println(gui.boardLayout[0][1]);
        initBoard(boardPane); // Adds Tile layout to pane
        initPieces(boardPane); // Adds Pieces layout to pane
        board.notifyAllObservers(); // Notify observers and update their state.

        gui.drawBoard(); // Draws the board layout Graphically
        gui.drawPieces(); // Draws the piece layout Graphically
        stage.setTitle("CrazyChess");

        Scene scene = new Scene(boardPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    void initBoard(Pane pane) {
        //Loops through the matrix and adds all tiles to pane
        for (int i = 0; i < gui.boardLayout.length; i++) {
            for (int j = 0; j < gui.boardLayout[i].length; j++) {
                System.out.println("Tile: " + (i+j));
                pane.getChildren().add(gui.boardLayout[i][j]); //Adds tile at i and j
            }
        }
    }
    void initPieces(Pane pane) {
        //Loops through the matrix and adds all pieces to pane
        for (int i = 0; i < gui.pieceLayout.length; i++) {
            for (int j = 0; j < gui.pieceLayout[i].length; j++) {
                //System.out.println("Tile: " + layout[i][j]);
                if (gui.pieceLayout[i][j] != null){
                    pane.getChildren().add(gui.pieceLayout[i][j].rect); //Adds tile at i and j
                }
            }
        }
    }
}
