package MVC.view;

import MVC.model.Pieces.DummyPiece;

import java.util.ArrayList;

public class MainBoard {
    public static void main(String[] args) {


        Board board = new Board(new ArrayList<>());  // New Board(logic)
        BoardGUI gui = new BoardGUI(); // New GUI // assign model var in gui
        BoardController controller = new BoardController(); // New Controller with connection to Gui
        controller.board = board;
        controller.boardGUI = gui;

        gui.initializeCriticalVar(board, controller); // assigns controller and model in gui.



        System.out.println(gui.boardController);
        gui.startGUI(); // Start
        // initialize Chess board tiles
        // initialize chess pieces.


    }
}
