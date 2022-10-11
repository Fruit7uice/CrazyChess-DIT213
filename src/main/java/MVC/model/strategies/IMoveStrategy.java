package MVC.model.strategies;

/**
 * Interface for strategies of movements
 * @author Joel Leiditz Thorsson
 */
public interface IMoveStrategy {
     static boolean move(int startX, int startY, int newX, int newY){
          return false;
     }
}
