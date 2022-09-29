package MVC.model.strategies;

public class KingStrategy implements IMoveStrategy {

    // Question??
    // Should the behavior for castling also be here considering
    // it's a part of the behavior concerning move for the King??

    /**
     * Method for the strategy regarding the behavior for how the King may move
     * @param startX Starting x-coordinate on the board
     * @param startY Starting y-coordinate on the board
     * @param endX Ending x-coordinate on the board
     * @param endY Ending y-coordinate on the board
     */

    @Override
    public void move(int startX, int startY, int endX, int endY) {
        if (Math.abs(startX - endX) == 1 && (startY == endY)) {
            startX = endX;
        }
        else if(Math.abs(startY - endY) == 1 && (startX == endX)){
            startY = endY;
            }
        else if((Math.abs(startX - endX) == 1) && (Math.abs(startY - endY) == 1)) {
            startX = endX;
            startY = endY;
        }
    }
}

