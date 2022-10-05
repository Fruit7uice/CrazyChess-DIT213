import MVC.model.Board;
import MVC.model.PieceFactory;
import MVC.model.Pieces.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;


public class PieceTest {
    King king;
    Queen queen;
    Knight knight;
    Rook rook;
    Bishop bishop;
    Pawn pawn;
    MoveHandler moveHandler;
    Board board;

    @Before
    public void setUp() {
        bishop = (Bishop) PieceFactory.createBishop(0, 0);

    }
    @Test
    public void bishopMovement(){
        assertTrue(moveHandler.moveChecker(1, 1, bishop, board.getPieceLayout()));
        assertFalse(moveHandler.moveChecker(1, 6, bishop, board.getPieceLayout()));
    }

}


