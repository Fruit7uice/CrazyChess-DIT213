package MVC.model.Pieces;


import MVC.model.Board;
import MVC.model.Tuple;


/**
 * @author Alva Johansson
 */
public class MoveHandler {

    Board board;
    public MoveHandler(Board board) {
        this.board = board;
    }

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param pieceLayout the board that contains the pieces
     * @param piece the current piece we are working on
     * @return true if the piece is allowed to make the desired move
     */
    public boolean isMoveAllowed(int newX, int newY, Piece piece, Piece[][] pieceLayout){ // Allowed
        //TODO add a check if king.IsInCheck()
        if(piece.legalMove(newX, newY)){
            System.out.println("Move Was Legal");
            if (isOccupied(newX, newY, pieceLayout)) { //is the tile not occupied

                if (isOccupiedByEnemy(newX, newY, piece, pieceLayout)) { //is the piece my enemy?

                    if(isPathBlocked(newX,newY,piece,pieceLayout)) {
                        System.out.println("Im here");
                        return false;
                    } else { //path is blocked
                        System.out.println("TRYING TO KILL");
                        killEnemyPiece();
                        return true;
                    }
                } else { //occupied by teammate
                    return false;
                }

            } else { // tile is not occupied
                System.out.println("Is not occupied");
                if (isPathBlocked(newX, newY, piece, pieceLayout)){
                    return false;
                } else {
                    return true;
                }

            }
        } else {
            System.out.println("Move Was Illegal");
            return false;
        }
    }

    /**
     * Updates the pieces coordinates if is allowed to make the desired move
     * @param newX the desired x position
     * @param newY the desired y position
     * @param pieceLayout the board that contains the pieces
     * @param piece the current piece we are working on
     */

