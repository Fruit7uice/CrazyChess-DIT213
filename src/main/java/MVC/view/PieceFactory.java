package MVC.view;

import MVC.model.Pieces.*;

public class PieceFactory {

    public static boolean isPlayerOne;
    void PieceFactory(boolean isPlayerOne){
        this.isPlayerOne = isPlayerOne;
    }
    void PieceFactory(){

    }

    public static Piece createRook(int x, int y){
        return new Rook(x, y, 100, 100, "pathOne", "pathTwo", "Rook", isPlayerOne);
    }

    public static Piece createBishop(int x, int y){
        return new Bishop(x, y, 100, 100, "pathOne", "pathTwo", "Bishop", isPlayerOne);
    }

    public static Piece createKnight(int x, int y){
        return new Knight(x, y, 100, 100, "pathOne", "pathTwo", "Knight", isPlayerOne);
    }

    public static Piece createQueen(int x, int y){
        return new Queen(x, y, 100, 100, "pathOne", "pathTwo", "Queen", isPlayerOne);
    }

    public static Piece createKing(int x, int y){
        return new King(x, y, 100, 100, "pathOne", "pathTwo", "King", isPlayerOne);
    }

    public static Piece createPawn(int x, int y){
        return new Pawn(x, y, 100, 100, "pathOne", "pathTwo", "Pawn", isPlayerOne);
    }

}
