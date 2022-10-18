package MVC.model.Pieces;
import MVC.model.Player;
import MVC.model.Board;
import MVC.model.SpecialMoves.Castle;
import MVC.model.SpecialMoves.PawnCapture;
import MVC.model.Tuple;

import java.util.Objects;


/**
 * @author Alva Johansson
 */
public class MoveHandler {
    Board board;
    public MoveHandler(Board board) {
        this.board = board;
    }
    Castle castle = new Castle(this);
    PawnCapture pawnCapture = new PawnCapture(this);

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param pieceLayout the board that contains the pieces
     * @param piece the current piece we are working on
     * @return true if the piece is allowed to make the desired move
     * @author Jeffrey Wolff
     */
    public boolean isMoveAllowed(int newX, int newY, Piece piece, Piece[][] pieceLayout){ // Allowed
        //TODO add a check if king.IsInCheck() and if king is checkmate
        return isMoveAllowedHelper(newX, newY, piece, pieceLayout);
    }

    /**
     * A helper function for isMoveAllowed.
     * @param newX New X position in the matrix, which piece wants to move.
     * @param newY New Y position in the matrix, which piece wants to move.
     * @param piece current piece that has been moved
     * @param pieceLayout Logical matrix which is worked on
     * @return if the piece is allowed to go there and if the path is blocked or not.
     * @author Jeffrey Wolff
     */
    private boolean isMoveAllowedHelper(int newX, int newY, Piece piece, Piece[][] pieceLayout){

        if(piece.legalMove(newX, newY)){
           // System.out.println("Move Was Legal");
            if (isOccupied(newX, newY, pieceLayout)) { //is the tile not occupied

                if (isOccupiedByEnemy(newX, newY, piece, pieceLayout)) { //is the piece my enemy?

                    if(isPathBlocked(newX,newY,piece,pieceLayout)) {
                   //     System.out.println("Im here");
                        return false;
                    } else { //path is blocked
                    //    System.out.println("TRYING TO KILL");
                        killEnemyPiece();
                        return true;
                    }
                } else { //occupied by teammate
                    return false;
                }

            } else { // tile is not occupied
               // System.out.println("Is not occupied");
                if (isPathBlocked(newX, newY, piece, pieceLayout)){
                    return false;
                } else {
                    return true;
                }

            }
        } else {
          //  System.out.println("Move Was Illegal");
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
     * Does vital checks before calling the basic isMoveAllowed. If any of the checks is true, it is handled
     * elsewhere and  won't call the isMoveAllowed.
     * @param newX New X position in the matrix, which piece wants to move.
     * @param newY New Y position in the matrix, which piece wants to move.
     * @param piece current piece that has been moved.
     * @param pieceLayout Logical matrix which is worked on.
     * @author Jeffrey Wolff && Johannes Höher
     */
    public void tryAndCheckMove(int newX, int newY, Piece piece, Piece[][] pieceLayout){
        int deltaX = Math.abs(piece.xPos - newX);
        if (piece.isPlayerOne() && deltaX == 2 && castle.isWhiteLongCastle(newX, newY) &&
                Objects.equals(piece.getType(), "King")
                && !hasPlayerOneCastled()){
            castle.performCastle(piece, newX, newY, pieceLayout, board); // Case for the white long castle
        }

        else if (piece.isPlayerOne() && deltaX == 2 && castle.isWhiteShortCastle(newX, newY) &&
                Objects.equals(piece.getType(), "King")
                && !hasPlayerOneCastled()){
            castle.performCastle(piece, newX, newY, pieceLayout, board); // Case for the white short castle
        }

        else if (!piece.isPlayerOne() && deltaX == 2 && castle.isBlackLongCastle(newX, newY) &&
                Objects.equals(piece.getType(), "King")
                && !hasPlayerTwoCastled()){
            castle.performCastle(piece, newX, newY, pieceLayout, board); // Case for the black long castle
        }

        else if (!piece.isPlayerOne() && deltaX == 2 && castle.isBlackShortCastle(newX, newY) &&
                Objects.equals(piece.getType(), "King")
                && !hasPlayerTwoCastled()){
            castle.performCastle(piece, newX, newY, pieceLayout, board); // Case for the black short castle
        }

        else if(Objects.equals(piece.getType(), "Pawn") && piece.isPlayerOne() &&
                pawnCapture.isPlayerOnePawnCapture(pieceLayout, piece, newX, newY) ){
            pawnCapture.playerOnePawnCaptures(pieceLayout, piece, newX, newY, board); // White pawn captures an enemy piece
        }

        else if(Objects.equals(piece.getType(), "Pawn") && !piece.isPlayerOne() &&
                pawnCapture.isPlayerTwoPawnCapture(pieceLayout, piece, newX, newY) ){
            pawnCapture.playerTwoPawnCaptures(pieceLayout, piece, newX, newY, board); // Black pawn captures an enemy piece
        }

        else if (isMoveAllowed(newX, newY, piece, pieceLayout)){
            board.changePiecePosition(piece, newX, newY);
        }
    }


    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param pieceLayout the board that contains the pieces
     * @return true if the position on the board is occupied
     */
    public boolean isOccupied(int newX, int newY, Piece[][] pieceLayout){
        return (pieceLayout[newY][newX] != null);
    }

    /**
     * @param newX the desired x position
     * @param newY the desired y position
     * @param pieceLayout the board that the pieces are on??
     * @return true if the position on the board is occupied by an enemy piece
     */
    public boolean isOccupiedByEnemy(int newX, int newY, Piece piece, Piece[][] pieceLayout){
        boolean piecePlayerOne = pieceLayout[newY][newX].isPlayerOne();
        return piecePlayerOne != piece.isPlayerOne();

    }

    /**
     * updates the pieces list if legal moves
     * @param piece the current piece
     * @param pieceLayout the current board
     */
    public void createListOfLegalMoves(Piece piece, Piece[][] pieceLayout){ //[[x,y], [x,y]...
        for (int y = 0; y < pieceLayout.length; y++) {
            for (int x = 0; x < pieceLayout[y].length; x++) {
                if(isMoveAllowed(x, y, piece, pieceLayout)) {
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
                //System.out.println("Checking Horizontal Blocked: " + pathBlockedHelperHorizontal(newX, piece, pieceLayout));
                return pathBlockedHelperHorizontal(newX, piece, pieceLayout);
            } else if (piece.xPos == newX && piece.yPos != newY) { // y pos changed
                //System.out.println("Checking Vertical Blocked: " + pathBlockedHelperVertical(newY, piece, pieceLayout));
                return pathBlockedHelperVertical(newY, piece, pieceLayout);
            } else if (piece.xPos != newX && piece.yPos != newY) {
                //System.out.println("Checking Diagonal Blocked: " + pathBlockedHelperDiagonal(newX, newY, piece, pieceLayout));
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
                    //System.out.println("Occupied at: " + pieceLayout[piece.yPos][piece.xPos - counter].isPlayerOne());
                    return true;
                } else {
                    counter++;
                }
            }
        } else {
            while (counter <= deltaX) { // if current x < new x (moved right)
                if (pieceLayout[piece.yPos][piece.xPos + counter] != null && (piece.xPos + counter) != piece.xPos
                        && pieceLayout[piece.yPos][piece.xPos+counter] != pieceLayout[piece.yPos][newX]) { // don't check where the piece is right now
                    //System.out.println("Occupied at: " + pieceLayout[piece.yPos][piece.xPos + counter]);
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
     * @param pieceLayout the current board that holds the pieces
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperVertical(int newY, Piece piece, Piece[][] pieceLayout){
        int counter = 0;
        int deltaY = Math.abs(piece.yPos - newY);
        if (piece.yPos > newY) { // current y > new y (moved "backwards")
            while (counter < deltaY) {
                if (pieceLayout[piece.yPos - counter][piece.xPos] != null && (piece.yPos - counter) != piece.yPos) { // don't check where the piece is right now
                    //System.out.println("Occupied at: " + pieceLayout[piece.yPos - counter][piece.xPos]);
                    return true;
                } else {
                    counter++;
                }
            }
        } else {
            while (counter < deltaY) { // if current y < new y (moved "forward")
                if (pieceLayout[piece.yPos + counter][piece.xPos] != null && (piece.xPos + counter) != piece.xPos) { // don't check where the piece is right now
                    //System.out.println("Occupied at: " + pieceLayout[piece.yPos + counter][piece.xPos]);
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
     * @param pieceLayout the current board that holds the pieces
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperDiagonal(int newX, int newY, Piece piece, Piece[][] pieceLayout){
        int counter = 0;
        int deltaX = Math.abs(piece.xPos - newX);
        int deltaY = Math.abs(piece.yPos - newY);
        if (piece.xPos > newX) { // Goes Left current x > new x doesn't matter if we compute the delta x or y since the change is equal (moved "backwards")
            if (piece.yPos > newY){ // Goes up
                while (counter < deltaX) {
                    if (pieceLayout[piece.yPos - counter][piece.xPos - counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos) ) { // don't check where the piece is right now
                        //System.out.println("Occupied at: " + board[piece.yPos - counter][piece.xPos - counter].getType());
                        return true;
                    }
                    else {
                        counter++;
                    }
                }
            } else if (piece.yPos < newY) { // Goes Down
                while (counter < deltaX) {
                    if (pieceLayout[piece.yPos + counter][piece.xPos - counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos) ) { // don't check where the piece is right now
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
                    if (pieceLayout[piece.yPos - counter][piece.xPos + counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos) ) { // don't check where the piece is right now
                        //System.out.println("Occupied at: " + board[piece.yPos - counter][piece.xPos - counter].getType());
                        return true;
                    }
                    else {
                        counter++;
                    }
                }
            } else if (piece.yPos < newY) { // Goes down
                while (counter < deltaX) {
                    if (pieceLayout[piece.yPos + counter][piece.xPos + counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos) ) { // don't check where the piece is right now
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
     * @param player the player whose turn it is right now
     * @param king the players king that we are checking if checked on
     * @param pieceLayout current placement of the pieces
     * @return true if the king is checked
     */
    public boolean isKingCheck(Player player, Piece king, Piece[][] pieceLayout){
        player.calcListOfLegalMovesPlayer(pieceLayout, this); // creates the lists of legal moves
        Tuple<Integer, Integer> tuple = new Tuple<>(king.xPos, king.yPos);
        if(king.isPlayerOne()){
            return player.playerTwoListOfLegalMoves.contains(tuple);
        } else {
            return player.playerOneListOfLegalMoves.contains(tuple);
        }
    }

    /**
     *
     * @param player the current player
     * @param king the king we check
     * @param pieceLayout the current placement of pieces
     * @return true if king is checkmate
     */
    public boolean isKingCheckMate(Player player, Piece king, Piece[][] pieceLayout){
        player.calcListOfLegalMovesPlayer(pieceLayout, this);
        if(king.isPlayerOne()){
           // System.out.println(player.playerTwoListOfLegalMoves);
            //System.out.println("hgkgjfgfh"+ player.playerTwoListOfLegalMoves);
            return player.playerTwoListOfLegalMoves.contains(checkPosAroundKing(player, king));
            //[Pair[1,0]!!!, Pair[0,1]!!, Pair[1,1], Pair[1,0]!!, Pair[0,1], Pair[1,1]]
        } else {
           // System.out.println(player.playerOneListOfLegalMoves);
            return player.playerOneListOfLegalMoves.contains(checkPosAroundKing(player, king));
        }
    }

    /**
     * checks if the "enemy" players list of legal moves contains any of the positions around the king
     * @param player the current player
     * @param king the current player
     * @return true if positions around the king is in the enemy players list of legal moves
     */
    private boolean checkPosAroundKing(Player player, Piece king){
        if(king.isPlayerOne()){
            if(king.xPos == 0 && king.yPos == 0){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos + 1)); // right up
            } else if(king.xPos == 7 && king.yPos == 0){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos + 1)); // left up
            } else if (king.xPos == 0 && king.yPos == 7) {
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos - 1)); // right down
            } else if(king.xPos == 7 && king.yPos == 7){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos - 1)); // left down
            } else if(king.xPos == 0){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos + 1)) // right upp
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos - 1)); // right down
            } else if(king.xPos == 7){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos + 1)) // left upp
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos - 1)); // left down
            } else if (king.yPos == 0) {
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos + 1)) // right up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos + 1)); // left up
            } else if(king.yPos == 7){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos - 1)) // right down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos - 1)); // left down
            } else{
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos + 1)) // right upp
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos + 1)) // left upp
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos - 1)) // right down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos - 1)); // left down
            }
        } else{
            if(king.xPos == 0 && king.yPos == 0){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos + 1)); // right up
            } else if(king.xPos == 7 && king.yPos == 0){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos + 1)); // left up
            } else if (king.xPos == 0 && king.yPos == 7) {
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos - 1)); // right down
            } else if(king.xPos == 7 && king.yPos == 7){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos - 1)); // left down
            } else if(king.xPos == 0){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos + 1)) // right upp
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos - 1)); // right down
            } else if(king.xPos == 7){
                return player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos + 1)) // left upp
                        && player.playerTwoListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos - 1)); // left down
            } else if (king.yPos == 0) {
                return player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos + 1)) // right up
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos + 1)); // left up
            } else if(king.yPos == 7){
                return player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos - 1)) // right down
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos - 1)); // left down
            } else{
                return player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos)) // king pos
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos)) // right
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos)) // left
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos + 1)) // up
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos, king.yPos - 1)) // down
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos + 1)) // right upp
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos + 1)) // left upp
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos + 1, king.yPos - 1)) // right down
                        && player.playerOneListOfLegalMoves.contains(new Tuple<>(king.xPos - 1, king.yPos - 1)); // left down
            }
        }
    }


    /**
     * removes an enemy piece from the board when it's killed
     */
    public void killEnemyPiece (){
        //TODO implement this maybe move it to another class
    }

    public boolean hasPlayerOneCastled() {
        return castle.playerOneHasCastled;
    }

    public boolean hasPlayerTwoCastled() {
        return castle.playerTwoHasCastled;
    }

}

