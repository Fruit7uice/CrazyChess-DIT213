package MVC.model.SpecialMoves;

import MVC.controller.BoardController;
import MVC.controller.PromotionController;
import MVC.model.PieceFactory;
import MVC.model.Pieces.*;
import javafx.scene.control.Button;

import java.util.Objects;

/**
 * Promotion for pawn, if it reaches either the top or
 * the bottom of the board, it will be able to promote
 * to a more powerful piece
 * @author Joel Leiditz Thorsson
 */
public class PromotionChecker {

    public boolean tryPromotion (Piece p){
        if ((p.yPos == 0 || p.yPos == 7) && Objects.equals(p.getType(), "Pawn")){

            return true;
        }  else {
            return false;
        }
    }
    //imagepath, fixa detta! också att fixa så att bonden kan bli andra pjäser än bara dam
    // kanske byta namn om denna kallas på ofta?
    // skapa pjäsen med factory istället senare
    // hur funkar det med isPlayer1 här?

    public static void bishopChoice(Piece p) {
        PieceFactory.createBishop(p.xPos, p.yPos);
    }

    public static void queenChoice(Piece p) {
        PieceFactory.createQueen(p.xPos, p.yPos);
    }

    public static void knightChoice(Piece p) {
        PieceFactory.createKnight(p.xPos, p.yPos);
    }

    public static void rookChoice(Piece p) {
        PieceFactory.createRook(p.xPos, p.yPos);
    }

}
