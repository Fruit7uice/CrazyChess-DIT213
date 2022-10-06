package MVC.model;

import MVC.controller.MouseHandler;
import MVC.view.GUI;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene();
        primaryStage.setTitle("Crazy Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Board board = new Board();
        GUI gui = new GUI();
        board.addObserver(gui);
        MoveHandler movehandler = new MoveHandler(board);
        MouseHandler mh = new MouseHandler(board, gui, movehandler);
    }

}
