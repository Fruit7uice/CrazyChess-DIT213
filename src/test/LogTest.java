import MVC.model.LogEntry;
import MVC.model.MoveLogger;
import MVC.model.PieceFactory;
import MVC.model.Pieces.Piece;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogTest {
    MoveLogger log = new MoveLogger();
    Piece pawn;

@Test
public void newLogEntry(){
    pawn = PieceFactory.createPawn(1,1);
    int originalLength = log.getLogLength();
    log.logMove(pawn, pawn.xPos,pawn.yPos, 1, 3, pawn.hasMoved);
    assertEquals(originalLength, log.getLogLength()-1);
}

@Test
public void getLastMove(){
    pawn = PieceFactory.createPawn(1,1);
    int oldX = pawn.xPos;
    int oldY = pawn.yPos;
    int newX = pawn.xPos+1;
    int newY = pawn.yPos+2;
    boolean firstMove = pawn.hasMoved;
    log.logMove(pawn, oldX, oldY, newX, newY, firstMove);
    LogEntry entry = log.getLastMove();
    assertEquals(entry.oldX, oldX);
    assertEquals(entry.oldY, oldY);
    assertEquals(entry.newX, newX);
    assertEquals(entry.newY, newY);
    assertEquals(entry.hasMoved, firstMove);


}
}
