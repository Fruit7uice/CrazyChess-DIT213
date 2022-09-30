package MVC.model.strategies;

public class DiagonalStrategy {

    boolean DiagonalStrategy(int startX, int startY, int newX, int newY ) {
        if (Math.abs(startX - newX) == Math.abs(startY - newY)) {
            return true;
        }else{
            return false;
        }

    }

}
