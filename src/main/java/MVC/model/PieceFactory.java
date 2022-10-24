package MVC.model;

import MVC.model.Pieces.*;

/**
 * This class represents a factory for the pieces of which
 * all of the pieces are created by this factory. 
 */
public class PieceFactory {

    public static boolean isPlayerOne;

    public static Piece createRook(int x, int y){
        //System.out.println("Piece(Rook) created with row:" + row + " and y: " + y);
        String blackPath =  "/PieceImages/Black_Rook.png";
        String whitePath = "/PieceImages/White_Rook.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Rook(x, y, 100, 100, path, "Rook", isPlayerOne, false);
    }

    public static Piece createBishop(int x, int y){
        //System.out.println("Piece(Bishop) created with row:" + row + " and col: " + col);
        String blackPath =  "/PieceImages/Black_Bishop.png";
        String whitePath = "/PieceImages/White_Bishop.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Bishop(x, y, 100, 100, path, "Bishop", isPlayerOne, false);
    }

    public static Piece createKnight(int x, int y){
        //System.out.println("Piece(Knight) created with row:" + row + " and y: " + y);
        String blackPath =  "/PieceImages/Black_Knight.png";
        String whitePath = "/PieceImages/White_Knight.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Knight(x, y, 100, 100, path, "Knight", isPlayerOne, false);
    }

    public static Piece createQueen(int x, int y){
        //System.out.println("Piece(Queen) created with row:" + row + " and col: " + col);
        String blackPath =  "/PieceImages/Black_Queen.png";
        String whitePath = "/PieceImages/White_Queen.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Queen(x, y, 100, 100, path, "Queen", isPlayerOne, false);
    }

    public static Piece createKing(int x, int y){
        //System.out.println("Piece(King) created with row:" + row + " and col: " + col);
        String blackPath =  "/PieceImages/Black_King.png";
        String whitePath = "/PieceImages/White_King.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new King(x, y, 100, 100, path, "King", isPlayerOne, false);
    }

    public static Piece createPawn(int x, int y){
        //System.out.println("Piece(Pawn) created with row:" + row + " and col: " + col);
        String blackPath =  "/PieceImages/Black_Pawn.png";
        String whitePath = "/PieceImages/White_Pawn.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Pawn(x, y, 100, 100, path, "Pawn", isPlayerOne, false);
    }

}
