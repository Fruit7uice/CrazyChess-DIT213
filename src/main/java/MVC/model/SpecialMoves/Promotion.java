package MVC.model.SpecialMoves;

import MVC.model.Board;
import MVC.model.PieceFactory;
import MVC.model.Pieces.*;
import java.util.Objects;

/**
 * Promotion for pawn, if it reaches either the top or
 * the bottom of the board, it will be able to promote
 * to a more powerful piece
 * @author Joel Leiditz Thorsson
 */
public class Promotion {
    public Board board;

    /**
     * Checks if the piece can promote.
     * @param p The current piece that tries to promote
     * @return true if statement holds.
     */
    public boolean tryPromotion (Piece p) {
        return (p.yPos == 0 || p.yPos == 7) && Objects.equals(p.getType(), "Pawn");
    }

    /**
     * Checks if a pawn can promote, depending on what button the client uses
     * a new piece will be created in the pawn's position.
     * @param p The current piece that tries promotion. Must be a pawn.
     */
    public void promotion(Piece p) {
        if(tryPromotion(p)) {
            if (queenChoice()) {
                PieceFactory.createQueen(p.xPos, p.yPos);
                //board.updateGameLayout(p, p.xPos, p.yPos);
            } else if (bishopChoice()){
                PieceFactory.createBishop(p.xPos, p.yPos);
                //board.updateGameLayout(p, p.xPos, p.yPos);
            } else if (knightChoice()) {
                PieceFactory.createKnight(p.xPos,p.yPos);
                //board.updateGameLayout(p, p.xPos, p.yPos);
            } else if (rookChoice()) {
                PieceFactory.createRook(p.xPos,p.yPos);
                //board.updateGameLayout(p, p.xPos, p.yPos);
            }
        }
    }

    /**
     * The client chose queen and this method is run.
     * @return true if method is run.
     */
    public static boolean queenChoice(){
        System.out.println("createqueen set to true");
        return true;
    }

    /**
     * The client chose bishop and this method is run.
     * @return true if method is run.
     */
    public static boolean bishopChoice() {
        System.out.println("bishopChoice set to true");
        return true;
    }

    /**
     * The client chose knight and this method is run.
     * @return true if method is run.
     */
    public static boolean knightChoice() {
        System.out.println("kightChoice set to true");
        return true;
    }

    /**
     * The client chose rook and this method is run.
     * @return true if method is run.
     */
    public static boolean rookChoice() {
        System.out.println("rookChoice set to true");
        return true;
    }

}
