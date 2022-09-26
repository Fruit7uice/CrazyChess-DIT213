package MVC.model.strategies;

public class MoveBishop implements IMoveStrategy{
    @Override
    public void move(int startX, int startY, int endX, int endY) {
        if(Math.abs(startX - endX) == Math.abs(startY - endY)){
            startX = endX;
            startY = endY;
        }else {
            //do nothing
        }
    }
}
