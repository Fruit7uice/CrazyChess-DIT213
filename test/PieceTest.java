import MVC.model.PieceFactory;
import MVC.model.Pieces.*;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class PieceTest {
    PieceFactory pieceFactory;
    Piece king;
    Piece queen;
    Piece knight;
    Piece bishop;
    Piece rook;
    Piece pawn;
    MoveHandler moveHandler = new MoveHandler();
    Piece[][] board;

    @Before
    public void setUp() {
        board = new Piece[8][8];
        PieceFactory.isPlayerOne = true;
        bishop = PieceFactory.createBishop(1,1);
        board[1][1] = bishop;
        rook = PieceFactory.createRook(5,5);
        board[1][1] = bishop;

    }
    //-----------------Bishop Tests---------------------------
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
    @Test
    public void bishopIllegalMoveMadeNotUpdatePos(){
        int startX = bishop.xPos;
        int startY = bishop.yPos;
        moveHandler.movePiece(4,6, bishop, board);
        assertTrue(bishop.xPos == startX && bishop.yPos == startY);
    }
    //-----------------Rook Tests---------------------------
    @Test
    public void rookMoveLegal() {
        assertTrue(moveHandler.moveChecker(5, 7, rook, board)); // move upp
        assertTrue(moveHandler.moveChecker(5, 3, rook, board)); // move down
        assertTrue(moveHandler.moveChecker(3, 5, rook, board)); // move left
        assertTrue(moveHandler.moveChecker(7, 5, rook, board)); // move right
    }
    @Test
    public void rookMoveIllegal() {
        assertFalse(moveHandler.moveChecker(4, 4, rook, board));
        assertFalse(moveHandler.moveChecker(4, 6, rook, board));
        assertFalse(moveHandler.moveChecker(6, 4, rook, board));
        assertFalse(moveHandler.moveChecker(6, 6, rook, board));
    }
    @Test
    public void rookMoveEnemyBlocking() {
        PieceFactory.isPlayerOne = false;
        board[5][3] = PieceFactory.createPawn(5,3);
        assertTrue(moveHandler.moveChecker(5, 3, rook, board));
    }
   @Test
    public void rookMovePathBlocked() {
        PieceFactory.isPlayerOne = true;
        board[5][4] = PieceFactory.createPawn(5,4);
        assertFalse(moveHandler.moveChecker(5, 2, rook, board));
    }
    @Test
    public void rookLegalMoveMadeUpdatePos(){
        moveHandler.movePiece(5,7, rook, board);
        assertTrue(rook.xPos == 5 && rook.yPos == 7);
    }
    @Test
    public void rookIllegalMoveMadeNotUpdatePos(){
        int startX = rook.xPos;
        int startY = rook.yPos;
        moveHandler.movePiece(6,3, rook, board);
        assertTrue(rook.xPos == startX && rook.yPos == startY);
    }
}


