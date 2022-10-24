package MVC.view;

import MVC.controller.BoardController;
import MVC.model.Observer;
import MVC.model.Pieces.Piece;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import static MVC.view.Tile.tileSize;

/**
 * This class visually represents the board with coloured tiles and pieces.
 * The class also updates the visual representation of what happens on the
 * logical board.
 */
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
        drawTiles(); // Draws the board
    }
    public BoardGUI() {
    }

    public void drawTiles() {
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

    public void drawPieces(){
        for (int i = 0; i < mirroredLayout.length; i++) {
            for (int j = 0; j < mirroredLayout[i].length; j++) {
                WrapperPiece p = mirroredLayout[i][j];
                if (p != null){
                    drawWrapperAfterIndex(p, Color.GREEN, Color.rgb(1,1,1,0));
                }
            }
        }
    }

    public void drawWrapperAfterIndex(WrapperPiece piece, Color color, Color stroke) {
        String path = piece.getRefPiece().getImagePath();
        if(path != "pathOne"){
            String realPath = String.valueOf(getClass().getResource(path));
            Image img = new Image(realPath);
            ImagePattern imagePattern = new ImagePattern(img);
            piece.setFill(imagePattern);
        }
        else {
            piece.setFill(color);
        }
        piece.setStroke(stroke);
        piece.setStrokeWidth(3);
        piece.setWidth(piece.getWidth());
        piece.setHeight(piece.getHeight());
        piece.setX(piece.getRefPiece().xPos * tileSize);
        piece.setY(piece.getRefPiece().yPos * tileSize);
        //printMatrix();
    }

    public void drawWrapperPiece(WrapperPiece piece, Color color, Color stroke, int x, int y){
        String path = piece.getRefPiece().getImagePath();
        if(path != "pathOne"){
            String realPath = String.valueOf(getClass().getResource(path));
            Image img = new Image(realPath);
            ImagePattern imagePattern = new ImagePattern(img);
            piece.setFill(imagePattern);
        }
        else {
            piece.setFill(color);
        }
        piece.setStroke(stroke);
        piece.setStrokeWidth(3);
        piece.setWidth(piece.getWidth());
        piece.setHeight(piece.getHeight());
        piece.setX(x);
        piece.setY(y);
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

    private void addPiecesToPane(WrapperPiece[][] graphicalLayout) {
        Group pieceGroup = new Group();
        for (int i = 0; i < graphicalLayout.length; i++) {
            for (int j = 0; j < graphicalLayout.length; j++) {
                if (graphicalLayout[i][j] != null){
                    pieceGroup.getChildren().add(graphicalLayout[i][j]);
                }
            }
        }
        appPane.getChildren().add(pieceGroup);
    }

    /**
     * Updates the pane by first clearing its children and then adding new ones.
     */
    private void updatePane() {
        appPane.getChildren().clear();
        addTilesToPane(boardTiles); // adds Matrix of tiles to pane
        addPiecesToPane(mirroredLayout); // Adds Matrix of WrapperPieces to pane
    }

    /**
     * Creates a Graphical copy of the logical Chess Piece layout
     * and adds functionality to the graphical objects.
     */
    private void createNewGraphicalPieceLayout() {
        mirroredLayout = new WrapperPiece[8][8];
        for (int row = 0; row < mirroredLayout.length; row++) {
            for (int col = 0; col < mirroredLayout.length; col++) {
                Piece index = pieceLayout[row][col];
                if (index != null){
                    // CREATES GRAPHICAL PIECE WHERE IT IS A PIECE IN THE LOGICAL LAYOUT
                    WrapperPiece wPiece = new WrapperPiece(col, row, tileSize, tileSize, index);
                    wPiece.setOnMouseClicked(event -> ctrl.pressed(wPiece));
                    wPiece.setOnMouseDragged(event -> ctrl.dragged(event, wPiece));
                    wPiece.setOnMouseReleased(event -> ctrl.released(event, wPiece));
                    mirroredLayout[row][col] = wPiece;
                }
            }
        }
    }

    /**
     * Updates the view when called.
     * Overided from Observer interface.
     * @param pieceLayout
     */
    @Override
    public void update(Piece[][] pieceLayout) {
        this.pieceLayout = pieceLayout;
        createNewGraphicalPieceLayout();
        updatePane();
        drawPieces();
    }


}
