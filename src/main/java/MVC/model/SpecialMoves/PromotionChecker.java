package MVC.model.SpecialMoves;

import MVC.model.Pieces.*;

/**
 * Promotion for pawn, if it reaches either the top or
 * the bottom of the board, it will be able to promote
 * to a more powerful piece
 * @author Joel Leiditz Thorsson
 */
public class PromotionChecker {

    public boolean tryPromotion (Piece p){
        return p.yPos == 0 || p.yPos == 7;
    }
    //imagepath, fixa detta! också att fixa så att bonden kan bli andra pjäser än bara dam
    // kanske byta namn om denna kallas på ofta?
    // skapa pjäsen med factory istället senare
    // hur funkar det med isPlayer1 här?
    public void promotion(Piece p) {

        if (p.getType() == "Pawn" && tryPromotion(p)) {
            choosePice(p, showOptionForPromotion());
            Queen queen = new Queen(p.xPos, p.yPos, p.width, p.height,
                    "vit dama", "svart dama", "Queen", true);
        }
    }
    public int showOptionForPromotion() {
        return 0;
    }
    public static void choosePice(Piece p, int choice) {

    }

}
