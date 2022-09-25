package MVC.view;

import MVC.model.Pieces.DummyPiece;

import java.util.ArrayList;


public class Board {

    public int squareSize = BoardGUI.WINDOW_WIDTH/8;

    public ArrayList<DummyPiece> pieces;

    private static String[][] InitialBoard=
            {
                    {"BR", "BKn", "BB", "BQ", "BK", "BB", "BKn", "BR" },
                    {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {"WP", "WP", "WP", "WP", "WP", "WP", "WP", "WP"},
                    {"WR", "WKn", "WB", "WQ", "WK", "WB", "WKn", "WR"}
            };

    public Board(ArrayList<DummyPiece> pieces) {
        this.pieces = pieces;
    }
}
