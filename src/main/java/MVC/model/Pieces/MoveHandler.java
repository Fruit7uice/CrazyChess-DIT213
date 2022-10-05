package MVC.model.Pieces;

/**
 * MoveHandler class takes care of the movement of pieces.
 * @author Alva Johansson
 */
public class MoveHandler {
    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param board the board that contains the pieces
     * @param piece the current piece we are working on
     * @return true if the piece is allowed to make the desired move
     */
    public boolean moveChecker(int newX, int newY, Piece[][] board, Piece piece){
        /* if king.IsSchecked()
        */
        if(piece.legalMove(newX, newY)){
            if (!isOccupied(newX, newY, board)){ //is the tile not occupied
                return true;
            } else { // tile is occupied
                if (isOccupiedByEnemy(newX, newY, board, piece)) { //is the piece my enemy?
                    killEnemyPiece();
                    return true;
                } else {
                    return false; //cant move because my teammate is in the way
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Updates the pieces coordinates if is allowed to make the desired move
     * @param newX the desired x position
     * @param newY the desired y position
     * @param board the board that contains the pieces
     * @param piece the current piece we are working on
     */
    public void movePiece(int newX, int newY, Piece[][] board, Piece piece){
        if(moveChecker(newX, newY, board, piece)){ // updates the position if the move is legal
            piece.xPos = newX;
            piece.yPos = newY;
        }
    }

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param board the board that contains the pieces
     * @return true if the position on the board is occupied
     */
    public boolean isOccupied(int newX, int newY, Piece[][] board){
        return (board[newX][newY] != null);
    }

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param board the board that the pieces are on??
     * @return true if the position on the board is occupied by an enemy piece
     */
    public boolean isOccupiedByEnemy(int newX, int newY, Piece[][] board, Piece piece){
        boolean piecePlayer1 = board[newX][newY].isPlayer1();
        return piecePlayer1 != piece.isPlayer1();
    }

    /**
     * removes an enemy piece from the board when it's killed
     */
    public void killEnemyPiece (){
        //TODO implement this maybe move it to another class
    }
}