    public void movePiece(int newX, int newY, Piece piece, Piece[][] pieceLayout){
        if(isMoveAllowed(newX, newY, piece, pieceLayout)){ // updates the position if the move is legal
            piece.xPos = newX;
            piece.yPos = newY;
            piece.listOfLegalMoves.clear();
            createListOfLegalMoves(piece, pieceLayout);
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
        return (board[newY][newX] != null);
    }

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param board the board that the pieces are on??
     * @return true if the position on the board is occupied by an enemy piece
     */
    public boolean isOccupiedByEnemy(int newX, int newY, Piece piece, Piece[][] board){
        boolean piecePlayerOne = board[newY][newX].isPlayerOne();
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
                if(isMoveAllowed(x, y, piece, board)) {
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
     * @param pieceLayout the pieceLayout that contains the pieces
     * @param piece the current piece we are working on
     * @return true if the path is blocked by any piece as long as the piece is not a knight
     */
    public boolean isPathBlocked(int newX, int newY, Piece piece, Piece[][] pieceLayout){
        if(!piece.getType().equals("Knight")) {
            if (piece.xPos != newX && piece.yPos == newY) { // x pos changed
                System.out.println("Checking Horizontal Blocked: " + pathBlockedHelperHorizontal(newX, piece, pieceLayout));
                return pathBlockedHelperHorizontal(newX, piece, pieceLayout);
            } else if (piece.xPos == newX && piece.yPos != newY) { // y pos changed
                System.out.println("Checking Vertical Blocked: " + pathBlockedHelperVertical(newY, piece, pieceLayout));
                return pathBlockedHelperVertical(newY, piece, pieceLayout);
            } else if (piece.xPos != newX && piece.yPos != newY) {
                System.out.println("Checking Diagonal Blocked: " + pathBlockedHelperDiagonal(newX, newY, piece, pieceLayout));
                return pathBlockedHelperDiagonal(newX, newY, piece, pieceLayout);
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
     * @param pieceLayout the current pieceLayout that holds the pieces
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperHorizontal(int newX, Piece piece, Piece[][] pieceLayout){
        int counter = 0;
        int deltaX = Math.abs(piece.xPos - newX);
        if (piece.xPos > newX) { // current x > new x (moved left)
            while (counter <= deltaX) {
                if (pieceLayout[piece.yPos][piece.xPos - counter] != null && (piece.xPos - counter) != piece.xPos
                        && pieceLayout[piece.yPos][piece.xPos-counter] != pieceLayout[piece.yPos][newX]) { // don't check where the piece is right now
                    System.out.println("Occupied at: " + pieceLayout[piece.yPos][piece.xPos - counter].isPlayerOne());
                    return true;
                } else {
                    counter++;
                }
            }
        } else {
            while (counter <= deltaX) { // if current x < new x (moved right)
                if (pieceLayout[piece.yPos][piece.xPos + counter] != null && (piece.xPos + counter) != piece.xPos
                        && pieceLayout[piece.yPos][piece.xPos+counter] != pieceLayout[piece.yPos][newX]) { // don't check where the piece is right now
                    System.out.println("Occupied at: " + pieceLayout[piece.yPos][piece.xPos + counter]);
                    return true;
                } else {
                    counter++;
                }
            }
        }
        return false;
    }

    /**
     * @param newY the desired x position
     * @param piece the current piece
     * @param board the current board that holds the pieces
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperVertical(int newY, Piece piece, Piece[][] board){
        int counter = 0;
        int deltaY = Math.abs(piece.yPos - newY);
        if (piece.yPos > newY) { // current y > new y (moved "backwards")
            while (counter < deltaY) {
                if (board[piece.yPos - counter][piece.xPos] != null && (piece.yPos - counter) != piece.yPos) { // don't check where the piece is right now
                    System.out.println("Occupied at: " + board[piece.yPos - counter][piece.xPos]);
                    return true;
                } else {
                    counter++;
                }
            }
        } else {
            while (counter < deltaY) { // if current y < new y (moved "forward")
                if (board[piece.yPos + counter][piece.xPos] != null && (piece.xPos + counter) != piece.xPos) { // don't check where the piece is right now
                    System.out.println("Occupied at: " + board[piece.yPos + counter][piece.xPos]);
                    return true;
                } else {
                    counter++;
                }
            }
        }
        return false;
    }

    /**
     * @param newX the desired x position
     * @param piece the current piece
     * @param board the current board that holds the pieces
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperDiagonal(int newX, int newY, Piece piece, Piece[][] board){
        int counter = 0;
        int deltaX = Math.abs(piece.xPos - newX);
        int deltaY = Math.abs(piece.yPos - newY);
        if (piece.xPos > newX) { // Goes Left current x > new x doesn't matter if we compute the delta x or y since the change is equal (moved "backwards")
            if (piece.yPos > newY){ // Goes up
                while (counter < deltaX) {
                    if (board[piece.yPos - counter][piece.xPos - counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos) ) { // don't check where the piece is right now
                        //System.out.println("Occupied at: " + board[piece.yPos - counter][piece.xPos - counter].getType());
                        return true;
                    }
                    else {
                        counter++;
                    }
                }
            } else if (piece.yPos < newY) { // Goes Down
                while (counter < deltaX) {
                    if (board[piece.yPos + counter][piece.xPos - counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos) ) { // don't check where the piece is right now
                        //System.out.println("Occupied at: " + board[piece.yPos - counter][piece.xPos - counter].getType());
                        return true;
                    }
                    else {
                        counter++;
                    }
                }
            }
        } else { // Goes to the right
            if (piece.yPos > newY){ // Goes up
                while (counter < deltaX) {
                    if (board[piece.yPos - counter][piece.xPos + counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos) ) { // don't check where the piece is right now
                        //System.out.println("Occupied at: " + board[piece.yPos - counter][piece.xPos - counter].getType());
                        return true;
                    }
                    else {
                        counter++;
                    }
                }
            } else if (piece.yPos < newY) { // Goes down
                while (counter < deltaX) {
                    if (board[piece.yPos + counter][piece.xPos + counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos) ) { // don't check where the piece is right now
                        //System.out.println("Occupied at: " + board[piece.yPos - counter][piece.xPos - counter].getType());
                        return true;
                    }
                    else {
                        counter++;
                    }
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

