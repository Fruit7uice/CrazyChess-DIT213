package MVC.model.SpecialMoves;


import MVC.model.Board;
import MVC.model.Pieces.MoveHandler;
import MVC.model.Pieces.Piece;
import MVC.model.Tuple;
import MVC.model.Player;

import java.util.Objects;

/**
 * Class that holds the logic for the Special move of castle.
 * @author Johannes Höher
 */

public class Castle {

    public boolean playerOneHasCastled;
    public boolean playerTwoHasCastled;
    MoveHandler moveHandler;
    Player playerOne;
    Player playerTwo;

    Board board;
    public Castle(MoveHandler moveHandler, Player playerOne, Player playerTwo, Board board){
        this.playerOneHasCastled = false;
        this.playerTwoHasCastled = false;
        this.moveHandler = moveHandler;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = board;
    }

    /**
     * This method returns true if the move of the king is of white-long-castle nature, that is the new coordinates
     * of the king matches towards newX & newY.
     * @param newX 2
     * @param newY 7
     */

    public boolean isWhiteLongCastle(int newX, int newY){
        return (newX == 2 && newY == 7);
    }

    /**
     * This method returns true if the move of the king is of white-short-castle nature, that is the new coordinates
     * of the king matches towards newX & newY.
     * @param newX 6
     * @param newY 7
     */

    public boolean isWhiteShortCastle(int newX, int newY){
        return (newX == 6 && newY == 7);
    }

    /**
     * This method returns true if the move of the king is of black-long-castle nature, that is the new coordinates
     * of the king matches towards newX & newY.
     * @param newX 2
     * @param newY 0
     */
    public boolean isBlackLongCastle(int newX, int newY){
        return (newX == 2 && newY == 0);
    }

    /**
     * This method returns true if the move of the king is of black-short-castle nature, that is the new coordinates
     * of the king matches towards newX & newY.
     * @param newX 6
     * @param newY 0
     */
    public boolean isBlackShortCastle(int newX, int newY){
        return (newX == 6 && newY == 0);
    }


    /**
     * Method that checks so that the preconditions are satisfied before the playerTwo-King can castle with the rook.
     * @param king King
     * @param pieceLayout
     * @return True if the preconditions are satisfied.
     */

    public boolean isBlackCastleAllowed(Piece king, Piece[][] pieceLayout){
        if(preconditionsBlackLongCastle(pieceLayout, king) && !pathCheckedBlackLongCastle()){
            return true;
        }else return preconditionsBlackShortCastle(pieceLayout, king) && !pathCheckedBlackShortCastle();
    }


    /**
     * Method that checks so that the preconditions are satisfied before the playerOne-King can castle with the rook.
     * @param king King
     * @param pieceLayout
     * @return True if the preconditions are satisfied.
     */


    public boolean isWhiteCastleAllowed(Piece king, Piece[][] pieceLayout){
        if(preconditionsWhiteLongCastle(pieceLayout, king) && !pathCheckedWhiteLongCastle()){
            return true;
        }else if(preconditionsWhiteShortCastle(pieceLayout, king) && !pathCheckedWhiteShortCastle()){
            return true;
        }
        else return false;
    }

    /**
     * Method that switches the positions of the White King and the left white Rook into castle-positions
     * @param king
     * @param pieceLayout
     */
    public void whiteLongCastle(Piece king, Piece[][] pieceLayout)   { // White king left white rook.
        Piece rook = pieceLayout[7][0];
        board.updateLayout(pieceLayout, king, 2, 7, false);
        board.updateLayout(pieceLayout, rook, 3, 7, false);
        board.setPieceLayout(pieceLayout);
    }

    /**
     * Method that switches the positions of the White King and the right whit Rook into castle-positions.
     * @param king
     * @param pieceLayout
     */
    public void whiteShortCastle(Piece king, Piece[][] pieceLayout)   { // White king right white rook.
        Piece rook = pieceLayout[7][7];
        board.updateLayout(pieceLayout, king, 6, 7, false);
        board.updateLayout(pieceLayout, rook, 5, 7, false);
        board.setPieceLayout(pieceLayout);
    }

    /**
     * Method that switches the positions of the Black King and the left black Rook into castle-positions.
     * @param king
     * @param pieceLayout
     */
    public void blackLongCastle(Piece king, Piece[][] pieceLayout)   { // Black king left black rook.
        System.out.println("TRYING BLACK LONG CASTLE");
        Piece rook = pieceLayout[0][0];
        board.updateLayout(pieceLayout, king, 2, 0, false);
        board.updateLayout(pieceLayout, rook, 3, 0, false);
        board.setPieceLayout(pieceLayout);
    }

