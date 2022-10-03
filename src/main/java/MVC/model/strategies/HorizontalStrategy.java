package MVC.model.strategies;

public class HorizontalStrategy implements IMoveStrategy {

    public boolean move (int startX, int startY, int newX, int newY ){
        if ((Math.abs(startX - newX) > 0) && (startY == newY)) {
            return true;
        }else{
            return false;
            }
        }



}
