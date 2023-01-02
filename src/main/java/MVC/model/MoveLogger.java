package MVC.model;

import MVC.model.Pieces.Piece;

import java.util.Stack;

public class MoveLogger {
    private Stack<LogEntry> moveLog = new Stack<LogEntry>();

    public void logMove(Piece p, int x, int y, boolean hasMoved){
        LogEntry entry = new LogEntry(p, x, y, hasMoved);
        moveLog.push(entry);

    }
    public LogEntry getLastMove(){
            moveLog.pop();
            return moveLog.pop();

    }
    public boolean checkLogEmpty(){
      return moveLog.isEmpty();
    };
}
