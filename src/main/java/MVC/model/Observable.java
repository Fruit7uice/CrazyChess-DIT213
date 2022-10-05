package MVC.model;

public interface Observable {
    public void addObserver(Observer o);

    public void updateObserver(Observer o, Tile[][] tileState, Piece[][] pieceState);

    public void updateAll(Tile[][] tileState, Piece[][] pieceState);
}
