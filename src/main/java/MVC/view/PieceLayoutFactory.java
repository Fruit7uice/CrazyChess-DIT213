package MVC.view;

import MVC.model.Pieces.Piece;

import java.util.ArrayList;

public class PieceLayoutFactory {

    static String[][] playerTwoLayout = {
            {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook",},
            {"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn"}};
    static String[][] playerOneLayout = {
            {"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn",},
            {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"}};


    PieceFactory pieceFactory;

    void PieceLayoutFactory(){
        pieceFactory = new PieceFactory();
    }


    public static Piece[][] createMatrixLayout(){
        Piece[][] pieces = new Piece[8][8];
        ArrayList<Piece> playerTwoPieces = CreatePieceList(playerTwoLayout, false);
        ArrayList<Piece> playerOnePieces = CreatePieceList(playerOneLayout, true);

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {

                if (i < 2){
                    pieces[i][j] = playerTwoPieces.get(i+j);
                }
                else if (i > 5) {
                    pieces[i][j] = playerOnePieces.get(i+j);
                }
                else {
                    pieces[i][j] = null;
                }
            }
        }
        return pieces;
    }


    public static ArrayList<Piece> CreatePieceList(String[][] layout, boolean isPlayerOne){
        PieceFactory.isPlayerOne = isPlayerOne;
        ArrayList<Piece> newPieces = new ArrayList<>();

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                String s = layout[i][j];
                switch (s){
                    case "Rook":
                        newPieces.add(PieceFactory.createRook(i, j));
                        break;
                    case "Knight":
                        newPieces.add(PieceFactory.createKnight(i, j));
                        break;
                    case "Bishop":
                        newPieces.add(PieceFactory.createBishop(i, j));
                        break;
                    case "Queen":
                        newPieces.add(PieceFactory.createQueen(i, j));
                        break;
                    case "King":
                        newPieces.add(PieceFactory.createKing(i, j));
                        break;
                    case "Pawn":
                        newPieces.add(PieceFactory.createPawn(i, j));
                        break;
                    default:
                        System.out.println("Error: Piece not created, possibly a faulty initial list. ");
                        break;
                }
            }
        }
        return newPieces;
    }

}
