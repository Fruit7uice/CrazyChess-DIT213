package MVC.view;

import MVC.controller.BoardController;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javafx.stage.Stage;

import java.io.IOException;

public class MainBoard extends Application {
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 800;

    public static boolean isCrazy;

    Board board;
    BoardController controller;
    BoardGUI gui;
    private Pane boardPane;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init(){
        Piece[][] pieceLayout;
        System.out.println("Start Matrix of Pieces: ");
        if(isCrazy){
            pieceLayout = PieceLayoutFactory.createClassicLayout();
        }
        else{
            pieceLayout = PieceLayoutFactory.createCrazyLayout();

        }

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

        BorderPane bigContainer = new BorderPane(); // Keeping all object in a layout
        //bigContainer.setMinSize(stage.getMinWidth(), stage.getMinHeight());

        // Create and Align Pane
        GridPane boardContainer = new GridPane(); // keeps board pane
        boardContainer.setAlignment(Pos.CENTER);
        // Add board to StackPane
        boardContainer.getChildren().add(boardPane);

        // Create Button and initialize it
        Button settingsBtn = new Button();
        settingsBtn.setOnAction(event -> {
            try {
                controller.settings(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        settingsBtn.setMinWidth(75);
        settingsBtn.setMinHeight(75);
        // Setting Settings button background
        setSettingsBtnBackground(settingsBtn, "/images/SettingsGearWhite.png");

        // Add stuff to big Container
        bigContainer.setCenter(boardContainer);
        bigContainer.setRight(settingsBtn);

        bigContainer.setPrefSize(800, 800);
        Scene scene = new Scene(bigContainer);

        //Setting chess background
        setPaneBackground(bigContainer, "/images/background.jpeg");

        // Window Settings
        stage.setFullScreen(false);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private void setPaneBackground(Pane pane, String path) {
        Image bgImage = new Image(String.valueOf(getClass().getResource(path)));
        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background bGround = new Background(backgroundImage);
        pane.setBackground(bGround);
    }

    private void setSettingsBtnBackground(Button settingsBtn, String path) {
        Image bgImage = new Image(String.valueOf(getClass().getResource(path)));
        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background bGround = new Background(backgroundImage);
        settingsBtn.setBackground(bGround);
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
