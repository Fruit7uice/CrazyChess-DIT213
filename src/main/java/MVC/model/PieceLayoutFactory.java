package MVC.model;

import MVC.model.PieceFactory;
import MVC.model.Pieces.Piece;

import java.util.ArrayList;
import java.util.Random;

public class PieceLayoutFactory {

    static String[][] playerTwoLayout = {
            {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook",},
            {"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn"}};
    static String[][] playerOneLayout = {
            {"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn",},
            {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"}};


    PieceFactory pieceFactory;

    void PieceLayoutFactory() {
        pieceFactory = new PieceFactory();
    }

    public static Piece[][] createClassicLayout() {
        return createMatrixLayout(playerOneLayout, reverseLayout(playerOneLayout));
    }

    public static Piece[][] createCrazyLayout() {
        String[][] p1Layout = shufflePieceRow(playerOneLayout, 1);

        return createMatrixLayout(p1Layout, reverseLayout(p1Layout));

    }

    private static Piece[][] createMatrixLayout(String[][] p1Layout, String[][] p2Layout) {
        Piece[][] pieces = new Piece[8][8];
        ArrayList<Piece> playerTwoPieces = CreatePieceList(p2Layout, false);
        ArrayList<Piece> playerOnePieces = CreatePieceList(p1Layout, true);
        int p1Index = 0;
        int p2Index = 0;

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {

                if (i < 2) {
                    pieces[i][j] = playerTwoPieces.get(p2Index);
                    System.out.println("Placed Piece at: " + i + " and " + j);
                    p2Index++;
                } else if (i > 5) {
                    pieces[i][j] = playerOnePieces.get(p1Index);
                    System.out.println("Placed Piece at: " + i + " and " + j);
                    p1Index++;
                } else {
                    pieces[i][j] = null;
                }
            }
        }
        return pieces;
    }


    public static ArrayList<Piece> CreatePieceList(String[][] layout, boolean isPlayerOne) {
        PieceFactory.isPlayerOne = isPlayerOne;
        ArrayList<Piece> newPieces = new ArrayList<>();

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                String s = layout[i][j];
                int n = (isPlayerOne == true) ? 6 : 0;
                switch (s) {
                    case "Rook":
                        newPieces.add(PieceFactory.createRook(n + i, j));
                        break;
                    case "Knight":
                        newPieces.add(PieceFactory.createKnight(n + i, j));
                        break;
                    case "Bishop":
                        newPieces.add(PieceFactory.createBishop(n + i, j));
                        break;
                    case "Queen":
                        newPieces.add(PieceFactory.createQueen(n + i, j));
                        break;
                    case "King":
                        newPieces.add(PieceFactory.createKing(n + i, j));
                        break;
                    case "Pawn":
                        newPieces.add(PieceFactory.createPawn(n + i, j));
                        break;
                    default:
                        System.out.println("Error: Piece not created, possibly a faulty initial list. ");
                        break;
                }
            }
        }
        return newPieces;
    }

    private static String[][] shufflePieceRow(String[][] pieces, int row) {
        Random rd = new Random();

        for (int i = pieces[row].length - 1; i > 0; i--) {
            int index = rd.nextInt(i + 1);

            String s = pieces[row][index];
            pieces[row][index] = pieces[row][i];
            pieces[row][i] = s;

        }
        System.out.println(pieces);
        return pieces;
    }


    private static String[][] reverseLayout(String[][] layout){
        int index = 0;
        String[][] reversed = new String[layout.length][layout[0].length];
            for(int i = (layout.length-1) ; i >= 0  ; i--){
                for(int j = 0 ; j < layout[i].length ; j++){
                    reversed[i][j] = layout[index][j];
                }
                index++;
            }
            return reversed;
        }
}
