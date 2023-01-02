package MVC.model;
import MVC.model.Pieces.Piece;

public class LogEntry {
    public Piece piece;
    public int x;
    public int y;
    public boolean hasMoved;

    public LogEntry(Piece p, int oldX, int oldY, boolean hasMoved){
        piece = p;
        x = oldX;
        y = oldY;
        this.hasMoved = hasMoved;
    }
}
