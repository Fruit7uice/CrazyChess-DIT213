import MVC.model.Board;
import MVC.model.PieceFactory;
import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;
import MVC.model.Player;
import MVC.model.SpecialMoves.Castle;
import MVC.model.SpecialMoves.PawnCapture;
import MVC.model.Tuple;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpecialMovesTest {
    Piece leftWhiteRook;
    Piece rightWhiteRook;
    Piece leftBlackRook;
    Piece rightBlackRook;
    Piece whiteKing;
    Piece blackKing;
    Piece[][] pieceLayout = new Piece[8][8];
    Board board = new Board(pieceLayout);
    MoveHandler moveHandler = new MoveHandler(board);
    PawnCapture pv = new PawnCapture(moveHandler, board);

    Player playerOne = new Player(true,moveHandler);
    Player playerTwo = new Player(false, moveHandler);
    Castle castle = new Castle(moveHandler,playerOne,playerTwo,board);

    @Test
    public void whiteShortCastle(){
        PieceFactory.isPlayerOne = true;
        whiteKing = PieceFactory.createKing(4,7);
        rightWhiteRook = PieceFactory.createRook(7,7);
        pieceLayout[7][7] = rightWhiteRook;
        pieceLayout[7][4] = whiteKing;
        printMatrix(pieceLayout);
        castle.preconditionsWhiteShortCastle(pieceLayout, whiteKing);
        assertFalse(castle.pathCheckedWhiteShortCastle());
        assertTrue(castle.isWhiteCastleAllowed(whiteKing,pieceLayout));
        assertTrue(castle.isWhiteShortCastle(6,7));
        assertTrue(castle.isMoveWhiteCastle(6,7));
        assertFalse(castle.isCastling(playerTwo,whiteKing,6,7,pieceLayout));
        castle.performCastle(playerOne,whiteKing,6,7,pieceLayout);

    }

    @Test
    public void whiteLongCastle(){
        PieceFactory.isPlayerOne = true;
        whiteKing = PieceFactory.createKing(4,7);
        leftWhiteRook = PieceFactory.createRook(0,7);
        pieceLayout[7][0] = leftWhiteRook;
        pieceLayout[7][4] = whiteKing;
        castle.preconditionsWhiteLongCastle(pieceLayout, whiteKing);
        assertFalse(castle.pathCheckedWhiteLongCastle());
        assertTrue(castle.isWhiteCastleAllowed(whiteKing,pieceLayout));
        assertTrue(castle.isWhiteLongCastle(2,7));
        assertTrue(castle.isMoveWhiteCastle(2,7));
        castle.performCastle(playerOne,whiteKing,2,7,pieceLayout);

    }

    @Test
    public void blackShortCastle(){
        PieceFactory.isPlayerOne = false;
        blackKing = PieceFactory.createKing(4,0);
        leftBlackRook = PieceFactory.createRook(7,0);
        pieceLayout[0][7] = leftBlackRook;
        pieceLayout[0][4] = whiteKing;
        castle.preconditionsBlackShortCastle(pieceLayout, blackKing);
        assertFalse(castle.pathCheckedBlackShortCastle());
        assertTrue(castle.isBlackCastleAllowed(blackKing,pieceLayout));
        assertTrue(castle.isBlackShortCastle(6,0));
        assertTrue(castle.isMoveBlackCastle(6,0));
        castle.performCastle(playerTwo,blackKing,6,0,pieceLayout);

    }

    @Test
    public void blackLongCastle(){
        PieceFactory.isPlayerOne = false;
        blackKing = PieceFactory.createKing(4,0);
        rightBlackRook = PieceFactory.createRook(0,0);
        pieceLayout[0][0] = rightBlackRook;
        pieceLayout[0][4] = blackKing;
        printMatrix(pieceLayout);
        castle.preconditionsBlackLongCastle(pieceLayout, blackKing);
        assertFalse(castle.pathCheckedBlackLongCastle());
        assertTrue(castle.isBlackCastleAllowed(blackKing,pieceLayout));
        assertTrue(castle.isBlackLongCastle(2,0));
        assertTrue(castle.isMoveBlackCastle(2,0));
        castle.performCastle(playerTwo,blackKing,2,0,pieceLayout);
    }

    @Test
    public void whiteCastleAfterMoved(){
        PieceFactory.isPlayerOne = true;
        whiteKing = PieceFactory.createKing(4,7);
        leftWhiteRook = PieceFactory.createRook(0,7);
        pieceLayout[7][0] = leftWhiteRook;
        pieceLayout[7][4] = whiteKing;
        moveHandler.movePieceInLayout(4,6,whiteKing,pieceLayout);
        moveHandler.movePieceInLayout(4,7,whiteKing,pieceLayout);
        castle.preconditionsWhiteLongCastle(pieceLayout, whiteKing);
        assertFalse(castle.pathCheckedWhiteLongCastle());
        assertFalse(castle.isWhiteCastleAllowed(whiteKing,pieceLayout));
    }

    @Test
    public void castleWhileCheckedPlayerOne(){
        PieceFactory.isPlayerOne = true;
        whiteKing = PieceFactory.createKing(4,7);
        leftWhiteRook = PieceFactory.createRook(0,7);
        pieceLayout[7][0] = leftWhiteRook;
        pieceLayout[7][4] = whiteKing;
        PieceFactory.isPlayerOne = false;
        Piece blackQueen = PieceFactory.createQueen(2,3);
        pieceLayout[3][2] = blackQueen;
        moveHandler.updatePiecePossibleMoves(blackQueen,pieceLayout);
        HashSet<Tuple<Integer, Integer>> possibleMovesPlayerTwo = playerTwo.updatePlayerPossibleMoves(pieceLayout);
        playerTwo.setOfAllMoves.addAll(possibleMovesPlayerTwo);
        castle.preconditionsWhiteLongCastle(pieceLayout, whiteKing);
        castle.pathCheckedWhiteLongCastle();
        assertFalse(castle.isWhiteCastleAllowed(whiteKing,pieceLayout));
    }

    @Test
    public void castleWhileCheckedPlayerTwo(){
        PieceFactory.isPlayerOne = false;
        blackKing = PieceFactory.createKing(4,0);
        leftBlackRook = PieceFactory.createRook(7,0);
        pieceLayout[0][7] = leftBlackRook;
        pieceLayout[0][4] = blackKing;
        PieceFactory.isPlayerOne = true;
        Piece whiteQueen = PieceFactory.createQueen(5,3);
        pieceLayout[3][5] = whiteQueen;
        printMatrix(pieceLayout);
        moveHandler.updatePiecePossibleMoves(whiteQueen,pieceLayout);
        HashSet<Tuple<Integer, Integer>> possibleMovesPlayerOne = playerOne.updatePlayerPossibleMoves(pieceLayout);
        playerOne.setOfAllMoves.addAll(possibleMovesPlayerOne);
        castle.preconditionsWhiteLongCastle(pieceLayout, blackKing);
        castle.pathCheckedBlackShortCastle();
        assertFalse(castle.isBlackCastleAllowed(blackKing,pieceLayout));
    }

    @Test
    public void whiteCastleWhilePathBlocked(){
        PieceFactory.isPlayerOne = true;
        whiteKing = PieceFactory.createKing(4,7);
        leftWhiteRook = PieceFactory.createRook(0,7);
        Piece blackQueen = PieceFactory.createQueen(3,7);
        pieceLayout[7][0] = leftWhiteRook;
        pieceLayout[7][4] = whiteKing;
        pieceLayout[7][3] = blackQueen;
        printMatrix(pieceLayout);
        castle.preconditionsWhiteLongCastle(pieceLayout, whiteKing);
        castle.pathCheckedWhiteLongCastle();
        assertFalse(castle.isWhiteCastleAllowed(whiteKing,pieceLayout));
    }

    @Test
    public void blackCastleWhilePathBlocked(){
        PieceFactory.isPlayerOne = false;
        blackKing = PieceFactory.createKing(4,0);
        rightBlackRook = PieceFactory.createRook(0,0);
        Piece blackQueen = PieceFactory.createQueen(3,0);
        pieceLayout[0][0] = rightBlackRook;
        pieceLayout[0][4] = blackKing;
        pieceLayout[0][3] = blackQueen;
        printMatrix(pieceLayout);
        castle.preconditionsBlackLongCastle(pieceLayout, blackKing);
        castle.pathCheckedBlackLongCastle();
        assertFalse(castle.isBlackCastleAllowed(blackKing,pieceLayout));
    }

    public static void printMatrix(Piece[][] pieceLayout){
        System.out.println("\n {");
        for (int i = 0; i < pieceLayout.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < pieceLayout[i].length; j++) {
                if (pieceLayout[i][j] != null){
                    System.out.print(pieceLayout[i][j].getType() + ", ");
                }
                else{
                    System.out.print(pieceLayout[i][j] + ", ");
                }
            }
            System.out.print("} \n");
        }
        System.out.println("}");
    }
    
}

