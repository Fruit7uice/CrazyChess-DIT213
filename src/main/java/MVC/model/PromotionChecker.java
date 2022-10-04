package MVC.model;

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
    public void promotePawn(Piece p) {
        if (p instanceof Pawn && tryPromotion(p)) {
            Queen queen = new Queen(p.xPos, p.yPos, p.width, p.height,
                                    "vit dama", "svart dama", "Queen");
        }
    }
}
