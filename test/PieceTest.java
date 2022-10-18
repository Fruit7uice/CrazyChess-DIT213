import MVC.controller.MainBoard;
import MVC.model.Board;
import MVC.model.PieceFactory;
import MVC.model.Pieces.*;

import MVC.model.Player;
import MVC.model.Tuple;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;


public class PieceTest {
    Piece king;
    Piece queen;
    Piece knight;
    Piece bishop;
    Piece rook;
    Piece whitePawn;
    Piece blackPawn;
    Player player = new Player(true);

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
        blackPawn = PieceFactory.createPawn(3,1);
        pieceLayout[1][3] = blackPawn;
        whitePawn = PieceFactory.createPawn(3,6);
        pieceLayout[6][3] = whitePawn;
    }

    //-----------------Pawn Tests---------------------------
    @Test
    public void whitePawnMoveLegal() {
        assertTrue(moveHandler.isMoveAllowed(3,4,whitePawn,pieceLayout));
        assertTrue(moveHandler.isMoveAllowed(3,5,whitePawn,pieceLayout));
    }

    @Test
    public void blackPawnMoveLegal() {
        PieceFactory.isPlayerOne = false;
        Piece blackTestPawn = PieceFactory.createPawn(3,1);
        assertTrue(moveHandler.isMoveAllowed(3,2,blackTestPawn,pieceLayout));
        assertTrue(moveHandler.isMoveAllowed(3,3,blackTestPawn,pieceLayout));
    }

    @Test
    public void blackPawnMoveIllegal() {
        PieceFactory.isPlayerOne = false;
        Piece blackTestPawn = PieceFactory.createPawn(3,1);
        assertFalse(moveHandler.isMoveAllowed(2, 2, blackTestPawn, pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(4,2,blackTestPawn,pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(3,1,blackTestPawn,pieceLayout));

    }

    @Test
    public void whitePawnMoveIllegal() {
        PieceFactory.isPlayerOne = true;
        Piece whiteTestPawn = PieceFactory.createPawn(3,6);
        assertFalse(moveHandler.isMoveAllowed(2,5,whiteTestPawn,pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(4,5,whiteTestPawn,pieceLayout));
        assertFalse(moveHandler.isMoveAllowed(3,6,whiteTestPawn,pieceLayout));
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
        moveHandler.tryAndCheckMove(0,2, bishop, pieceLayout);
        assertTrue(bishop.xPos == 0 && bishop.yPos == 2);
    }
    @Test
    public void bishopIllegalMoveMadeNotUpdatePos(){
        int startX = bishop.xPos;
        int startY = bishop.yPos;
        moveHandler.tryAndCheckMove(0,1, bishop, pieceLayout);
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
        moveHandler.tryAndCheckMove(6,0, rook, pieceLayout);
        assertTrue(rook.xPos == 6 && rook.yPos == 0);
    }
    @Test
    public void rookIllegalMoveMadeNotUpdatePos(){
        int startX = rook.xPos;
        int startY = rook.yPos;
        moveHandler.tryAndCheckMove(5,0, rook, pieceLayout);
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
        moveHandler.tryAndCheckMove(7,6, queen, pieceLayout);
        assertTrue(queen.xPos == 7 && queen.yPos == 6);
    }
    @Test
    public void queenIllegalMoveMadeNotUpdatePos(){
        int startX = queen.xPos;
        int startY = queen.yPos;
        moveHandler.tryAndCheckMove(7,4, queen, pieceLayout);
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
        moveHandler.tryAndCheckMove(3,5, knight, pieceLayout);
        assertTrue(knight.xPos == 3 && knight.yPos == 5);
    }
    @Test
    public void knightIllegalMoveMadeNotUpdatePos(){
        int startX = knight.xPos;
        int startY = knight.yPos;
        moveHandler.tryAndCheckMove(0,5, knight, pieceLayout);
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
        assertTrue(moveHandler.isKingCheck(player, pieceLayout));
    }
    @Test
    public void kingIsNotChecked(){
        pieceLayout = new Piece[8][8];
        king  = PieceFactory.createKing(5,0);
        pieceLayout[0][5] = king;
        pieceLayout[4][5] = PieceFactory.createQueen(5,4);
        assertFalse(moveHandler.isKingCheck(player, pieceLayout));
    }

    //-------------------Test king checkmate-------------------------
    @Test
    public void kingIsCheckmate(){
        pieceLayout = new Piece[8][8];
        PieceFactory.isPlayerOne = true;
        king  = PieceFactory.createKing(4,7);
        pieceLayout[7][4] = king;
        PieceFactory.isPlayerOne = false;
        pieceLayout[2][0] = PieceFactory.createRook(0,2);
        pieceLayout[2][2] = PieceFactory.createBishop(2,2);
        pieceLayout[0][7] = PieceFactory.createRook(7,0);
        //moveHandler.createListOfLegalMoves(king,pieceLayout);
        //player.calcListOfLegalMovesPlayer(pieceLayout, moveHandler);
        //List<Tuple<Integer, Integer>> tList = king.listOfLegalMoves;
        //System.out.println(player.playerOneListOfLegalMoves);
        //System.out.println(tList);
        //assertTrue(tList.equals(player.playerOneListOfLegalMoves));
        assertFalse(moveHandler.isKingCheckMate(player, pieceLayout));

    }

    void printMatrix(Piece[][] pieces){
        System.out.println("\n {");
        for (int i = 0; i < pieces.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] != null){
                    System.out.print(pieces[i][j].getType() + ", ");
                }
                else{
                    System.out.print(pieces[i][j] + ", ");
                }
            }
            System.out.print("} \n");
        }
        System.out.println("}");
    }

    @Test
    public void kingIsNotCheckmate(){
        MainBoard mboard = new MainBoard();
        pieceLayout = new Piece[8][8];
        PieceFactory.isPlayerOne = true;
        Piece king  = PieceFactory.createKing(0,0);
        pieceLayout[0][0] = king;
        PieceFactory.isPlayerOne = false;
        Piece bishop = PieceFactory.createBishop(2,2);
        pieceLayout[2][2] = bishop;
        Piece rook = PieceFactory.createRook(7,0);
        pieceLayout[0][7] = rook;
        System.out.println(king.isPlayerOne() + " " + bishop.isPlayerOne() + " " + rook.isPlayerOne());
        printMatrix(pieceLayout);
        assertFalse(moveHandler.isKingCheckMate(player, pieceLayout));
    }

}


