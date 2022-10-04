package MVC.model.Pieces;

public class MoveHandler {
    public boolean moveChecker(int newX, int newY, Piece[][] board){
        /* is king checked?
            if piece.legalmove()
            true:
        *
        */
        if (!isOccupied(newX, newY, board)){ //is the tile not occupied
            return true;
        } else { // tile is occupied
            if (isOccupiedByEnemy(newX, newY, board)) { //is the piece my enemy?
                killEnemyPiece();
                return true;
            } else {
                return false; //cant move because my teammate is in the way
            }
        }
        /*
        * else false
        * */
    }

    public void movePiece(int newX, int newY, Piece[][] board, Piece piece){
        if(moveChecker(newX, newY, board)){ // updates the position if the move is legal
            piece.xPos = newX;
            piece.yPos = newY;
        }
    }

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param board the board that the pieces are on??
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
    public boolean isOccupiedByEnemy(int newX, int newY, Piece[][] board){
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

