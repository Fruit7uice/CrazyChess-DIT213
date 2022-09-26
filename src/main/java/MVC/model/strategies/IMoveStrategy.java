package MVC.model.strategies;

import MVC.model.Pieces.Piece;

/**
 * Interface for strategies of movements
 * @author Joel Leiditz Thorsson
 */
public interface IMoveStrategy {
    public void move(int startX, int startY, int endX, int endY);
}
