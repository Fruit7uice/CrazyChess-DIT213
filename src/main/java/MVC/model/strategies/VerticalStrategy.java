package MVC.model.strategies;

public class VerticalStrategy implements IMoveStrategy {

    public boolean move(int startX, int startY, int newX, int newY) {
        if ((Math.abs(startY - newY) > 0) && (startX == newX)) {
            return true;
        } else {
            return false;
        }
    }


}
