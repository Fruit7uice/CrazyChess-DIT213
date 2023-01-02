package MVC.model.Pieces;

import MVC.model.MoveLogger;
import MVC.view.MainBoard;
import MVC.model.Player;
import MVC.model.Board;
import MVC.model.SpecialMoves.Castle;
import MVC.model.SpecialMoves.PawnCapture;
import MVC.model.SpecialMoves.Promotion;
import MVC.model.Tuple;

import java.util.HashSet;
import java.util.Objects;


/**
 * The class of MoveHandler handles the logic revolving around the movement of the pieces.
 * @author Alva Johansson
 * @author Jeffrey Wolff
 * @author Johannes Höher
 */
public class MoveHandler {


    Board board;
    
    public Player playerOne;
    public Player playerTwo;
    private final MoveLogger log;

    Castle castle;
    PawnCapture pawnCapture;
    Promotion promotion;

    public MoveHandler(Board board) {
        this.log = new MoveLogger();
        this.board = board;
        this.playerOne = new Player(true, this);
        this.playerTwo = new Player(false, this);

        this.promotion = new Promotion(board);
        this.pawnCapture = new PawnCapture(this, board);
        board.isPlayerOneTurn = true;

        setPlayerKing(playerOne, board.pieceLayout);
        setPlayerKing(playerTwo, board.pieceLayout);
        this.castle = new Castle(this, playerOne, playerTwo, board);
    }


    /**
     * This method makes sure that the move of the piece obey the criteria that have been
     * declared for the given move.
     * @param newX   the desired x position
     * @param newY   the desired y position
     * @param layout the board that contains the pieces
     * @param piece  the current piece we are working on
     * @return true if the piece is allowed to make the desired move
     * @author Jeffrey Wolff
     */
    public boolean isMoveAllowed(int newX, int newY, Piece piece, Piece[][] layout) { // Allowed
        // NewX and NewY cannot be outside board.
        if (newX > MainBoard.WINDOW_WIDTH || newX < 0 || newY > MainBoard.WINDOW_HEIGHT || newY < 0) {
            return false;
        }
        //******** Checking if this move will result in check ************
        int oldX = piece.xPos;
        int oldY = piece.yPos;
        Piece[][] copy = board.getCopiedLayout();

        board.changePiecePosWithoutMoving(piece, newX, newY);
        board.placePieceAt(piece, newX, newY, copy);

        if (isChecked(copy)) {
            System.out.println("Move put your king in chess");
            board.changePiecePosWithoutMoving(piece, oldX, oldY);
            board.placePieceAt(piece, oldX, oldY, copy); // Undo Move
            return false;
        } else {
            board.changePiecePosWithoutMoving(piece, oldX, oldY);
            board.placePieceAt(piece, oldX, oldY, copy); // Undo Move
            //board.changePiecePosition(piece, oldX, oldY);
            return isMoveAllowedHelper(newX, newY, piece, layout);// Time to see if this move will result in being checked
        }
    }



    /**
     * A helper function for isMoveAllowed.
     *
     * @param newX        New X position in the matrix, which piece wants to move.
     * @param newY        New Y position in the matrix, which piece wants to move.
     * @param piece       current piece that has been moved
     * @param pieceLayout Logical matrix which is worked on
     * @return if the piece is allowed to go there and if the path is blocked or not.
     * @author Jeffrey Wolff
     */
    private boolean isMoveAllowedHelper(int newX, int newY, Piece piece, Piece[][] pieceLayout) {

        if (piece.legalMove(newX, newY)) {
            //System.out.println(piece.getType() + " at (" + piece.xPos + "," + piece.yPos + ")" +
            //      " is allowed to move to (" + newX + "," + newY + ")" );
            if (isOccupied(newX, newY, pieceLayout)) {
                // Special case for pawn. Cannot kill going forward.
                if (Objects.equals(piece.getType(), "Pawn")) {
                    return false;
                }
                if (isOccupiedByEnemy(newX, newY, piece, pieceLayout)) { //is the piece my enemy?

                    if (isPathBlocked(newX, newY, piece, pieceLayout)) {
                        return false;
                    } else { //path is blocked
                        return true;
                    }
                } else { //occupied by teammate
                    return false;
                }

            } else { // tile is not occupied
                return !isPathBlocked(newX, newY, piece, pieceLayout);
            }
        } else {
            return false;
        }
    }


