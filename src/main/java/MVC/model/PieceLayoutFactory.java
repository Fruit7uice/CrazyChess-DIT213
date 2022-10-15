package MVC.model;

import MVC.model.PieceFactory;
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


    /**
     * Creates the Matrix which is later used to work on the game.
     * Uses the CreatePieceList, so it can differentiate between where it should put the pieces in the matrix.
     * @return A Matrix filled with Pieces.
     */
    public static Piece[][] createMatrixLayout(){
        Piece[][] pieces = new Piece[8][8];
        ArrayList<Piece> playerTwoPieces = CreatePieceList(playerTwoLayout, false);
        ArrayList<Piece> playerOnePieces = CreatePieceList(playerOneLayout, true);
        //System.out.println(playerOnePieces.get(6).getType() + " Position X: " + playerOnePieces.get(8).xPos + " and Y: " + playerOnePieces.get(8).yPos );
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

}
