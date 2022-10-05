package MVC.model;

public interface Observer {
    public void update(Tile[][] boardState, Piece[][] pieceState);
}
