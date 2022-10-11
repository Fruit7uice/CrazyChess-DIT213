import MVC.model.PieceFactory;
import MVC.model.Pieces.*;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class PieceTest {
    PieceFactory pieceFactory;
    King king;
    Queen queen;
    Knight knight;
    Piece bishop;
    Rook rook;
    Pawn pawn;
    MoveHandler moveHandler = new MoveHandler();
    Piece[][] board;

    @Before
    public void setUp() {
        board = new Piece[8][8];
        PieceFactory.isPlayerOne = true;
        bishop = PieceFactory.createBishop(1,1);
        board[1][1] = bishop;

    }
    @Test
    public void bishopMoveLegal() {
        assertTrue(moveHandler.moveChecker(2, 2, bishop, board));
    }
    @Test
    public void bishopMoveIllegal() {
        assertFalse(moveHandler.moveChecker(1, 6, bishop, board));
        assertFalse(moveHandler.moveChecker(6, 1, bishop, board));
    }
    @Test
    public void bishopMoveEnemyBlocking() {
        PieceFactory.isPlayerOne = false;
        board[3][3] = PieceFactory.createPawn(3,3);
        assertTrue(moveHandler.moveChecker(3, 3, bishop, board));
    }
    @Test
    public void bishopMovePathBlocked() {
        PieceFactory.isPlayerOne = true;
        board[4][4] = PieceFactory.createRook(4,4);
        assertFalse(moveHandler.moveChecker(6, 6, bishop, board));
    }
    @Test
    public void bishopLegalMoveMadeUpdatePos(){
        moveHandler.movePiece(4,4, bishop, board);
        assertTrue(bishop.xPos == 4 && bishop.yPos == 4);
    }

}


