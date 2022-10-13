package MVC.model.strategies;
/**
 * @author Johannes Höher
 */
public class KnightStrategy {
    /**
     * @param startX Starting x coordinate on the board
     * @param startY Starting y coordinate on the board
     * @param newX The x coordinate where the piece want to move
     * @param newY The y coordinate where the piece want to move
     * @return returns a bool if the desired movement is a "knight" movement
     */
 public static boolean move(int startX, int startY, int newX, int newY){
     int deltaX = Math.abs(startX - newX);
     int deltaY = Math.abs(startY - newY);
        if ((deltaX + deltaY == 3) && (deltaX > 0 && deltaY > 0)){
            System.out.println("DeltaX: " + deltaX + " DeltaY: " + deltaY);
            return true;
        }
        else {
            System.out.println("Inside Else: DeltaX: " + deltaX + " DeltaY: " + deltaY);
            return false;
        }
    }


}
