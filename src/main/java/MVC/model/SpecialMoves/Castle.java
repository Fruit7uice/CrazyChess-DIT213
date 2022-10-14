package MVC.model.SpecialMoves;


import MVC.model.Board;
import MVC.model.Pieces.King;
import MVC.model.Pieces.Piece;
import MVC.model.Pieces.Rook;
import MVC.model.Tuple;
import MVC.model.Player;
import MVC.model.Pieces.MoveHandler;

/**
 * Class that holds the logic for the Special move of castle.
 * @author Johannes Höher
 */

public class Castle {

    public Castle(){}

    Player player = new Player();




    /**
     * Method that checks so that the preconditions are satisfied before the King can castle with the rook.
     * @param king King
     * @param rook the rook in question.
     * @param pieces
     * @return
     */

    public boolean isCastleAllowed(King king, Rook rook, Piece[][] pieces){
        if(preconditionsWhiteKingLongCastle(pieces, king, rook) && !pathCheckedWhiteLongCastle()){
            return true;
        }else if(preconditionsWhiteKingShortCastle(pieces, king, rook) && !pathCheckedWhiteShortCastle()){
            return true;
        }else if(preconditionsBlackKingLongCastle(pieces, king, rook) && !pathCheckedBlackLongCastle()){
            return true;
        }else return preconditionsBlackKingShortCastle(pieces, king, rook) && !pathCheckedBlackShortCastle();
    }


    /**
     * Method that switches the positions of the White King and the left white Rook into castle-positions.
     * @param king King
     * @param rook = the rook in question.
     */


    public void whiteKingLongCastle(King king, Rook rook, Board board){ // White king left white rook.
        board.changePiecePosition(king, 2, 7);
        board.changePiecePosition(rook, 3, 7);
    }

    /**
     * Method that switches the positions of the White King and the right whit Rook into castle-positions.
     * @param king King
     * @param rook = the rook in question.
     */

    public void whiteKingShortCastle(King king, Rook rook, Board board){ // White king right white rook.
        board.changePiecePosition(king, 6, 7);
        board.changePiecePosition(rook, 5, 7);
    }

    /**
     * Method that switches the positions of the Black King and the left black Rook into castle-positions.
     * @param king King
     * @param rook = the rook in question.
     */

    public void blackKingLongCastle (King king, Rook rook, Board board){ // Black king left black rook.
        board.changePiecePosition(king, 2, 0);
        board.changePiecePosition(rook, 3, 0);
    }

    /**
     * Method that switches the positions of the black King and the right black Rook into castle-positions.
     * @param king King
     * @param rook = the rook in question.
     */

    public void blackKingShortCastle (King king, Rook rook, Board board){ // Black King Right Black Rook
        board.changePiecePosition(king, 6, 0);
        board.changePiecePosition(rook, 5, 0);
    }


    /**
     * Method that makes sure that the space between the white King and the left white Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The white King
     * @param rook The left white Rook.
     * @return true if the space is not occupied between them.
     */

    public boolean preconditionsWhiteKingLongCastle(Piece[][] pieces, King king, Rook rook) { // White King Left Rook
        if (king.isPlayerOne() && rook.isPlayerOne() && rook.xPos == 0 && rook.yPos == 7
                && notMoved(king, rook)){
            for (int row = 7; row < 8; row++) {
                for (int col = 1; col < 4; col++) {
                    if ((pieces[row][col]) == null) {
                        return true;
                    }
                }
            }
        }return false;
    }

    /**
     * Method that makes sure that the space between the white King and the left white Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The white King
     * @param rook The right white Rook.
     * @return true if the space is not occupied between them.
     */



    public boolean preconditionsWhiteKingShortCastle(Piece[][] pieces, King king, Rook rook){ // White King Right Rook
        if(king.isPlayerOne() && rook.isPlayerOne() && rook.xPos == 7 && rook.yPos == 7
                && notMoved(king, rook)){
            for (int row = 7; row < 8; row++) {
                for (int col = 5; col < 7; col++) {
                    if ((pieces[row][col]) == null) {
                        return true;
                    }
                }
            }
        }return false;
    }


    /**
     * Method that makes sure that the space between the black King and the left black Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The black King
     * @param rook The left black Rook.
     * @return true if the space is not occupied between them.
     */


    public boolean preconditionsBlackKingLongCastle(Piece[][] pieces, King king, Rook rook){ // White King Left Rook
        if(!king.isPlayerOne() && !rook.isPlayerOne() && rook.xPos == 0 && rook.yPos == 0
                && notMoved(king, rook)){
            for (int row = 0; row < 1; row++) {
                for (int col = 1; col < 4; col++) {
                    if ((pieces[row][col]) == null){
                        return true;
                    }
                }
            }
        }return false;
    }

    /**
     * Method that makes sure that the space between the black King and the right black Rook is
     * free and not occupied and that the king and rook has not moved.
     * @param pieces Matrix of the Board-layout with all the pieces.
     * @param king The black King
     * @param rook The right black Rook.
     * @return true if the space is not occupied between them.
     */


    public boolean preconditionsBlackKingShortCastle(Piece[][] pieces, King king, Rook rook){ // Black King Right Rook
        if(!king.isPlayerOne() && !rook.isPlayerOne() && rook.xPos == 0 && rook.yPos == 7
                && notMoved(king, rook)){
            for (int row = 0; row < 1; row++) {
                for (int col = 5; col < 7; col++) {
                    if ((pieces[row][col]) == null){
                        return true;
                    }
                }
            }
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

    public boolean notMoved(King king, Rook rook) {
        return !king.hasMoved && !rook.hasMoved;
    }




}