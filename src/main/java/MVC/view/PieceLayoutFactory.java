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
        int p1Index = 0;
        int p2Index = 0;

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {

                if (i < 2){
                    pieces[i][j] = playerTwoPieces.get(p2Index);
                    System.out.println("Placed Piece at: " + i + " and " + j);
                    p2Index++;
                }
                else if (i > 5) {
                    pieces[i][j] = playerOnePieces.get(p1Index);
                    System.out.println("Placed Piece at: " + i + " and " + j);
                    p1Index++;
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
                int n = (isPlayerOne == true) ? 6 : 0;
                switch (s){
                    case "Rook":
                        newPieces.add(PieceFactory.createRook(n+i, j));
                        break;
                    case "Knight":
                        newPieces.add(PieceFactory.createKnight(n+i, j));
                        break;
                    case "Bishop":
                        newPieces.add(PieceFactory.createBishop(n+i, j));
                        break;
                    case "Queen":
                        newPieces.add(PieceFactory.createQueen(n+i, j));
                        break;
                    case "King":
                        newPieces.add(PieceFactory.createKing(n+i, j));
                        break;
                    case "Pawn":
                        newPieces.add(PieceFactory.createPawn(n+i, j));
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
