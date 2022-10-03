package MVC.model.strategies;

/**
 * Movement strategy for the piece knight.
 * @author Joel Leiditz Thorsson
 */
public class KnightStrategy implements IMoveStrategy {

    @Override
    public void move(int startX, int startY, int endX, int endY) {
        if(Math.abs(endX-startX)+Math.abs(endY-startY) == 3
                && Math.abs(endX-endY) > 0){
            startX = endX;
            startY = endY;
        }
    }
}
