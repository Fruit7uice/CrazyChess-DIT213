package MVC.model.strategies;


/**
 * Movement strategy for the piece pawn. The boolean hasMoved might
 * be moved to another class later. Before it has been moved the pawn
 * is able to move two tiles forward
 *
 * @author Joel Leiditz Thorsson
 */
public class PawnStrategy implements IMoveStrategy {


    /**
     * Method that holds the logic for the move strategy that revolves around the Pawn.
     * @param startX
     * @param startY
     * @param newX
     * @param newY
     * @param hasMoved
     * @param isPlayerOne
     * @return
     */

    public static boolean move(int startX, int startY, int newX, int newY, boolean hasMoved, boolean isPlayerOne) {
        if (isPlayerOne){
            if (!hasMoved) {// not finished, move logic to another class.
                return (newY - startY) == -2 || (newY - startY) == -1 && startX == newX;
            } else return (newY - startY) == -1 && startX == newX;
        }
        else {
            if (!hasMoved) {// not finished, move logic to another class.
                return (newY - startY) == 2 || (newY - startY) == 1
                        && startX == newX;
            } else return (newY - startY) == 1 && startX == newX;
        }
    }
}
