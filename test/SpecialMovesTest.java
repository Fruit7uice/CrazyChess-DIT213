/*
import MVC.model.Board;
import MVC.model.PieceFactory;
import MVC.model.Pieces.*;
import MVC.model.SpecialMoves.Castle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

*/
/**
 * Gör om testerna då flera fel hittades. Kolla så att testerna går igenom
 * även efter ändringar gjorts i modellen. saknas: tryAndCheckMove i movehandler
 * och lite if else satser. I Castle saknas hälften, se klass Castle.
 *//*


public class SpecialMovesTest {
    Piece leftWhiteRook;
    Piece rightWhiteRook;
    Piece leftBlackRook;
    Piece rightBlackRook;
    Piece whiteKing;
    Piece blackKing;
    Piece whitePawn;
    Piece blackPawn;
    Piece[][] pieceLayout = new Piece[8][8];
    Board board = new Board(pieceLayout);
    MoveHandler moveHandler = new MoveHandler(board);
    */
/*Castle castle = new Castle();*//*


    @Before
    public void setup(){
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

    // ej klar implementerat än :(
    @Test
    public void castleWhileCheckedPlayerOne(){
        PieceFactory.isPlayerOne = false;
        Piece queen = PieceFactory.createQueen(4,5);
        pieceLayout[5][4] = queen;
        assertTrue(castle.pathCheckedWhiteLongCastle()
                    && castle.pathCheckedWhiteShortCastle());
    }

    // ej klar implementerat än :(
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

}
*/
