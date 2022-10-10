package MVC.view;

import MVC.controller.BoardController;
import MVC.model.Board;
import MVC.model.Observer;
import MVC.model.Pieces.Piece;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static MVC.view.Tile.tileSize;


public class BoardGUI implements Observer {

    //private BoardController controller;

    private Color darkTile = Color.rgb(65, 47, 44);  //Lighter Color
    private Color lightTile = Color.rgb(248, 226, 184); // Darker Color
    public Tile[][] boardTiles = new Tile[8][8];
    public Piece[][] pieceLayout;

    private WrapperPiece[][] mirroredLayout;
    Pane appPane;

    public BoardGUI(Pane appPane) {
        this.appPane = appPane;
        initBoardTiles();
        addTilesToPane(boardTiles);
        drawBoard();
    }

    public void drawBoard() {
        System.out.println("Drawing board...");
        for (int i = 0; i < boardTiles.length; i++) {
            for (int j = 0; j < boardTiles[i].length; j++) {
                Tile tile = boardTiles[i][j];
                tile.setFill(tile.getColor());
                tile.setStroke(darkTile);
                tile.setX(tile.getX());
                tile.setY(tile.getY());
            }
        }
    }

    public void drawPieces() {
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {
                Piece p = pieceLayout[i][j];
                if (p != null){
                    drawPieceInPlace(p, Color.GREEN);
                    System.out.println("Drawing pieces at: x-" + p.xPos + " y-" + p.yPos);
                }
            }
        }
    }

    public void drawPieceInPlace(Piece piece, Color color){
        /*
        piece.rect.setFill(color);
        piece.rect.setWidth(piece.width);
        piece.rect.setHeight(piece.height);
        piece.rect.setX(piece.xPos * Board.tileSize);
        piece.rect.setY(piece.yPos * Board.tileSize);
        //printMatrix();

         */
    }

    public void drawPiece(Piece piece, Color color, int x, int y){
        /*
        piece.rect.setFill(color);
        piece.rect.setWidth(piece.width);
        piece.rect.setHeight(piece.height);
        piece.rect.setX(x);
        piece.rect.setY(y);
        //System.out.println("Draws piece at coords: X" + x + " and Y" + y);
         */
    }

    /**
     * Creates tiles(rectangles) which is added to a matrix.
     */
    public void initBoardTiles(){
        int counter = 0;
        //Create grid
        for (int i = 0; i < boardTiles.length; i++) {
            counter++;
            for (int j = 0; j < boardTiles[i].length; j++) {
                counter++;
                Color color  = (counter % 2 == 0)? lightTile : darkTile;
                Tile tile = new Tile((i*tileSize), (j*tileSize), tileSize, tileSize, color);
                boardTiles[i][j] = tile;
            }
        }
    }

    /*
    void initPieces(Pane pane) {
        //Loops through the matrix and adds all pieces to pane
        for (int i = 0; i < gui.pieceLayout.length; i++) {
            for (int j = 0; j < gui.pieceLayout[i].length; j++) {
                //System.out.println("Tile: " + layout[i][j]);
                if (gui.pieceLayout[i][j] != null){
                    pane.getChildren().add(gui.pieceLayout[i][j].rect); //Adds tile at i and j
                }
            }
        }
    }

     */

    private void addTilesToPane(Tile[][] tiles){
        Group tileGroup = new Group();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile tile = tiles[i][j];
                tileGroup.getChildren().add(tile);
            }
        }
        appPane.getChildren().add(tileGroup);
    }


    @Override
    public void update(Piece[][] pieceLayout) {
        //this.boardLayout = boardState;
        this.pieceLayout = pieceLayout;
        //drawBoard();
        //drawPieces();
        mirrorPieceLayout();
    }

    private void mirrorPieceLayout() {
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {



            }
        }
    }


}
