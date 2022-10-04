package MVC.model.strategies;

public class DiagonalStrategy implements IMoveStrategy{
    public boolean move(int startX, int startY, int newX, int newY) {
        if(Math.abs(startX - newX) == Math.abs(startY - newY)){
           return true;
        }
        return false;
    }
}
