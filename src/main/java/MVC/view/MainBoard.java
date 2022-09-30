package MVC.view;

import MVC.model.Pieces.DummyPiece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainBoard extends Application {
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 800;


    Board board;
    BoardController controller;
    BoardGUI gui;
    private Pane boardPane;

    public static void main(String[] args) {

        Application.launch(args);
        // initialize Chess board tiles
        // initialize chess pieces.
    }

    @Override
    public void init(){
        this.gui = new BoardGUI();
        this.board = new Board(gui);
        this.controller = new BoardController(gui, board);

        board.addObserver(gui);

        board.notify(gui);
        //controller.board = board;
        controller.boardGUI = gui;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.controller = new BoardController();
        System.out.println(gui.boardLayout);
        System.out.println("Start controller: " + controller);
        this.controller.boardGUI = gui;
        //initializeVariables(); // 1. Initializes a pane
        this.boardPane = new Pane();
        initBoard(); // Adds Tile layout to pane
        initPieces(); // Adds Pieces layout to pane
        gui.drawBoard(); // Draws the board layout Graphically
        gui.drawPieces(); // Draws the piece layout Graphically
        stage.setTitle("CrazyChess");

        Scene scene = new Scene(boardPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    void initBoard() {
        //Loops through the matrix and adds all tiles to pane

        for (int i = 0; i < gui.boardLayout.length; i++) {
            for (int j = 0; j < gui.boardLayout[i].length; j++) {
                //System.out.println("Tile: " + layout[i][j]);
                boardPane.getChildren().add(gui.boardLayout[i][j]); //Adds tile at i and j
            }
        }
    }
    void initPieces() {
        //Loops through the matrix and adds all pieces to pane
        for (int i = 0; i < gui.pieceLayout.length; i++) {
            for (int j = 0; j < gui.pieceLayout[i].length; j++) {
                //System.out.println("Tile: " + layout[i][j]);
                if (gui.pieceLayout[i][j] != null){
                    boardPane.getChildren().add(gui.pieceLayout[i][j].rect); //Adds tile at i and j
                }

            }
        }
    }
}
