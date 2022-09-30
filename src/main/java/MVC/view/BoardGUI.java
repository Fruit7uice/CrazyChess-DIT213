package MVC.view;

import MVC.model.Pieces.DummyPiece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class BoardGUI implements Observer {

    private BoardController controller;

    Tile[][] boardLayout = new Tile[8][8];

    DummyPiece[][] pieceLayout;

    void drawPieces() {
        //------INITIALIZE DUMMY PIECE ---------
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {
                DummyPiece dp = pieceLayout[i][j];
                if (dp != null){
                    drawPiece(dp);
                    System.out.println("Drawing pieces.");
                }

            }
        }
        //--------------------------------------
    }

    void drawBoard() {
        //Board board = new Board();
        //Tile[][] boardLayout = board.getCurrentBoardLayout();
        System.out.println("Drawing board...");
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout[i].length; j++) {
                Tile tile = boardLayout[i][j];
                tile.setFill(tile.getColor());
                tile.setStroke(Color.BLACK);
                tile.setX(tile.getX());
                tile.setY(tile.getY());
            }
        }
    }


    public void drawPiece(DummyPiece p){
        p.rect.setFill(Color.GREEN);
        p.rect.setWidth(p.width);
        p.rect.setHeight(p.height);
        p.rect.setX(p.x);
        p.rect.setY(p.y);
    }

    @Override
    public void update(Tile[][] boardState, DummyPiece[][] pieceLayout) {
        //Board board = new Board();
        this.boardLayout = boardState;
        this.pieceLayout = pieceLayout;
        drawBoard();
        drawPieces();
    }
}
