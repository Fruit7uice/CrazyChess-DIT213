package MVC.controller;

import MVC.model.Board;
import MVC.model.Pieces.MoveHandler;
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

        Piece[][] pieceLayout = PieceLayoutFactory.createClassicLayout();
        printMatrix(pieceLayout);
        this.boardPane = new Pane(); // Creates a new pane
        this.gui = new BoardGUI(boardPane);
        this.board = new Board(pieceLayout);
        this.controller = new BoardController(gui, board, new MoveHandler(board), boardPane);
        gui.setController(controller);

        board.addObserver(gui);
    }

    @Override
    public void start(Stage stage) throws Exception {
        /* SOME TESTING FOR NULLPOINTEREXCEPTION
        System.out.println("Start controller: " + controller);
        System.out.println("Gui: " + gui);
        System.out.println("Board: " + board);
         */
        board.notifyAllObservers(); // Notify observers and update their state, which in return notifies gui.
        stage.setTitle("CrazyChess");
        Scene scene = new Scene(boardPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Used to Test and debug the relations between gui -> controller -> model,
     * making sure when an event has happend it should update the model.
     */
    void printMatrix(Piece[][] pieces){
        System.out.println("\n {");
        for (int i = 0; i < pieces.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] != null){
                    System.out.print(pieces[i][j].getType() + ", ");
                }
                else{
                    System.out.print(pieces[i][j] + ", ");
                }
            }
            System.out.print("} \n");
        }
        System.out.println("}");
    }

}