    /**
     * Method that switches the positions of the black King and the right black Rook into castle-positions.
     * @param king
     * @param pieceLayout
     */
    public void blackShortCastle(Piece king, Piece[][] pieceLayout)   { // Black King Right Black Rook
        Piece rook = pieceLayout[0][7];
        board.updateLayout(pieceLayout, king, 6, 0, false);
        board.updateLayout(pieceLayout, rook, 5, 0, false);
        board.setPieceLayout(pieceLayout);
    }


    /**
     * Method that makes sure that the space between the white King and the left white Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The white King
     * @return true if the space is not occupied between them.
     */

    public boolean preconditionsWhiteLongCastle(Piece[][] pieces, Piece king) { // White King Left Rook
        Piece rook = pieces[7][0];
        if (king.isPlayerOne() &&  rook != null && rook.isPlayerOne() && rook.getType().equals("Rook")
                && !hasAnyMoved(king, rook) && !moveHandler.isPathBlocked(0,7, king, pieces)){
            return true;
        }return false;
    }

    /**
     * Method that makes sure that the space between the white King and the left white Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The white King
     * @return true if the space is not occupied between them.
     */

    public boolean preconditionsWhiteShortCastle(Piece[][] pieces, Piece king){ // White King Right Rook
        Piece rook = pieces[7][7];
        if(king.isPlayerOne() && rook != null && rook.isPlayerOne() && Objects.equals(rook.getType(), "Rook")
                && !hasAnyMoved(king, rook) && !moveHandler.isPathBlocked(7, 7, king, pieces)){
            return true;
        }return false;
    }


    /**
     * Method that makes sure that the space between the black King and the left black Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The black King
     * @return true if the space is not occupied between them.
     */
    public boolean preconditionsBlackLongCastle(Piece[][] pieces, Piece king){ // White King Left Rook
        Piece rook = pieces[0][0];
        if(!king.isPlayerOne() &&  rook != null && !rook.isPlayerOne() && Objects.equals(rook.getType(), "Rook")
                && !hasAnyMoved(king, rook) && !moveHandler.isPathBlocked(0, 0, king, pieces)){
            return true;
        }return false;
    }
    /**
     * Method that makes sure that the space between the black King and the right black Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The black King
     * @return true if the space is not occupied between them.
     */
    public boolean preconditionsBlackShortCastle(Piece[][] pieces, Piece king){ // Black King Right Rook
        Piece rook = pieces[0][7];
        if(!king.isPlayerOne() && rook != null && !rook.isPlayerOne() && Objects.equals(rook.getType(), "Rook")
                && !hasAnyMoved(king, rook) && !moveHandler.isPathBlocked(7, 0, king, pieces)){
            return true;
        }return false;
    }

    /**
     * Method that checks if there are any legal moves for the opponent in their list of legal moves that
     * corresponds to the tiles that need to be unchecked for the castle to be made.
     *
     * This method in particular correspond to the White Long Castle.
     * @return True if any piece of the opposing player can move to any of these tiles.
     */
    public boolean pathCheckedWhiteLongCastle(){
        Tuple<Integer, Integer> tuple17 = new Tuple(1, 7);
        Tuple<Integer, Integer> tuple27 = new Tuple(2, 7);
        Tuple<Integer, Integer> tuple37 = new Tuple(3, 7);
        Tuple<Integer, Integer> tuple47 = new Tuple(4, 7);


        return playerTwo.setOfAllMoves.contains(tuple17) || playerTwo.setOfAllMoves.contains(tuple27) ||
                playerTwo.setOfAllMoves.contains(tuple37) || playerTwo.setOfAllMoves.contains(tuple47);
    };


    /**
     * Method that checks if there are any legal moves for the opponent in their list of legal moves that
     * corresponds to the tiles that need to be unchecked for the castle to be made.
     *
     * This method in particular correspond to the White Short Castle.
     * @return True if any piece of the opposing player can move to any of these tiles.
     */
    public boolean pathCheckedWhiteShortCastle(){
        Tuple<Integer, Integer> tuple47 = new Tuple(4, 7);
        Tuple<Integer, Integer> tuple57 = new Tuple(5, 7);
        Tuple<Integer, Integer> tuple67 = new Tuple(6, 7);

        return playerTwo.setOfAllMoves.contains(tuple47) || playerTwo.setOfAllMoves.contains(tuple57) ||
                playerTwo.setOfAllMoves.contains(tuple67);

    };

