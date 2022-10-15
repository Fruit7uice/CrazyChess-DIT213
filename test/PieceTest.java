import MVC.model.Board;
import MVC.model.PieceFactory;
import MVC.model.Pieces.*;

import MVC.model.Player;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class PieceTest {
    Piece king;
    Piece queen;
    Piece knight;
    Piece bishop;
    Piece rook;
    Piece pawn;
    Player player = new Player();
    Piece[][] pieceLayout = new Piece[8][8];
    Board board = new Board(pieceLayout);
    MoveHandler moveHandler = new MoveHandler(board);

    @Before
    public void setUp() {
        PieceFactory.isPlayerOne = true;
        bishop = PieceFactory.createBishop(1,1);
        pieceLayout[1][1] = bishop;
        rook = PieceFactory.createRook(6,1 );
        pieceLayout[1][6] = rook;
        queen = PieceFactory.createQueen(6,6);
        pieceLayout[6][6] = queen;
        knight = PieceFactory.createKnight(2,7);
        pieceLayout[7][2] = knight;

    }
    //-----------------Bishop Tests---------------------------
    @Test
    public void bishopMoveLegal() {
        assertTrue(moveHandler.isMoveAllowed(2, 2, bishop, pieceLayout));
    }
    @Test
    public void bishopMoveIllegal() {
        assertFalse(moveHandler.isMoveAllowed(1, 2, bishop, pieceLayout));// upp
        assertFalse(moveHandler.isMoveAllowed(2, 1, bishop, pieceLayout));// right
        assertFalse(moveHandler.isMoveAllowed(1, 0, bishop, pieceLayout));//down
        assertFalse(moveHandler.isMoveAllowed(0, 1, bishop, pieceLayout));// left
    }
    @Test
    public void bishopMoveEnemyBlocking() {
        PieceFactory.isPlayerOne = false;
        pieceLayout[2][2] = PieceFactory.createPawn(2,2);
        assertTrue(moveHandler.isMoveAllowed(2, 2, bishop, pieceLayout));
    }
    @Test
    public void bishopMovePathBlocked() {
        PieceFactory.isPlayerOne = true;
        pieceLayout[2][2] = PieceFactory.createRook(2,2);
        assertFalse(moveHandler.isMoveAllowed(3, 3, bishop, pieceLayout));
    }
    @Test
    public void bishopLegalMoveMadeUpdatePos(){
        moveHandler.movePiece(0,2, bishop, pieceLayout);
        assertTrue(bishop.xPos == 0 && bishop.yPos == 2);
    }
    @Test
    public void bishopIllegalMoveMadeNotUpdatePos(){
        int startX = bishop.xPos;
        int startY = bishop.yPos;
        moveHandler.movePiece(0,1, bishop, pieceLayout);
        assertTrue(bishop.xPos == startX && bishop.yPos == startY);
    }
    //-----------------Rook Tests---------------------------
    @Test
    public void rookMoveLegal() {
        assertTrue(moveHandler.isMoveAllowed(6, 2, rook, pieceLayout)); // move upp
        assertTrue(moveHandler.isMoveAllowed(6, 0, rook, pieceLayout)); // move down
        assertTrue(moveHandler.isMoveAllowed(5, 1, rook, pieceLayout)); // move left
        assertTrue(moveHandler.isMoveAllowed(7, 1, rook, pieceLayout)); // move right
    }
    @Test
    public void rookMoveIllegal() {
        assertFalse(moveHandler.isMoveAllowed(7, 0, rook, pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(7, 2, rook, pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(7, 3, rook, pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(5, 0, rook, pieceLayout));
    }
    @Test
    public void rookMoveEnemyBlocking() {
        PieceFactory.isPlayerOne = false;
        pieceLayout[2][6] = PieceFactory.createPawn(6, 2);
        assertTrue(moveHandler.isMoveAllowed(6, 2, rook, pieceLayout));
    }
    @Test
    public void rookMovePathBlocked() {
        PieceFactory.isPlayerOne = true;
        pieceLayout[2][6] = PieceFactory.createPawn(6,2);
        assertFalse(moveHandler.isMoveAllowed(6, 3, rook, pieceLayout));
    }
    @Test
    public void rookLegalMoveMadeUpdatePos(){
        moveHandler.movePiece(6,0, rook, pieceLayout);
        assertTrue(rook.xPos == 6 && rook.yPos == 0);
    }
    @Test
    public void rookIllegalMoveMadeNotUpdatePos(){
        int startX = rook.xPos;
        int startY = rook.yPos;
        moveHandler.movePiece(5,0, rook, pieceLayout);
        assertTrue(rook.xPos == startX && rook.yPos == startY);
    }
    //------------------Queen Tests----------------------------
    @Test
    public void queenMoveLegal() {
        assertTrue(moveHandler.isMoveAllowed(6, 7, queen, pieceLayout)); // move upp
        assertTrue(moveHandler.isMoveAllowed(6, 5, queen, pieceLayout)); // move down
        assertTrue(moveHandler.isMoveAllowed(5, 6, queen, pieceLayout)); // move left
        assertTrue(moveHandler.isMoveAllowed(7, 6, queen, pieceLayout)); // move right
        assertTrue(moveHandler.isMoveAllowed(7, 7, queen, pieceLayout)); // move upp right
        assertTrue(moveHandler.isMoveAllowed(7, 5, queen, pieceLayout)); // move down right
        assertTrue(moveHandler.isMoveAllowed(5, 7, queen, pieceLayout)); // move upp left
        assertTrue(moveHandler.isMoveAllowed(5, 5, queen, pieceLayout)); // move down left
    }

    @Test
    public void queenMoveIllegal() {
        assertFalse(moveHandler.isMoveAllowed(7, 4, queen, pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(4, 5, queen, pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(3, 7, queen, pieceLayout));
    }
    @Test
    public void queenMoveEnemyBlocking() {
        PieceFactory.isPlayerOne = false;
        pieceLayout[5][6] = PieceFactory.createPawn(6, 5);
        assertTrue(moveHandler.isMoveAllowed(6, 5, queen, pieceLayout));
    }
    @Test
    public void queenMovePathBlocked() {
        PieceFactory.isPlayerOne = true;
        pieceLayout[5][6] = PieceFactory.createPawn(6,5);
        assertFalse(moveHandler.isMoveAllowed(6, 4, queen, pieceLayout));
    }
    @Test
    public void queenLegalMoveMadeUpdatePos(){
        moveHandler.movePiece(7,6, queen, pieceLayout);
        assertTrue(queen.xPos == 7 && queen.yPos == 6);
    }
    @Test
    public void queenIllegalMoveMadeNotUpdatePos(){
        int startX = queen.xPos;
        int startY = queen.yPos;
        moveHandler.movePiece(7,4, queen, pieceLayout);
        assertTrue(queen.xPos == startX && queen.yPos == startY);
    }
    //------------------Knight Tests----------------------------
    @Test
    public void knightMoveLegal() {
        assertTrue(moveHandler.isMoveAllowed(0, 6, knight, pieceLayout));
        assertTrue(moveHandler.isMoveAllowed(1, 5, knight, pieceLayout));
        assertTrue(moveHandler.isMoveAllowed(3, 5, knight, pieceLayout));
        assertTrue(moveHandler.isMoveAllowed(4, 6, knight, pieceLayout));
    }
    @Test
    public void knightMoveIllegal() {
        assertFalse(moveHandler.isMoveAllowed(1, 7, knight, pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(1, 6, knight, pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(2, 6, knight, pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(3, 6, knight, pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(3, 7, knight, pieceLayout));
    }
    @Test
    public void knightMoveEnemyBlocking() {
        PieceFactory.isPlayerOne = false;
        pieceLayout[5][1] = PieceFactory.createPawn(1, 5);
        assertTrue(moveHandler.isMoveAllowed(1, 5, knight, pieceLayout));
    }
    @Test
    public void knightMovePathBlocked() {
        PieceFactory.isPlayerOne = true;
        pieceLayout[6][2] = PieceFactory.createPawn(2,6);
        assertTrue(moveHandler.isMoveAllowed(1, 5, knight, pieceLayout));
    }
    @Test
    public void knightLegalMoveMadeUpdatePos(){
        moveHandler.movePiece(3,5, knight, pieceLayout);
        assertTrue(knight.xPos == 3 && knight.yPos == 5);
    }
    @Test
    public void knightIllegalMoveMadeNotUpdatePos(){
        int startX = knight.xPos;
        int startY = knight.yPos;
        moveHandler.movePiece(0,5, knight, pieceLayout);
        assertTrue(knight.xPos == startX && knight.yPos == startY);
    }
    //-------------------Test king checked-------------------------
    @Test
    public void kingIsChecked(){
        pieceLayout = new Piece[8][8];
        PieceFactory.isPlayerOne = true;
        king  = PieceFactory.createKing(5,0);
        pieceLayout[0][5] = king;
        PieceFactory.isPlayerOne = false;
        pieceLayout[4][5] = PieceFactory.createQueen(5,4);
        assertTrue(moveHandler.isKingCheck(player, king, pieceLayout));
    }
    @Test
    public void kingIsNotChecked(){
        pieceLayout = new Piece[8][8];
        king  = PieceFactory.createKing(5,0);
        pieceLayout[0][5] = king;
        pieceLayout[4][5] = PieceFactory.createQueen(5,4);
        assertFalse(moveHandler.isKingCheck(player, king, pieceLayout));
    }
}


