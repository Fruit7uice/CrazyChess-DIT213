package MVC.model;

import MVC.model.Pieces.*;

public class PieceFactory {

    public static boolean isPlayerOne;

    public static Piece createRook(int x, int y){
        System.out.println("Piece(Rook) created with x:" + x + " and y: " + y);
        return new Rook(x, y, 100, 100, "pathOne", "pathTwo", "Rook", isPlayerOne, false);

    }

    public static Piece createBishop(int x, int y){
        System.out.println("Piece(Bishop) created with x:" + x + " and y: " + y);
        return new Bishop(x, y, 100, 100, "pathOne", "pathTwo", "Bishop", isPlayerOne, false);
    }

    public static Piece createKnight(int x, int y){
        System.out.println("Piece(Knight) created with x:" + x + " and y: " + y);
        return new Knight(x, y, 100, 100, "pathOne", "pathTwo", "Knight", isPlayerOne, false);

    }

    public static Piece createQueen(int x, int y){
        System.out.println("Piece(Queen) created with x:" + x + " and y: " + y);
        return new Queen(x, y, 100, 100, "pathOne", "pathTwo", "Queen", isPlayerOne, false);

    }

    public static Piece createKing(int x, int y){
        System.out.println("Piece(King) created with x:" + x + " and y: " + y);
        return new King(x, y, 100, 100, "pathOne", "pathTwo", "King", isPlayerOne, false);
    }

    public static Piece createPawn(int x, int y){
        System.out.println("Piece(Pawn) created with x:" + x + " and y: " + y);
        return new Pawn(x, y, 100, 100, "pathOne", "pathTwo", "Pawn", isPlayerOne, false);
    }

}
