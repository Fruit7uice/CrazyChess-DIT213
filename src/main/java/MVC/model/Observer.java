package MVC.model;

import MVC.model.Pieces.Piece;
import MVC.view.Tile;

public interface Observer {
    void update(Piece[][] pieces);
}
