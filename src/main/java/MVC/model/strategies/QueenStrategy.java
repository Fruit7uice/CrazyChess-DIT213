package MVC.model.strategies;

public class QueenStrategy implements IMoveStrategy {


    /**
     * Method for the strategy regarding the behavior for how the Queen may move
     * @param startX Starting x-coordinate on the board
     * @param startY Starting y-coordinate on the board
     * @param endX Ending x-coordinate on the board
     * @param endY Ending y-coordinate on the board
     */


    public void move(int startX, int startY, int endX, int endY) {
        if (Math.abs(startX - endX) == Math.abs(startY - endY)) { // Same behavior as for Bishop.
            startX = endX;
            startY = endY;
        } else if (((Math.abs(startX - endX) > 0) && (startY == endY)) || // Same behavior as for Rook.
                ((Math.abs(startY - endY) > 0) && (startX == endX))) {
            startX = endX;
            startY = endY;
        }
    }
}












