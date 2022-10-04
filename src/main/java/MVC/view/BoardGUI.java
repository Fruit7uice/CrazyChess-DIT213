package MVC.view;

import MVC.model.Pieces.DummyPiece;
import MVC.model.Pieces.Piece;
import javafx.scene.paint.Color;


public class BoardGUI implements Observer {

    private BoardController controller;

    Tile[][] boardLayout = new Tile[8][8];

    Piece[][] pieceLayout;

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

    void drawPieces() {
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {
                Piece p = pieceLayout[i][j];
                if (p != null){
                    drawPiece(p, Color.GREEN);
                    System.out.println("Drawing pieces at: x-" + p.xPos + " y-" + p.yPos);
                }
            }
        }
    }

    public void drawPiece(Piece piece, Color color){
        piece.rect.setFill(color);
        piece.rect.setWidth(piece.width);
        piece.rect.setHeight(piece.height);
        piece.rect.setX(piece.xPos * Board.tileSize);
        piece.rect.setY(piece.yPos * Board.tileSize);
        //printMatrix();
    }

    @Override
    public void update(Tile[][] boardState, Piece[][] pieceLayout) {
        //Board board = new Board();
        this.boardLayout = boardState;
        this.pieceLayout = pieceLayout;
        drawBoard();
        drawPieces();
    }

    void printMatrix(){

        for (int i = 0; i < pieceLayout.length; i++) {
            System.out.println("\n {");
            for (int j = 0; j < pieceLayout[i].length; j++) {
                System.out.println(pieceLayout[i][j] + ", ");
            }
            System.out.println("}");
        }
    }
}