    /**
     * Checks if the king depending on the turn if it is in check.
     *
     * @param layout to check.
     * @return a true if the king in question is checked, false if not.
     * @author Jeffrey Wolff
     */
    public boolean isChecked(Piece[][] layout) { // ändra denna till private eller flytta till separat klass.
        if (board.isPlayerOneTurn) {
            return isKingChecked(playerOne.king, layout);
        } else {
            return isKingChecked(playerTwo.king, layout);
        }
    }

    /**
     * Updates the pieces coordinates if is allowed to make the desired move
     *
     * @param newX  the desired x position
     * @param newY  the desired y position
     * @param piece Layout the board that contains the pieces
     * @param piece the current piece we are working on
     */
    public void movePieceInLayout(int newX, int newY, Piece piece, Piece[][] pieceLayout)   {
        board.updateLayout(pieceLayout, piece, newX, newY, true);
        //updateAllPossibleMoves(pieceLayout);
    }


    /**
     * Updates both players possible moves
     *
     * @param pieceLayout
     */
    private void updateAllPossibleMoves(Piece[][] pieceLayout) {
        playerOne.setOfAllMoves.clear();
        playerTwo.setOfAllMoves.clear();
        playerOne.setOfAllMoves = playerOne.updatePlayerPossibleMoves(pieceLayout);
        playerTwo.setOfAllMoves = playerTwo.updatePlayerPossibleMoves(pieceLayout);
        //System.out.println("UPDATED ALL POSSIBLE MOVES");
    }


    /**
     * @param newX        New X position in the matrix, which piece wants to move.
     * @param newY        New Y position in the matrix, which piece wants to move.
     * @param piece       Current piece that has been moved.
     * @param pieceLayout Logical matrix which is worked on.
     * @author Jeffrey Wolff && Johannes Höher
     */
    public void turnHandler(int newX, int newY, Piece piece, Piece[][] pieceLayout)   {
        int oldX = piece.xPos;
        int oldY = piece.yPos;

        //****** PLAYER ONE **************
        if (piece.isPlayerOne() && board.isPlayerOneTurn) {
            tryMove(piece, newX, newY, pieceLayout, playerOne);
        }
        //********** PLAYER TWO *************
        else if (!piece.isPlayerOne() && !board.isPlayerOneTurn) {
            tryMove(piece, newX, newY, pieceLayout, playerTwo);
        }

        // if a valid move was registered
        if (!(oldX == piece.xPos && oldY == piece.yPos)) { // Piece has moved
            //System.out.println("HAS MOVED");
            if (promotion.isPromotable(piece, newY)) {
                promotion.promote(piece);
            }
            // Check if king is Checked *** UPDATES VALUES
            if (board.isPlayerOneTurn) {
                playerTwo.isChecked = isChecked(pieceLayout);


            } else {
                playerOne.isChecked = isChecked(pieceLayout);

            }
            updateAllPossibleMoves(pieceLayout); // Updates list of all possible moves if a move was done
            //***** Switch turn *****
            board.isPlayerOneTurn = !board.isPlayerOneTurn; // Switching turn
        }
    }

    /**
     * Will try to move the players piece if it is allowed. Also checks for special moves.
     *
     * @param piece
     * @param newX
     * @param newY
     * @param pieceLayout
     * @param player
     */
    private void tryMove(Piece piece, int newX, int newY, Piece[][] pieceLayout, Player player)   {
        if (castle.isCastling(player, piece, newX, newY, pieceLayout)) { // is move identified as a Castle and Allowed
            castle.performCastle(player, piece, newX, newY, pieceLayout);
        } else if (pawnCapture.isPlayerCapturing(player, piece, newX, newY, pieceLayout)) { // is move identified as a pawn capture and Allowed
            pawnCapture.performPawnCapture(player, piece, newX, newY, pieceLayout);
        } else if (isMoveAllowed(newX, newY, piece, pieceLayout)) {
            movePieceInLayout(newX, newY, piece, pieceLayout);
            board.setPieceLayout(pieceLayout);
            System.out.println("Modified pieceLayout:");
            Board.printMatrix(pieceLayout);
        }
    }

