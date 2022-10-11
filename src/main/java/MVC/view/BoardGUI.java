package MVC.view;

import MVC.controller.BoardController;
import MVC.model.Observer;
import MVC.model.Pieces.Piece;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static MVC.view.Tile.tileSize;


public class BoardGUI implements Observer {

    private BoardController ctrl;

    private Color darkTile = Color.rgb(65, 47, 44);  //Lighter Color
    private Color lightTile = Color.rgb(248, 226, 184); // Darker Color
    public Tile[][] boardTiles = new Tile[8][8];
    public Piece[][] pieceLayout;

    private WrapperPiece[][] mirroredLayout;
    Pane appPane;

    public BoardGUI(Pane appPane) {
        this.appPane = appPane;
        initBoardTiles(); //Creates the board grid
        addTilesToPane(boardTiles); // adds the grid as a group to a pane
        //drawBoard(); // Draws the board
    }
    public BoardGUI() {
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
        for (int i = 0; i < mirroredLayout.length; i++) {
            for (int j = 0; j < mirroredLayout[i].length; j++) {
                WrapperPiece p = mirroredLayout[i][j];
                if (p != null){
                    drawPieceToPlace(p, Color.GREEN);
                    //System.out.println("Drawing pieces at: x-" + p.xPos + " y-" + p.yPos);
                }
            }
        }
    }

    public void drawPieceToPlace(WrapperPiece piece, Color color){
        piece.setFill(color);
        piece.setWidth(piece.getWidth());
        piece.setHeight(piece.getHeight());
        piece.setX(piece.getX() * tileSize);
        piece.setY(piece.getY() * tileSize);
        //printMatrix();
    }



    public void drawPiece(WrapperPiece piece, Color color, int x, int y){

        piece.setFill(color);
        piece.setWidth(piece.getWidth());
        piece.setHeight(piece.getHeight());
        piece.setX(x);
        piece.setY(y);
        //System.out.println("Draws piece at coords: X" + x + " and Y" + y);

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

    public void setController(BoardController ctrl){
        this.ctrl = ctrl;
    }


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

    private void addPiecesToPane() {
        Group pieceGroup = new Group();
        for (int i = 0; i < mirroredLayout.length; i++) {
            for (int j = 0; j < mirroredLayout.length; j++) {
                if (mirroredLayout[i][j] != null){
                    pieceGroup.getChildren().add(mirroredLayout[i][j]);
                }
            }
        }
        if (appPane.getChildren().size() < 2){
            appPane.getChildren().add(pieceGroup);
        }
        else{
            appPane.getChildren().remove(1);
            appPane.getChildren().add(pieceGroup);
        }
    }

    private void mirrorPieceLayout() {
        int x = pieceLayout.length;
        int y = pieceLayout.length;
        this.mirroredLayout = new WrapperPiece[x][y];
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout[i].length; j++) {
                //Getting piece information.
                if (pieceLayout[i][j] != null){
                    //String type = pieceLayout[i][j].getType();
                    int xPos = pieceLayout[i][j].xPos;
                    int yPos = pieceLayout[i][j].yPos;
                    String image = pieceLayout[i][j].getFirstImagePath();
                    //Image img = new Image(image);
                    //Creating wrapper piece
                    Piece connectedPiece = pieceLayout[i][j];
                    WrapperPiece wPiece = new WrapperPiece(xPos, yPos, tileSize, tileSize, connectedPiece);
                    wPiece.setOnMouseClicked(event -> ctrl.pressed(event, wPiece));
                    wPiece.setOnMouseDragged(event -> ctrl.dragged(event, wPiece));
                    wPiece.setOnMouseReleased(event -> ctrl.dragReleased(event, wPiece));
                    //wPiece.setFill(new ImagePattern(img));
                    wPiece.setFill(Color.GREEN);
                    mirroredLayout[i][j] = wPiece;
                }
            }
        }
    }

    @Override
    public void update(Piece[][] pieceLayout) {
        this.pieceLayout = pieceLayout;
        createGraphicalPieceLayout();
        mirrorPieceLayout();
        addPiecesToPane();
        drawPieces();
    }

    private void createGraphicalPieceLayout() {
        mirroredLayout = new WrapperPiece[8][8];
        for (int i = 0; i < mirroredLayout.length; i++) {
            for (int j = 0; j < mirroredLayout.length; j++) {
                
            }
        }
    }


}
