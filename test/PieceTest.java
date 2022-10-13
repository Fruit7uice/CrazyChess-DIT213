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
    Piece[][] board;
    MoveHandler moveHandler = new MoveHandler(board);

    @Before
    public void setUp() {
        board = new Piece[8][8];
        PieceFactory.isPlayerOne = true;
        bishop = PieceFactory.createBishop(1,1);
        board[1][1] = bishop;
        rook = PieceFactory.createRook(6,1 );
        board[1][6] = rook;
        queen = PieceFactory.createQueen(6,6);
        board[6][6] = queen;
        knight = PieceFactory.createKnight(2,7);
        board[2][7] = knight;

    }
    //-----------------Bishop Tests---------------------------
    @Test
    public void bishopMoveLegal() {
        assertTrue(moveHandler.moveChecker(2, 2, bishop, board));
    }
    @Test
    public void bishopMoveIllegal() {
        assertFalse(moveHandler.moveChecker(1, 2, bishop, board));// upp
        assertFalse(moveHandler.moveChecker(2, 1, bishop, board));// right
        assertFalse(moveHandler.moveChecker(1, 0, bishop, board));//down
        assertFalse(moveHandler.moveChecker(0, 1, bishop, board));// left
    }
    @Test
    public void bishopMoveEnemyBlocking() {
        PieceFactory.isPlayerOne = false;
        board[2][2] = PieceFactory.createPawn(2,2);
        assertTrue(moveHandler.moveChecker(2, 2, bishop, board));
    }
    @Test
    public void bishopMovePathBlocked() {
        PieceFactory.isPlayerOne = true;
        board[2][2] = PieceFactory.createRook(2,2);
        assertFalse(moveHandler.moveChecker(3, 3, bishop, board));
    }
    @Test
    public void bishopLegalMoveMadeUpdatePos(){
        moveHandler.movePiece(0,2, bishop, board);
        assertTrue(bishop.xPos == 0 && bishop.yPos == 2);
    }
    @Test
    public void bishopIllegalMoveMadeNotUpdatePos(){
        int startX = bishop.xPos;
        int startY = bishop.yPos;
        moveHandler.movePiece(0,1, bishop, board);
        assertTrue(bishop.xPos == startX && bishop.yPos == startY);
    }
    //-----------------Rook Tests---------------------------
    @Test
    public void rookMoveLegal() {
        assertTrue(moveHandler.moveChecker(6, 2, rook, board)); // move upp
        assertTrue(moveHandler.moveChecker(6, 0, rook, board)); // move down
        assertTrue(moveHandler.moveChecker(5, 1, rook, board)); // move left
        assertTrue(moveHandler.moveChecker(7, 1, rook, board)); // move right
    }
    @Test
    public void rookMoveIllegal() {
        assertFalse(moveHandler.moveChecker(7, 0, rook, board));
        assertFalse(moveHandler.moveChecker(7, 2, rook, board));
        assertFalse(moveHandler.moveChecker(7, 3, rook, board));
        assertFalse(moveHandler.moveChecker(5, 0, rook, board));
    }
    @Test
    public void rookMoveEnemyBlocking() {
        PieceFactory.isPlayerOne = false;
        board[6][2] = PieceFactory.createPawn(6, 2);
        assertTrue(moveHandler.moveChecker(6, 2, rook, board));
    }
   @Test
    public void rookMovePathBlocked() {
        PieceFactory.isPlayerOne = true;
        board[6][2] = PieceFactory.createPawn(6,2);
        assertFalse(moveHandler.moveChecker(6, 3, rook, board));
    }
    @Test
    public void rookLegalMoveMadeUpdatePos(){
        moveHandler.movePiece(6,0, rook, board);
        assertTrue(rook.xPos == 6 && rook.yPos == 0);
    }
    @Test
    public void rookIllegalMoveMadeNotUpdatePos(){
        int startX = rook.xPos;
        int startY = rook.yPos;
        moveHandler.movePiece(5,0, rook, board);
        assertTrue(rook.xPos == startX && rook.yPos == startY);
    }
    //------------------Queen Tests----------------------------
    @Test
    public void queenMoveLegal() {
        assertTrue(moveHandler.moveChecker(6, 7, queen, board)); // move upp
        assertTrue(moveHandler.moveChecker(6, 5, queen, board)); // move down
        assertTrue(moveHandler.moveChecker(5, 6, queen, board)); // move left
        assertTrue(moveHandler.moveChecker(7, 6, queen, board)); // move right
        assertTrue(moveHandler.moveChecker(7, 7, queen, board)); // move upp right
        assertTrue(moveHandler.moveChecker(7, 5, queen, board)); // move down right
        assertTrue(moveHandler.moveChecker(5, 7, queen, board)); // move upp left
        assertTrue(moveHandler.moveChecker(5, 5, queen, board)); // move down left
    }

    @Test
    public void queenMoveIllegal() {
        assertFalse(moveHandler.moveChecker(7, 4, queen, board));
        assertFalse(moveHandler.moveChecker(4, 5, queen, board));
        assertFalse(moveHandler.moveChecker(3, 7, queen, board));
    }
    @Test
    public void queenMoveEnemyBlocking() {
        PieceFactory.isPlayerOne = false;
        board[6][5] = PieceFactory.createPawn(6, 5);
        assertTrue(moveHandler.moveChecker(6, 5, queen, board));
    }
   @Test
    public void queenMovePathBlocked() {
        PieceFactory.isPlayerOne = true;
        board[6][5] = PieceFactory.createPawn(6,5);
        assertFalse(moveHandler.moveChecker(6, 4, queen, board));
    }
    @Test
    public void queenLegalMoveMadeUpdatePos(){
        moveHandler.movePiece(7,6, queen, board);
        assertTrue(queen.xPos == 7 && queen.yPos == 6);
    }
    @Test
    public void queenIllegalMoveMadeNotUpdatePos(){
        int startX = queen.xPos;
        int startY = queen.yPos;
        moveHandler.movePiece(7,4, queen, board);
        assertTrue(queen.xPos == startX && queen.yPos == startY);
    }
    //------------------Knight Tests----------------------------
    @Test
    public void knightMoveLegal() {
        assertTrue(moveHandler.moveChecker(0, 6, knight, board));
        assertTrue(moveHandler.moveChecker(1, 5, knight, board));
        assertTrue(moveHandler.moveChecker(3, 5, knight, board));
        assertTrue(moveHandler.moveChecker(4, 6, knight, board));
    }
    @Test
    public void knightMoveIllegal() {
        assertFalse(moveHandler.moveChecker(1, 7, knight, board));
        assertFalse(moveHandler.moveChecker(1, 6, knight, board));
        assertFalse(moveHandler.moveChecker(2, 6, knight, board));
        assertFalse(moveHandler.moveChecker(3, 6, knight, board));
        assertFalse(moveHandler.moveChecker(3, 7, knight, board));
    }
    @Test
    public void knightMoveEnemyBlocking() {
        PieceFactory.isPlayerOne = false;
        board[6][5] = PieceFactory.createPawn(1, 5);
        assertTrue(moveHandler.moveChecker(1, 5, knight, board));
    }
    @Test
    public void knightMovePathBlocked() {
        PieceFactory.isPlayerOne = true;
        board[2][6] = PieceFactory.createPawn(2,6);
        assertTrue(moveHandler.moveChecker(1, 5, knight, board));
    }
    @Test
    public void knightLegalMoveMadeUpdatePos(){
        moveHandler.movePiece(3,5, knight, board);
        assertTrue(knight.xPos == 3 && knight.yPos == 5);
    }
    @Test
    public void knightIllegalMoveMadeNotUpdatePos(){
        int startX = knight.xPos;
        int startY = knight.yPos;
        moveHandler.movePiece(0,5, knight, board);
        assertTrue(knight.xPos == startX && knight.yPos == startY);
    }
}


