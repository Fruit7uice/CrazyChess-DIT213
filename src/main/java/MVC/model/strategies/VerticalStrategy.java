package MVC.model.strategies;

public class VerticalStrategy {

    public boolean VerticalStrategy(int startX, int startY, int newX, int newY) {
        if ((Math.abs(startY - newY) > 0) && (startX == newX)) {
            return true;
        } else {
            return false;
        }
    }


}
