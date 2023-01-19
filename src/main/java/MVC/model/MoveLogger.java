package MVC.model;

import MVC.model.Pieces.Piece;

import java.util.Stack;

public class MoveLogger {
    private Stack<LogEntry> moveLog = new Stack<LogEntry>();

    /**
     * Logs a move in the moveLog and what piece's "hasMoved" boolean is so that it can change the value back if you undo
     * the first move a piece made
     * @param p
     * @param x
     * @param y
     * @param hasMoved
     */
    public void logMove(Piece p, int x, int y, boolean hasMoved){
        LogEntry entry = new LogEntry(p, x, y, hasMoved);
        moveLog.push(entry);

    }

    /**
     * Pops moveLog twice to remove last move, and return and remove the previous move to move piece back
     * @return
     */
    public LogEntry getLastMove(){
            moveLog.pop();
            return moveLog.pop();

    }
    public boolean checkLogEmpty(){
      return moveLog.isEmpty();
    };
}
