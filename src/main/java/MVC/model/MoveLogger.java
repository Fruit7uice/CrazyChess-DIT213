package MVC.model;

import MVC.model.Pieces.Piece;

import java.util.Stack;

public class MoveLogger {
    public Stack<LogEntry> moveLog = new Stack<LogEntry>();

    /**
     * Logs a move in the moveLog and what piece's "hasMoved" boolean is so that it can change the value back if you undo
     * the first move a piece made
     * @param p
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @param moveBool
     */
    public void logMove(Piece p, int oldX, int oldY, int newX, int newY, boolean moveBool){
        LogEntry entry = new LogEntry(p, oldX, oldY, newX, newY, moveBool);
        moveLog.push(entry);

    }

    /**
     * Pops moveLog twice to remove last move, and return and remove the previous move to move piece back
     * @return
     */
    public LogEntry getLastMove(){
            return moveLog.pop();

    }

    public int getLogLength(){
        return moveLog.size();
    }

    public boolean checkLogEmpty(){
      return moveLog.isEmpty();
    };
}
