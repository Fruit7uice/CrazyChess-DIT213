package MVC.view;

import MVC.model.Pieces.DummyPiece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;


public class BoardGUI extends Application {
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 800;

    public static int BOARD_SIZE = 800;

    private int squareSize = BOARD_SIZE/8;
    public Pane boardPane;

    public Board board;
    public BoardController boardController;

    public ArrayList<DummyPiece> pieces;

    public void initializeBoardGrid(){
        // Create grid with rectangles:
        int counter = 0;
        Color dark = Color.rgb(65, 47, 44);  //Lighter Color
        Color light = Color.rgb(248, 226, 184); // Darker Color

        for (int i = 0; i < WINDOW_WIDTH; i += (WINDOW_WIDTH/8)) {
            counter++;
            for (int j = 0; j < WINDOW_HEIGHT; j += (WINDOW_HEIGHT/8)) {
                counter++;
                Rectangle rect = new Rectangle(i, j, squareSize, squareSize);
                Color tile  = (counter % 2 == 0)? light: dark;
                rect.setFill(tile);
                rect.setStroke(dark);
                boardPane.getChildren().add(rect);
            }
        }
        // -------------------------------------
    }


    public void startGUI(){
        Application.launch();
    }



    private void initializeVariables() {
        this.boardPane = new Pane();
        this.pieces = new ArrayList<>();
        //boardController = new BoardController();
        //System.out.println("Controller has been initialized");
        //System.out.println(boardController);

    }

    public void drawPiece(DummyPiece p){
        p.rect.setWidth(p.width);
        p.rect.setHeight(p.height);
        p.rect.setX(p.x);
        p.rect.setY(p.y);
    }


    @Override
    public void start(Stage stage) throws Exception {
        initializeVariables();
        initializeBoardGrid();

        stage.setTitle("CrazyChess");

        //------INITIALIZE DUMMY PIECE ---------

        for (int i = 0; i < 2; i++) {
            Rectangle rect = new Rectangle();
            rect.setFill(Color.GREEN);
            int x = squareSize * i;
            int y = squareSize * i;
            DummyPiece dp = new DummyPiece(x, y, rect, squareSize, squareSize);
            pieces.add(dp);
            // Adding mouse event functionality
            rect.setOnMousePressed(event -> new BoardController().pressed(event, dp));
            rect.setOnMouseDragged(event -> new BoardController().dragged(event, dp));
            rect.setOnMouseReleased(event -> new BoardController().released(event, dp));

            boardPane.getChildren().add(rect);
            drawPiece(dp);
        }
        //--------------------------------------
        Scene scene = new Scene(boardPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();

    }

    public void initializeCriticalVar(Board board, BoardController controller) {
        this.board = board;
        this.boardController = controller;
    }
}
