package MVC.model.Pieces;


import MVC.model.Board;
import MVC.model.Tuple;


/**
 * @author Alva Johansson
 */
public class MoveHandler {

    Board board;
    public MoveHandler(Board board) {
        this.board = this.board;
    }

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param pieceLayout the board that contains the pieces
     * @param piece the current piece we are working on
     * @return true if the piece is allowed to make the desired move
     */
    public boolean moveChecker(int newX, int newY, Piece piece, Piece[][] pieceLayout){
        //TODO add a check if king.IsInCheck()
        if(piece.legalMove(newX, newY)){
            if (!isOccupied(newX, newY, pieceLayout)) { //is the tile not occupied
                return !isPathBlocked(newX, newY, piece, pieceLayout); // true if path is not blocked
            } else { // tile is occupied
                if (isOccupiedByEnemy(newX, newY, piece, pieceLayout)) { //is the piece my enemy?
                    if(!isPathBlocked(newX,newY,piece,pieceLayout)) { // path is not blocked
                        killEnemyPiece();
                        return true;
                    }else { //path is blocked
                        return false;
                    }
                } else { //occupied by teammate
                    return false;
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
     * @param piece Layout the board that contains the pieces
     * @param piece the current piece we are working on
     */

    public void movePiece(int newX, int newY, Piece piece, Piece[][] board){
        if(moveChecker(newX, newY, piece, board)){ // updates the position if the move is legal
            piece.xPos = newX;
            piece.yPos = newY;
            piece.listOfLegalMoves.clear();
            createListOfLegalMoves(piece, board);
            piece.hasMoved = true; // The first time the piece moves, the boolean is going to get
        }                          // switched to true.
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
    public boolean isOccupiedByEnemy(int newX, int newY, Piece piece, Piece[][] board){
        boolean piecePlayerOne = board[newX][newY].isPlayerOne();
        return piecePlayerOne != piece.isPlayerOne();

    }

    /**
     * updates the pieces list if legal moves
     * @param piece the current piece
     * @param board the current board
     */
    public void createListOfLegalMoves(Piece piece, Piece[][] board){ //[[x,y], [x,y]...
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if(moveChecker(x, y, piece, board)) {
                    Tuple<Integer, Integer> tuple = new Tuple(x, y);
                    piece.listOfLegalMoves.add(tuple);
                } else{
                    //don't add to matrix
                }
            }
        }
    }

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param board the board that contains the pieces
     * @param piece the current piece we are working on
     * @return true if the path is blocked by any piece as long as the piece is not a knight
     */
    public boolean isPathBlocked(int newX, int newY, Piece piece, Piece[][] board){
        if(!piece.getType().equals("Knight")) {
            if (piece.xPos != newX && piece.yPos == newY) { // x pos changed
                return pathBlockedHelperHorizontal(newX, piece, board, 0);
            } else if (piece.xPos == newX && piece.yPos != newY) { // y pos changed
                return pathBlockedHelperVertical(newY, piece, board, 0);
            } else if (piece.xPos != newX && piece.yPos != newY) {
                return pathBlockedHelperDiagonal(newX, newY, piece, board, 0);
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    /**
     * @param newX the desired x position
     * @param piece the current piece
     * @param board the current board that holds the pieces
     * @param counter to keep track of how much we should increment
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperHorizontal(int newX, Piece piece, Piece[][] board, int counter){
        int deltaX = Math.abs(piece.xPos - newX);
        if (piece.xPos > newX) { // current x > new x (moved left)
            while (counter < deltaX) {
                if (board[piece.xPos - counter][piece.yPos] != null && (piece.xPos - counter) != piece.xPos) { // don't check where the piece is right now
                    return true;
                } else {
                    counter++;
                    pathBlockedHelperHorizontal(newX, piece, board, counter);
                }
            }
        } else {
            while (counter < deltaX) { // if current x < new x (moved right)
                if (board[piece.xPos + counter][piece.yPos] != null && (piece.xPos + counter) != piece.xPos) { // don't check where the piece is right now
                    return true;
                } else {
                    counter++;
                    pathBlockedHelperHorizontal(newX, piece, board, counter);
                }
            }
        }
        return false;
    }

    /**
     * @param newY the desired x position
     * @param piece the current piece
     * @param board the current board that holds the pieces
     * @param counter to keep track of how much we should increment
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperVertical(int newY, Piece piece, Piece[][] board, int counter){
        int deltaY = Math.abs(piece.yPos - newY);
        if (piece.yPos > newY) { // current y > new y (moved "backwards")
            while (counter < deltaY) {
                if (board[piece.xPos][piece.yPos - counter] != null && (piece.yPos - counter) != piece.yPos) { // don't check where the piece is right now
                    return true;
                } else {
                    counter++;
                    pathBlockedHelperVertical(newY, piece, board, counter);
                }
            }
        } else {
            while (counter < deltaY) { // if current y < new y (moved "forward")
                if (board[piece.xPos][piece.yPos + counter] != null && (piece.xPos + counter) != piece.xPos) { // don't check where the piece is right now
                    return true;
                } else {
                    counter++;
                    pathBlockedHelperVertical(newY, piece, board, counter);
                }
            }
        }
        return false;
    }

    /**
     * @param newX the desired x position
     * @param piece the current piece
     * @param board the current board that holds the pieces
     * @param counter to keep track of how much we should increment
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperDiagonal(int newX, int newY, Piece piece, Piece[][] board, int counter){
        int deltaX = Math.abs(piece.xPos - newX);
        if (piece.xPos > newX) { // current x > new x doesn't matter if we compute the delta x or y since the change is equal (moved "backwards")
            while (counter < deltaX) {
                if (board[piece.xPos - counter][piece.yPos - counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos) ) { // don't check where the piece is right now
                    return true;
                } else { // calls recursively
                    counter++;
                    pathBlockedHelperDiagonal(newX, newY, piece,board, counter);
                }
            }
        } else {
            while (counter < deltaX) { // if current x < new x (moved "forward")
                if (board[piece.xPos + counter][piece.yPos + counter] != null && (piece.xPos + counter) != piece.xPos && (piece.yPos + counter != piece.yPos) ) { // don't check where the piece is right now
                    return true;
                } else {
                    counter++;
                    pathBlockedHelperDiagonal(newX, newY, piece,board, counter);
                }
            }
        }
        return false;
    }

    /**
     * removes an enemy piece from the board when it's killed
     */
    public void killEnemyPiece (){
        //TODO implement this maybe move it to another class
    }
}

