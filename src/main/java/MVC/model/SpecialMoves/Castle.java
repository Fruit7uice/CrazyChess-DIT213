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

    public Castle(MoveHandler moveHandler){
        this.playerOneHasCastled = false;
        this.playerTwoHasCastled = false;
        this.moveHandler = moveHandler;
    }

    Player player = new Player(true);







    /**
     * Method that takes all the aspects into consideration regarding the castle. Are the pre-conditions satisfied?
     * If so, then complete the castle-move.
     * @param piece The piece of king
     * @param pieces The board of pieces
     * @param board THe board
     **/


    public void performCastle(Piece piece, int newX, int newY, Piece[][] pieces, Board board) {
        if (isCastleAllowed(piece, pieces)) {
            if (isWhiteLongCastle(newX, newY)) {
                whiteLongCastle(piece, pieces, board); // Switch the positions of the king and the rook.
                playerOneHasCastled = true;
            } else if (isWhiteShortCastle(newX, newY)) {
                whiteShortCastle(piece, pieces, board); // Switch the positions of the king and the rook.
                playerOneHasCastled = true;
            } else if (isBlackLongCastle(newX, newY)) {
                blackLongCastle(piece, pieces, board); // Switch the positions of the king and the rook.
                playerTwoHasCastled = true;
            } else if (isBlackShortCastle(newX, newY)) {
                blackShortCastle(piece, pieces, board); // Switch the positions of the king and the rook.
                playerTwoHasCastled = true;
            }
        }
    }

    public boolean isWhiteLongCastle(int newX, int newY){
        return (newX == 2 && newY == 7);
    }
    public boolean isWhiteShortCastle(int newX, int newY){
        return (newX == 6 && newY == 7);
    }
    public boolean isBlackLongCastle(int newX, int newY){
        return (newX == 2 && newY == 0);
    }
    public boolean isBlackShortCastle(int newX, int newY){
        return (newX == 6 && newY == 0);
    }


    /**
     * Method that checks so that the preconditions are satisfied before the King can castle with the rook.
     * @param king King
     * @param pieces
     * @return
     */

    public boolean isCastleAllowed(Piece king, Piece[][] pieces){
        if(preconditionsWhiteLongCastle(pieces, king) && !pathCheckedWhiteLongCastle()){
            return true;
        }else if(preconditionsWhiteShortCastle(pieces, king) && !pathCheckedWhiteShortCastle()){
            return true;
        }else if(preconditionsBlackLongCastle(pieces, king) && !pathCheckedBlackLongCastle()){
            return true;
        }else return preconditionsBlackShortCastle(pieces, king) && !pathCheckedBlackShortCastle();
    }


    /**
     * Method that switches the positions of the White King and the left white Rook into castle-positions
     * @param king
     * @param pieces
     * @param board
     */
    public void whiteLongCastle(Piece king, Piece[][] pieces, Board board){ // White king left white rook.
        Piece rook = pieces[7][0];
        board.changePiecePosition(king, 2, 7);
        board.changePiecePosition(rook, 3, 7);
    }

    /**
     * Method that switches the positions of the White King and the right whit Rook into castle-positions.
     * @param king
     * @param pieces
     * @param board
     */
    public void whiteShortCastle(Piece king, Piece[][] pieces, Board board){ // White king right white rook.
        Piece rook = pieces[7][7];
        board.changePiecePosition(king, 6, 7);
        board.changePiecePosition(rook, 5, 7);
    }

    /**
     * Method that switches the positions of the Black King and the left black Rook into castle-positions.
     * @param king
     * @param pieces
     * @param board
     */
    public void blackLongCastle(Piece king, Piece[][] pieces, Board board){ // Black king left black rook.
        Piece rook = pieces[0][0];
        board.changePiecePosition(king, 2, 0);
        board.changePiecePosition(rook, 3, 0);
    }

    /**
     * Method that switches the positions of the black King and the right black Rook into castle-positions.
     * @param king
     * @param pieces
     * @param board
     */
    public void blackShortCastle(Piece king, Piece[][] pieces, Board board){ // Black King Right Black Rook
        Piece rook = pieces[0][7];
        board.changePiecePosition(king, 6, 0);
        board.changePiecePosition(rook, 5, 0);
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

        for (int i = 0; i < player.playerTwoListOfLegalMoves.size()-1; i++) {
            if (player.playerTwoListOfLegalMoves.get(i) == tuple17 ||
                    player.playerTwoListOfLegalMoves.get(i) == tuple27 ||
                    player.playerTwoListOfLegalMoves.get(i) == tuple37 ||
                    player.playerTwoListOfLegalMoves.get(i) == tuple47){
                return true;
            }
        }
        return false;
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

        for (int i = 0; i < player.playerTwoListOfLegalMoves.size()-1; i++) {
            if (player.playerTwoListOfLegalMoves.get(i) == tuple47 ||
                    player.playerTwoListOfLegalMoves.get(i) == tuple57 ||
                    player.playerTwoListOfLegalMoves.get(i) == tuple67){
                return true;
            }
        }
        return false;
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

        for (int i = 0; i < player.playerOneListOfLegalMoves.size()-1; i++) {
            if (player.playerOneListOfLegalMoves.get(i) == tuple10 ||
                    player.playerOneListOfLegalMoves.get(i) == tuple20 ||
                    player.playerOneListOfLegalMoves.get(i) == tuple30 ||
                    player.playerOneListOfLegalMoves.get(i) == tuple40){
                return true;
            }
        }
        return false;
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

        for (int i = 0; i < player.playerOneListOfLegalMoves.size()-1; i++) {
            if(player.playerOneListOfLegalMoves.get(i) == tuple40 ||
                    player.playerOneListOfLegalMoves.get(i) == tuple50 ||
                    player.playerOneListOfLegalMoves.get(i) == tuple60){
                return true;
            }
        }
        return false;
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






}