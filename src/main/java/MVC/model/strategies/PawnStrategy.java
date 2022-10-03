package MVC.model.strategies;

/**
 * Movement strategy for the piece pawn. The boolean hasMoved might
 * be moved to another class later. Before it has been moved the pawn
 * is able to move two tiles forward
 *
 * @author Joel Leiditz Thorsson
 */
public class PawnStrategy implements IMoveStrategy{
    private boolean hasMoved = false; // not finished, move logic to another class

    @Override
    public void move(int startX, int startY, int endX, int endY) {
        if (!hasMoved) {          // not finished, move logic to another class.
            if((endY-startY) == 2 || (endY-startY) == 1
                && startX == endX) {
                startY = endY;
                hasMoved = true;
        }
    } else {
            if((endY-startY) == 1 && startX == endX) {
                startY = endY;
            }
        }
    }
}
