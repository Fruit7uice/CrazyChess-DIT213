package MVC.model.strategies;
/**
 * @author Alva Johansson
 */
public class DiagonalStrategy implements IMoveStrategy {
    /**
     * @param startX Starting x coordinate on the board
     * @param startY Starting y coordinate on the board
     * @param newX The x coordinate where the piece want to move
     * @param newY The y coordinate where the piece want to move
     * @return returns a bool if the desired movement is a diagonal movement
     */
    public boolean move(int startX, int startY, int newX, int newY ) {
        if (Math.abs(startX - newX) == Math.abs(startY - newY)) {
            return true;
        }else{
            return false;
        }
    }
}
