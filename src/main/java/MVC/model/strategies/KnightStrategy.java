package MVC.model.strategies;

public class KnightStrategy {
    public static void main(String[] args) {
        KnightStrategy(0,0, 2, 2);

    }

    private static int startX;
    private static int startY;
    private static int newX;
    private static int newY;

    //private static int deltaKnightX = Math.abs(startX - newX);
    //private static int deltaKnightY = Math.abs(startY - newY);

    public static boolean KnightStrategy(int startX, int startY, int newX, int newY){
        if(Math.abs(startX - newX) + Math.abs(startY - newY) == 3  &&
                Math.abs(startX - newX) > 0 && Math.abs(startY - newY) > 0){
            System.out.println("Valid move");
            return true;
        }else{System.out.println("Unvalid move");
            return false;
        }

    }


}
