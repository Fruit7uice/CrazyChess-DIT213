package MVC.view;

import MVC.model.Pieces.DummyPiece;

public interface Observer {
    void update(Tile[][] boardState, DummyPiece[][] pieces);
}
