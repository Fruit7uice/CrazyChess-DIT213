package MVC.model;

import MVC.model.Pieces.Piece;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class creates the layout for the different game modes,
 * Classic and Crazy chess.
 */
public class PieceLayoutFactory {

    private static String[][] playerOneLayout = {
            {"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn",},
            {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"}};


    PieceFactory pieceFactory;

    void PieceLayoutFactory(){
        pieceFactory = new PieceFactory();
    }

    public static Piece[][] createClassicLayout() {
        return createMatrixLayout(playerOneLayout, reverseLayout(playerOneLayout));
    }

    public static Piece[][] createCrazyLayout() {
        String[][] p1Layout = shufflePieceRow(playerOneLayout, 1);

        return createMatrixLayout(p1Layout, reverseLayout(p1Layout));

    }


    /**
     * Creates the Matrix which is later used to work on the game.
     * Uses the CreatePieceList, so it can differentiate between where it should put the pieces in the matrix.
     * @return A Matrix filled with Pieces.
     */
    private static Piece[][] createMatrixLayout(String[][] p1Layout, String[][] p2Layout){
        Piece[][] pieces = new Piece[8][8];
        ArrayList<Piece> playerTwoPieces = CreatePieceList(p2Layout, false);
        ArrayList<Piece> playerOnePieces = CreatePieceList(p1Layout, true);
        int p1Index = 0;
        int p2Index = 0;

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {

                if (i < 2){
                    pieces[i][j] = playerTwoPieces.get(p2Index);
                    System.out.println("Placed Piece at: Row(Y) " + i + " and Col(X) " + j);
                    p2Index++;
                }
                else if (i > 5) {
                    pieces[i][j] = playerOnePieces.get(p1Index);
                    System.out.println("Placed Piece at: Row " + i + " and Col " + j);
                    p1Index++;
                }
                else {
                    pieces[i][j] = null;
                }
            }
        }
        return pieces;
    }


    /**
     * Creates an ArrayList of Pieces with the attribute if it is player One or Two.
     * @param layout The String Matrix containing the way we want to fill 2 Rows and 8 Columns with Pieces.
     * @param isPlayerOne Boolean saying if the piece created belongs to player One or Two.
     * @return ArrayList filled with the Pieces just created with its x and y positions.
     */
    public static ArrayList<Piece> CreatePieceList(String[][] layout, boolean isPlayerOne){
        PieceFactory.isPlayerOne = isPlayerOne;
        ArrayList<Piece> newPieces = new ArrayList<>();
        int n = (isPlayerOne) ? 6 : 0;
        for (int row = 0; row < layout.length; row++) {
            for (int col = 0; col < layout[row].length; col++) {
                String s = layout[row][col];
                int finalRow = row + n;

                switch (s){
                    case "Rook":
                        newPieces.add(PieceFactory.createRook(col, finalRow));
                        break;
                    case "Knight":
                        newPieces.add(PieceFactory.createKnight(col, finalRow));
                        break;
                    case "Bishop":
                        newPieces.add(PieceFactory.createBishop(col, finalRow));
                        break;
                    case "Queen":
                        newPieces.add(PieceFactory.createQueen(col, finalRow));
                        break;
                    case "King":
                        newPieces.add(PieceFactory.createKing(col, finalRow));
                        break;
                    case "Pawn":
                        newPieces.add(PieceFactory.createPawn(col, finalRow));
                        break;
                    case ("null"):
                        newPieces.add(null);
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
        String[][] copy = createMatrixCopy(pieces);
        Random rd = new Random();

        for (int i = copy[row].length-1; i > 0; i--) {
            int index = rd.nextInt(i + 1);

            String s = copy[row][index];
            copy[row][index] = copy[row][i];
            copy[row][i] = s;

        }
        System.out.println(copy);
        return copy;
    }

    private static String[][] createMatrixCopy(String[][] pieces) {
        String[][] copy = new String[pieces.length][pieces[0].length];
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                copy[i][j] = pieces[i][j];
            }
        }
        return copy;
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
