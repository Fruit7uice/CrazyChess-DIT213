package MVC.model;
import MVC.model.Pieces.Piece;

public class LogEntry {
    public Piece piece;
    public int oldX;
    public int oldY;
    public int newX;
    public int newY;
    public boolean hasMoved;

    /**
     * Log entry contains a piece, old x and y, new x and y, and the boolean value of the piece's "hasMoved" before the move
     * so that it can set the value back to what it was before if needed.
     * @param p
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @param hasMoved
     */
    public LogEntry(Piece p, int oldX, int oldY, int newX, int newY, boolean hasMoved){
        piece = p;
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
        this.hasMoved = hasMoved;
    }
}
