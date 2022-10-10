package MVC.model;

import MVC.model.Pieces.Piece;

public interface Observer {
    void update(Tile[][] boardState, Piece[][] pieces);
}