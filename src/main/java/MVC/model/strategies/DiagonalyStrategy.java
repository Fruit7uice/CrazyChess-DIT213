package MVC.model.strategies;

public class DiagonalyStrategy implements IMoveStrategy{
    int xPos;
    int yPos;
    @Override
    public boolean move(int startX, int startY, int endX, int endY) {
        if(Math.abs(startX - endX) == Math.abs(startY - endY)){
           return true;
        }
        return false;
    }
    public int getyPos() {
        return yPos;
    }

    public int getxPos() {
        return xPos;
    }
}
