import MVC.model.Board;
import MVC.model.PieceFactory;
import MVC.model.Pieces.*;
import MVC.model.SpecialMoves.Castle;
import MVC.model.SpecialMoves.PawnCapture;
import MVC.model.SpecialMoves.Promotion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Gör om testerna då flera fel hittades. Kolla så att testerna går igenom
 * även efter ändringar gjorts i modellen. saknas: tryAndCheckMove i movehandler
 * och lite if else satser. I Castle saknas hälften, se klass Castle.
 */

public class SpecialMovesTest {
    Piece leftWhiteRook;
    Piece rightWhiteRook;
    Piece leftBlackRook;
    Piece rightBlackRook;
    Piece whiteKing;
    Piece blackKing;
    Piece whitePawn;
    Piece[][] pieceLayout = new Piece[8][8];
    Board board = new Board(pieceLayout);
    MoveHandler moveHandler = new MoveHandler(board);
    Castle castle = new Castle(moveHandler);
    PawnCapture pv = new PawnCapture(moveHandler);
    Promotion promotion = new Promotion(board);

    @Before
    public void setup(){
        PieceFactory.isPlayerOne = true;
        leftWhiteRook = PieceFactory.createRook(0,7);
        pieceLayout[0][7] = leftWhiteRook;
        rightWhiteRook = PieceFactory.createRook(7,7);
        pieceLayout[7][7] = rightWhiteRook;
        leftBlackRook = PieceFactory.createRook(0,0);
        pieceLayout[0][0] = leftBlackRook;
        rightBlackRook = PieceFactory.createRook(7,0);
        pieceLayout[0][7] = rightBlackRook;
        whiteKing = PieceFactory.createKing(4,7);
        pieceLayout[7][4] = whiteKing;
        blackKing = PieceFactory.createKing(4,0);
        pieceLayout[4][0] = blackKing;
        whitePawn = PieceFactory.createPawn(6,3);
        pieceLayout[3][6] = whitePawn;
    }

    @Test
    public void whiteShortCastle(){
        assertFalse(castle.pathCheckedWhiteShortCastle());
        castle.preconditionsWhiteShortCastle(pieceLayout, whiteKing);
        assertTrue(castle.isCastleAllowed(whiteKing,pieceLayout));
    }

    @Test
    public void whiteLongCastle(){
        assertFalse(castle.pathCheckedWhiteLongCastle());
        castle.preconditionsWhiteLongCastle(pieceLayout, whiteKing);
        assertTrue(castle.isCastleAllowed(whiteKing,pieceLayout));
    }

    @Test
    public void blackShortCastle(){
        assertFalse(castle.pathCheckedBlackShortCastle());
        castle.preconditionsBlackShortCastle(pieceLayout, blackKing);
        assertTrue(castle.isCastleAllowed(blackKing,pieceLayout));
    }

    @Test
    public void blackLongCastle(){
        assertFalse(castle.pathCheckedBlackLongCastle());
        castle.preconditionsBlackLongCastle(pieceLayout, blackKing);
        assertTrue(castle.isCastleAllowed(blackKing,pieceLayout));
    }

    @Test
    public void castleAfterMoved(){
        moveHandler.movePiece(5,7,whiteKing,pieceLayout);
        moveHandler.movePiece(5,6, whiteKing, pieceLayout);
        assertFalse(castle.isCastleAllowed(whiteKing,pieceLayout));
    }

    /*
    // ej klar implementerat än :(
    @Test
    public void castleWhileCheckedPlayerOne(){
        PieceFactory.isPlayerOne = false;
        Piece queen = PieceFactory.createQueen(4,5);
        pieceLayout[5][4] = queen;
        assertTrue(castle.pathCheckedWhiteLongCastle()
                    && castle.pathCheckedWhiteShortCastle());
    }

     ej klar implementerat än :(
    @Test
    public void castleWhileCheckedPlayerTwo(){
        Piece queen = PieceFactory.createQueen(4,5);
        pieceLayout[5][4] = queen;
        assertTrue(castle.pathCheckedBlackLongCastle()
                && castle.pathCheckedBlackShortCastle());
    }

    // Gör detta *läses med alex gerdes röst*
    // testet skall gå igenom
    @Test
    public void castleWhilePathBlocked(){
        Piece knight = PieceFactory.createKnight(1,7);
        pieceLayout[7][1] = knight;
        castle.preconditionsWhiteLongCastle(pieceLayout,whiteKing);
        castle.pathCheckedWhiteLongCastle();
        assertFalse(castle.isCastleAllowed(whiteKing,pieceLayout));
    }

     */

    @Test
    public void playerOneHasCastled(){
        PieceFactory.isPlayerOne = true;
        castle.preconditionsWhiteShortCastle(pieceLayout,whiteKing);
        castle.pathCheckedWhiteShortCastle();
        castle.isCastleAllowed(whiteKing,pieceLayout);
        castle.performCastle(whiteKing,6,7,pieceLayout,board);
        assertTrue(castle.playerOneHasCastled);
    }

    @Test
    public void playerTwoHasCastled(){
        PieceFactory.isPlayerOne = false;
        castle.preconditionsBlackLongCastle(pieceLayout,whiteKing);
        castle.pathCheckedBlackLongCastle();
        castle.isCastleAllowed(blackKing,pieceLayout);
        castle.performCastle(blackKing,2,0,pieceLayout,board);
        assertTrue(castle.playerTwoHasCastled);
    }

    @Test
    public void playerOnePawnCaptures(){
        PieceFactory.isPlayerOne = false;
        Piece dummyPawn = PieceFactory.createPawn(5,2);
        pieceLayout[2][5] = dummyPawn;
        assertTrue(moveHandler.isOccupied(5,2,pieceLayout));
        assertTrue(moveHandler.isOccupiedByEnemy(5,2,whitePawn,pieceLayout));
        assertTrue(pv.isPlayerOnePawnCapture(pieceLayout,whitePawn,5,2));
        pv.playerOnePawnCaptures(pieceLayout,whitePawn,5,2,board);
        assertTrue(whitePawn.xPos == 5 && whitePawn.yPos == 2);
    }

    @Test
    public void playerTwoPawnCaptures(){
        Piece dummyKnight = PieceFactory.createKnight(2,5);
        pieceLayout[5][2] = dummyKnight;
        PieceFactory.isPlayerOne = false;
        Piece blackPawn = PieceFactory.createPawn(1,4);
        pieceLayout[4][1] = blackPawn;
        assertTrue(moveHandler.isOccupied(2,5,pieceLayout));
        assertTrue(moveHandler.isOccupiedByEnemy(2,5,blackPawn,pieceLayout));
        assertTrue(pv.isPlayerTwoPawnCapture(pieceLayout,blackPawn,2,5));
        pv.playerTwoPawnCaptures(pieceLayout,blackPawn,2,5,board);
        assertTrue(blackPawn.xPos == 2 && blackPawn.yPos == 5);
    }

    @Test
    public void promoteToQueen(){
        Piece pawn = PieceFactory.createPawn(5,1);
        pieceLayout[1][5] = pawn;
        //Move and check
        moveHandler.tryAndCheckMove(5,0, pawn, pieceLayout);

        Piece p = pieceLayout[0][5];
        System.out.println(p.getType());
    }
    
}
