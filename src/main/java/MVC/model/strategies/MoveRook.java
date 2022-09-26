package MVC.model.strategies;

public class MoveRook implements IMoveStrategy{
    @Override
    public void move(int startX, int startY, int endX, int endY) {
        if(Math.abs(startX - endX) > 0 || Math.abs(startY - endY) > 0){
            startX = endX;
            startY = endY;
        }else {
            //do nothing
        }
    }
}
