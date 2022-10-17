package MVC.controller;

import MVC.model.Board;
import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;
import MVC.view.BoardGUI;
import MVC.model.PieceLayoutFactory;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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
        GridPane container = new GridPane();

        container.getChildren().add(boardPane);
        container.setAlignment(Pos.CENTER);
        GridPane.setHalignment(boardPane, HPos.CENTER);
        GridPane.setValignment(boardPane, VPos.CENTER);
        container.setPrefSize(800, 800);
        Scene scene = new Scene(container);

        //Setting background
        String path = "/images/background.jpeg";
        Image bgImage = new Image(String.valueOf(getClass().getResource(path)));
        BackgroundImage backgroundImage = new BackgroundImage(
                                            bgImage,
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundPosition.CENTER,
                                            new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background bGround = new Background(backgroundImage);
        container.setBackground(bGround);

        // Window Settings
        stage.setFullScreen(false);
        stage.setMinWidth(815);
        stage.setMinHeight(830);
        stage.setMaximized(true);

        stage.setScene(scene);
        stage.show();
    }

    private VBox createNumbers() {
        VBox container = new VBox();
        String[] numbers = {"1","2","3","4","5","6","7","8"};
        for (int i = 0; i < numbers.length; i++) {
            Label letter = new Label(numbers[i]);
            container.getChildren().add(letter);
            System.out.println("Number created and added: " + letter.getText());
        }
        return container;
    }

    private HBox createLetters() {
        HBox container = new HBox();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < letters.length; i++) {
            Label letter = new Label(letters[i]);
            container.getChildren().add(letter);
            System.out.println("Letter created and added: " + letter.getText());
        }
        return container;
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