    boolean isCheckMate(Player player, Piece[][] layout)   {
        boolean checkMate = true;
        for (Tuple<Integer, Integer> move : player.setOfAllMoves) {
            int newX = move.getFirst();
            int newY = move.getSecond();
            Piece dummy = new Queen(0, 0, 100, 100, "", "Queen", player.isPlayerOne(), true);
            movePieceInLayout(newX, newY, dummy, layout);
            if (!isKingChecked(player.king, layout)) {
                checkMate = false;
                break;
            }
            movePieceInLayout(0, 0, dummy, layout);
        }

        return checkMate;
    }

    /**
     * Goes through the opponents list of possible moves and returns wheaten any of those
     * moves resulting coordinates is equal to the kings Position.
     *
     * @param king
     * @param layout
     * @return
     */
    private boolean isKingChecked(Piece king, Piece[][] layout) {
        Tuple<Integer, Integer> kingPos = new Tuple<>(king.xPos, king.yPos);
        if (king.isPlayerOne()) {
            HashSet<Tuple<Integer, Integer>> possibleMoves = playerTwo.updatePlayerPossibleMoves(layout);
            return possibleMoves.contains(kingPos);
        } else {
            HashSet<Tuple<Integer, Integer>> possibleMoves = playerOne.updatePlayerPossibleMoves(layout);
            return possibleMoves.contains(kingPos);
        }

    }


    /**
     * Method that checks if the tile is occupied or not.
     * @param newX        the desired x position
     * @param newY        the desired y position
     * @param pieceLayout the board that contains the pieces
     * @return true if the position on the board is occupied
     */
    public boolean isOccupied(int newX, int newY, Piece[][] pieceLayout) {
        return (pieceLayout[newY][newX] != null);
    }

    /**
     * Method that checks if the tile is occupied by an enemy.
     * @param newX        the desired x position
     * @param newY        the desired y position
     * @param pieceLayout the board that the pieces are on??
     * @return true if the position on the board is occupied by an enemy piece
     */
    public boolean isOccupiedByEnemy(int newX, int newY, Piece piece, Piece[][] pieceLayout) {
        boolean piecePlayerOne = pieceLayout[newY][newX].isPlayerOne();
        return piecePlayerOne != piece.isPlayerOne();

    }

    /**
     * Updates the piece's set of possible moves
     *
     * @param piece       the current piece
     * @param pieceLayout the current board
     */
    public void updatePiecePossibleMoves(Piece piece, Piece[][] pieceLayout) { //[[x,y], [x,y]...
        piece.setOfMoves.clear();
        //System.out.println("Piece updating moves below:");
        for (int y = 0; y < pieceLayout.length; y++) {
            for (int x = 0; x < pieceLayout[y].length; x++) {
                if (isMoveAllowedHelper(x, y, piece, pieceLayout)) {
                    Tuple<Integer, Integer> tuple = new Tuple<>(x, y);
                    piece.setOfMoves.add(tuple);
                }
            }
        }
    }

