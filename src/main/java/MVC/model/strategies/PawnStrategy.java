package MVC.model.strategies;


/**
 * Movement strategy for the piece pawn. The boolean hasMoved might
 * be moved to another class later. Before it has been moved the pawn
 * is able to move two tiles forward
 *
 * @author Joel Leiditz Thorsson
 */
public class PawnStrategy implements IMoveStrategy {

    public static boolean move(int startX, int startY, int endX, int endY, boolean hasMoved, boolean isPlayerOne) {
        if (isPlayerOne){
            if (!hasMoved) {// not finished, move logic to another class.
                return (endY - startY) == -2 || (endY - startY) == -1 && startX == endX;
            } else return (endY - startY) == -1 && startX == endX;
        }
        else {
            if (!hasMoved) {// not finished, move logic to another class.
                return (endY - startY) == 2 || (endY - startY) == 1
                        && startX == endX;
            } else return (endY - startY) == 1 && startX == endX;
        }
    }
}
