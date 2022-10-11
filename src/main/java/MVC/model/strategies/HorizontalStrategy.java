package MVC.model.strategies;

/**
 * @author Johannes Höher
 */
public class HorizontalStrategy implements IMoveStrategy {
    /**
     * @param startX Starting x coordinate on the board
     * @param startY Starting y coordinate on the board
     * @param newX The x coordinate where the piece want to move
     * @param newY The y coordinate where the piece want to move
     * @return returns a bool if the desired movement is a horizontal movement
     */
    public static boolean move (int startX, int startY, int newX, int newY ){
        return (Math.abs(startX - newX) > 0) && (startY == newY);
        }
}
