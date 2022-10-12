package MVC.model;

import MVC.model.Pieces.*;

public class PieceFactory {

    public static boolean isPlayerOne;

    public static Piece createRook(int x, int y){
        //System.out.println("Piece(Rook) created with x:" + x + " and y: " + y);
        String blackPath =  "/PieceImages/Black_Rook.png";
        String whitePath = "/PieceImages/White_Rook.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Rook(y, x, 100, 100, path, "Rook", isPlayerOne, false);

    }

    public static Piece createBishop(int x, int y){
        //System.out.println("Piece(Bishop) created with x:" + x + " and y: " + y);
        String blackPath =  "/PieceImages/Black_Bishop.png";
        String whitePath = "/PieceImages/White_Bishop.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Bishop(y, x, 100, 100, path, "Bishop", isPlayerOne, false);
    }

    public static Piece createKnight(int x, int y){
        //System.out.println("Piece(Knight) created with x:" + x + " and y: " + y);
        String blackPath =  "/PieceImages/Black_Knight.png";
        String whitePath = "/PieceImages/White_Knight.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Knight(y, x, 100, 100, path, "Knight", isPlayerOne, false);
    }

    public static Piece createQueen(int x, int y){
        //System.out.println("Piece(Queen) created with x:" + x + " and y: " + y);
        String blackPath =  "/PieceImages/Black_Queen.png";
        String whitePath = "/PieceImages/White_Queen.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Queen(y, x, 100, 100, path, "Queen", isPlayerOne, false);
    }

    public static Piece createKing(int x, int y){
        //System.out.println("Piece(King) created with x:" + x + " and y: " + y);
        String blackPath =  "/PieceImages/Black_King.png";
        String whitePath = "/PieceImages/White_King.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new King(y, x, 100, 100, path, "King", isPlayerOne, false);
    }

    public static Piece createPawn(int x, int y){
        //System.out.println("Piece(Pawn) created with x:" + x + " and y: " + y);
        String blackPath =  "/PieceImages/Black_Pawn.png";
        String whitePath = "/PieceImages/White_Pawn.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Pawn(y, x, 100, 100, path, "Pawn", isPlayerOne, false);
    }

}
