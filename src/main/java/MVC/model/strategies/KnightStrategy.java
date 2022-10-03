package MVC.model.strategies;

public class KnightStrategy {


    public boolean move(int startX, int startY, int newX, int newY){
        if(Math.abs(startX - newX) + Math.abs(startY - newY) == 3  &&
                Math.abs(startX - newX) > 0 && Math.abs(startY - newY) > 0){
            return true;
        }else{
            return false;
        }
    }


}
