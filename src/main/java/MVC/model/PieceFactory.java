package MVC.model;

import MVC.model.Pieces.*;

public class PieceFactory {

    public static boolean isPlayerOne;

    public static Piece createRook(int row, int col){
        //System.out.println("Piece(Rook) created with row:" + row + " and y: " + y);
        String blackPath =  "/PieceImages/Black_Rook.png";
        String whitePath = "/PieceImages/White_Rook.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Rook(col, row, 100, 100, path, "Rook", isPlayerOne, false);
    }

    public static Piece createBishop(int row, int col){
        //System.out.println("Piece(Bishop) created with row:" + row + " and col: " + col);
        String blackPath =  "/PieceImages/Black_Bishop.png";
        String whitePath = "/PieceImages/White_Bishop.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Bishop(col, row, 100, 100, path, "Bishop", isPlayerOne, false);
    }

    public static Piece createKnight(int row, int col){
        //System.out.println("Piece(Knight) created with row:" + row + " and y: " + y);
        String blackPath =  "/PieceImages/Black_Knight.png";
        String whitePath = "/PieceImages/White_Knight.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Knight(col, row, 100, 100, path, "Knight", isPlayerOne, false);
    }

    public static Piece createQueen(int row, int col){
        //System.out.println("Piece(Queen) created with row:" + row + " and col: " + col);
        String blackPath =  "/PieceImages/Black_Queen.png";
        String whitePath = "/PieceImages/White_Queen.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Queen(col, row, 100, 100, path, "Queen", isPlayerOne, false);
    }

    public static Piece createKing(int row, int col){
        //System.out.println("Piece(King) created with row:" + row + " and col: " + col);
        String blackPath =  "/PieceImages/Black_King.png";
        String whitePath = "/PieceImages/White_King.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new King(col, row, 100, 100, path, "King", isPlayerOne, false);
    }

    public static Piece createPawn(int row, int col){
        //System.out.println("Piece(Pawn) created with row:" + row + " and col: " + col);
        String blackPath =  "/PieceImages/Black_Pawn.png";
        String whitePath = "/PieceImages/White_Pawn.png";
        String path = (isPlayerOne) ? whitePath : blackPath;
        return new Pawn(col, row, 100, 100, path, "Pawn", isPlayerOne, false);
    }

}