    /**
     * Method that checks if there are any legal moves for the opponent in their list of legal moves that
     * corresponds to the tiles that need to be unchecked for the castle to be made.
     *
     * This method in particular correspond to the Black Long Castle.
     * @return True if any piece of the opposing player can move to any of these tiles.
     */
    public boolean pathCheckedBlackLongCastle(){
        Tuple<Integer, Integer> tuple10 = new Tuple(1, 0);
        Tuple<Integer, Integer> tuple20 = new Tuple(2, 0);
        Tuple<Integer, Integer> tuple30 = new Tuple(3, 0);
        Tuple<Integer, Integer> tuple40 = new Tuple(4, 0);
        System.out.println(playerOne.setOfAllMoves.stream().toList());
        System.out.println("King is supposed to be checked");


        return playerOne.setOfAllMoves.contains(tuple10) || playerOne.setOfAllMoves.contains(tuple20) ||
                playerOne.setOfAllMoves.contains(tuple30) || playerOne.setOfAllMoves.contains(tuple40);
    };

    /**
     * Method that checks if there are any legal moves for the opponent in their list of legal moves that
     * corresponds to the tiles that need to be unchecked for the castle to be made.
     *
     * This method in particular correspond to the Black Short Castle.
     * @return True if any piece of the opposing player can move to any of these tiles.
     */
    public boolean pathCheckedBlackShortCastle(){
        Tuple<Integer, Integer> tuple40 = new Tuple(4, 0);
        Tuple<Integer, Integer> tuple50 = new Tuple(5, 0);
        Tuple<Integer, Integer> tuple60 = new Tuple(6, 0);

        return playerOne.setOfAllMoves.contains(tuple40) || playerOne.setOfAllMoves.contains(tuple50) ||
                playerOne.setOfAllMoves.contains(tuple60);
    };


    /**
     * Method that checks if both the king and rook has moved or not.
     * @param king King
     * @param rook = the rook in question.
     * @return true if both of them has not moved.
     */

    public boolean hasAnyMoved(Piece king, Piece rook) {
        return (king.hasMoved || rook.hasMoved);
    }


    public boolean isMoveWhiteCastle(int newX, int newY) {
        return (isWhiteLongCastle(newX, newY) || isWhiteShortCastle(newX, newY));
    }


    /**
     * Method that takes all the aspects into consideration regarding the castle. Are the pre-conditions satisfied?
     * If so, then complete the white castle-move.
     * @param piece The piece of king
     * @param pieceLayout The board of pieces
     **/



    public void performWhiteCastle(Piece piece, int newX, int newY, Piece[][] pieceLayout)   {
        if (isWhiteLongCastle(newX, newY)) {
            whiteLongCastle(piece, pieceLayout); // Switch the positions of the king and the rook.
            playerOneHasCastled = true;
        } else if (isWhiteShortCastle(newX, newY)) {
            whiteShortCastle(piece, pieceLayout); // Switch the positions of the king and the rook.
            playerOneHasCastled = true;
        }
    }


    public boolean isMoveBlackCastle(int newX, int newY) {
        return (isBlackLongCastle(newX, newY) || isBlackShortCastle(newX, newY));
    }


    /**
     * Method that takes all the aspects into consideration regarding the castle. Are the pre-conditions satisfied?
     * If so, then complete the black castle-move.
     * @param piece The piece of king
     * @param pieceLayout The board of pieces
     **/
    public void performBlackCastle(Piece piece, int newX, int newY, Piece[][] pieceLayout)   {
        if (isBlackLongCastle(newX, newY)) {
            blackLongCastle(piece, pieceLayout); // Switch the positions of the king and the rook.
            playerTwoHasCastled = true;
        } else if (isBlackShortCastle(newX, newY)) {
            blackShortCastle(piece, pieceLayout); // Switch the positions of the king and the rook.
            playerTwoHasCastled = true;
        }
    }


    public boolean isCastling(Player primaryPlayer, Piece piece, int newX, int newY, Piece[][] layout) {
        int deltaX = Math.abs(piece.xPos - newX);
        if (deltaX == 2 && Objects.equals(piece.getType(), "King")){
            if (primaryPlayer.isPlayerOne()){
                return (isMoveWhiteCastle(newX, newY) && isWhiteCastleAllowed(primaryPlayer.king, layout));
            }
            else {
                return (isMoveBlackCastle(newX, newY) && isBlackCastleAllowed(playerTwo.king, layout));
            }
        }
        return false;
    }


    /**
     * Method that is able to perform the different castles.
     * @param player
     * @param piece
     * @param newX
     * @param newY
     * @param layout
     */

    public void performCastle(Player player, Piece piece, int newX, int newY, Piece[][] layout)   {
        if (player.isPlayerOne()){
            performWhiteCastle(piece, newX, newY, layout);
        }
        else{
            performBlackCastle(piece, newX, newY, layout);
        }
    }
}