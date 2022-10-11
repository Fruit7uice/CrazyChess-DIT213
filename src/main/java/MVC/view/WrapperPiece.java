package MVC.view;

import MVC.model.Pieces.Piece;
import javafx.scene.shape.Rectangle;

public class WrapperPiece extends Rectangle {

    Piece refPiece;
    public WrapperPiece(double x, double y, double width, double height, Piece p) {
        super(x, y, width, height);
        this.refPiece = p;
    }

    public Piece getRefPiece() {
        return refPiece;
    }
}
