package MVC.controller;

import MVC.model.Board;
import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;
import MVC.view.BoardGUI;
import MVC.model.PieceLayoutFactory;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainBoard extends Application {
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 800;

    Board board;
    BoardController controller;
    BoardGUI gui;
    private Pane boardPane;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init(){
        Piece[][] pieceLayout = PieceLayoutFactory.createMatrixLayout();
        System.out.println("Start Matrix of Pieces: ");
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
        board.notifyAllObservers(); // Notify observers and update their state, which in return notifies gui.
        stage.setTitle("CrazyChess");
        StackPane container = new StackPane();
        HBox hAlign = new HBox();
        VBox vAlign = new VBox();
        hAlign.getChildren().add(vAlign);
        hAlign.setAlignment(Pos.CENTER);
        vAlign.getChildren().add(boardPane);
        vAlign.setAlignment(Pos.CENTER);
        container.getChildren().add(hAlign);
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(800, 800);


        Scene scene = new Scene(container);

        stage.setFullScreen(false);
        stage.setMinWidth(815);
        stage.setMinHeight(830);
        stage.setMaximized(true);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Used to Test and debug the relations between gui -> controller -> model,
     * making sure when an event has happened it should update the model.
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


    public void runAfterLaunch(Stage stage) throws Exception {
        this.init();
        this.start(stage);
    }

}