    /**
     * Method that makes sure that no piece is able to "jump" over another piece.
     * The only piece that is allowed to do so is the Knight.
     * @param newX        the desired x position
     * @param newY        the desired y position
     * @param pieceLayout the pieceLayout that contains the pieces
     * @param piece       the current piece we are working on
     * @return true if the path is blocked by any piece as long as the piece is not a knight
     */
    public boolean isPathBlocked(int newX, int newY, Piece piece, Piece[][] pieceLayout) {
        if (!piece.getType().equals("Knight")) {
            if (piece.xPos != newX && piece.yPos == newY) { // x pos changed
                return pathBlockedHelperHorizontal(newX, piece, pieceLayout);
            } else if (piece.xPos == newX && piece.yPos != newY) { // y pos changed
                return pathBlockedHelperVertical(newY, piece, pieceLayout);
            } else if (piece.xPos != newX && piece.yPos != newY) {
                return pathBlockedHelperDiagonal(newX, newY, piece, pieceLayout);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Helper-function for isPathBlocked, checks the horizontal direction.
     * @param newX        the desired x position
     * @param piece       the current piece
     * @param pieceLayout the current pieceLayout that holds the pieces
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperHorizontal(int newX, Piece piece, Piece[][] pieceLayout) {
        int counter = 0;
        int deltaX = Math.abs(piece.xPos - newX);
        if (piece.xPos > newX) { // current x > new x (moved left)
            while (counter <= deltaX) {
                if (pieceLayout[piece.yPos][piece.xPos - counter] != null && (piece.xPos - counter) != piece.xPos
                        && pieceLayout[piece.yPos][piece.xPos - counter] != pieceLayout[piece.yPos][newX]) { // don't check where the piece is right now
                    return true;
                } else {
                    counter++;
                }
            }
        } else {
            while (counter <= deltaX) { // if current x < new x (moved right)
                if (pieceLayout[piece.yPos][piece.xPos + counter] != null && (piece.xPos + counter) != piece.xPos
                        && pieceLayout[piece.yPos][piece.xPos + counter] != pieceLayout[piece.yPos][newX]) { // don't check where the piece is right now
                    return true;
                } else {
                    counter++;
                }
            }
        }
        return false;
    }

    /**
     * Helper-function for isPathBlocked, checks the vertical direction.
     * @param newY        the desired x position
     * @param piece       the current piece
     * @param pieceLayout the current board that holds the pieces
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperVertical(int newY, Piece piece, Piece[][] pieceLayout) {
        int counter = 0;
        int deltaY = Math.abs(piece.yPos - newY);
        if (piece.yPos > newY) { // current y > new y (moved "backwards")
            while (counter < deltaY) {
                if (pieceLayout[piece.yPos - counter][piece.xPos] != null && (piece.yPos - counter) != piece.yPos) { // don't check where the piece is right now
                    return true;
                } else {
                    counter++;
                }
            }
        } else {
            while (counter < deltaY) { // if current y < new y (moved "forward")
                if (pieceLayout[piece.yPos + counter][piece.xPos] != null && (piece.xPos + counter) != piece.xPos) { // don't check where the piece is right now
                    return true;
                } else {
                    counter++;
                }
            }
        }
        return false;
    }

    /**
     * Helper-function for isPathBlocked, checks the diagonal direction.
     * @param newX        the desired x position
     * @param piece       the current piece
     * @param pieceLayout the current board that holds the pieces
     * @return true if the current coordinates are not blocked
     */
    public boolean pathBlockedHelperDiagonal(int newX, int newY, Piece piece, Piece[][] pieceLayout) {
        int counter = 0;
        int deltaX = Math.abs(piece.xPos - newX);
        int deltaY = Math.abs(piece.yPos - newY);
        if (piece.xPos > newX) { // Goes Left current x > new x doesn't matter if we compute the delta x or y since the change is equal (moved "backwards")
            if (piece.yPos > newY) { // Goes up
                while (counter < deltaX) {
                    if (pieceLayout[piece.yPos - counter][piece.xPos - counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos)) { // don't check where the piece is right now
                        return true;
                    } else {
                        counter++;
                    }
                }
            } else if (piece.yPos < newY) { // Goes Down
                while (counter < deltaX) {
                    if (pieceLayout[piece.yPos + counter][piece.xPos - counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos)) { // don't check where the piece is right now
                        return true;
                    } else {
                        counter++;
                    }
                }
            }
        } else { // Goes to the right
            if (piece.yPos > newY) { // Goes up
                while (counter < deltaX) {
                    if (pieceLayout[piece.yPos - counter][piece.xPos + counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos)) { // don't check where the piece is right now
                        return true;
                    } else {
                        counter++;
                    }
                }
            } else if (piece.yPos < newY) { // Goes down
                while (counter < deltaX) {
                    if (pieceLayout[piece.yPos + counter][piece.xPos + counter] != null && (piece.xPos - counter) != piece.xPos && (piece.yPos - counter != piece.yPos)) { // don't check where the piece is right now
                        return true;
                    } else {
                        counter++;
                    }
                }
            }
        }
        return false;
    }


    /**
     * This method sets a king to a player.
     * @param player The player in question, either player one or two.
     * @param piecesLayout The matrix of pieces
     */

    public void setPlayerKing(Player player, Piece[][] piecesLayout) {
        for (int row = 0; row < piecesLayout.length; row++) {
            for (int col = 0; col < piecesLayout[row].length; col++) {
                if (piecesLayout[row][col] != null) {
                    Piece p = piecesLayout[row][col];
                    if (p.getType().equals("King") && p.isPlayerOne() == player.isPlayerOne()) {
                        player.king = p;
                    }
                }
            }
        }
    }

}

