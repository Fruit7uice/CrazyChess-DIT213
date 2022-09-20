package MVC.snap;

import MVC.model.Piece;
import MVC.model.Tile;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SnapToGridController {

    @FXML
    GridPane pane;

    private ArrayList<Piece> pieces;

    private int columns = 8;
    private int rows = 8;
    private int count = 0;
    private int size = 800;
    private int tileSize = size / rows;

    public SnapToGridController() {
    }

    @FXML
    public void initialize(){
    for (int i = 0; i < rows; i+= 1){
       count++;
       for (int j = 0; j < columns; j++){
           Rectangle r = new Rectangle(tileSize, tileSize);
           if (count % 2 == 0){r.setFill(Color.WHITE);}
           pane.add(r,i,j);
           count++;
       }
    }

    pieces = new ArrayList<Piece>();

    for(int i = 0; i < 2; i++){
        initializePieces(i);
    }
    for (int i = rows-1; i > rows-3; i--){
        initializePieces(i);
    }

    }



    /**
     * Puts a piece on each tile on the given row
     * @param row the given row for the function
     */
    private void initializePieces(int row){
        for (int col = 0; col < columns; col++) {
            Circle c = new Circle();
            c.setFill(Color.GREEN); //TODO make color "Color.TRANSPARENT" when everything is working as intended
            double radius = tileSize / 10.0;
            Piece p = new Piece(row, col, radius, c);
            pieces.add(p);

            c.setOnMousePressed(event -> pressed(event, p));
            c.setOnMouseDragged(event -> dragged(event, p));
            c.setOnMouseReleased(event -> released(event, p));

            pane.add(c,col,row);
            GridPane.setHalignment(c, HPos.CENTER);
            p.draw();
        }

    }



    /**
     * Make a Piece "selected" so that it shows where it's possible moves are
     * @param event
     * @param p
     */
    public void pressed(MouseEvent event, Piece p){
        //TODO make function for checking possible moves
    }

    public void dragged(MouseEvent event, Piece p){
        // TODO make piece move with mouse
    }

    public void released(MouseEvent event, Piece p){
        // TODO place piece where released, but ONLY if the move is legal, handle eventual capture etc.
    }

}
