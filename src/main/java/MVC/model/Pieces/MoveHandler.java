package MVC.model.Pieces;

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
 * @author Alva Johansson
 * @author Jeffrey Wolff
 * @author Johannes Höher
 */
public class MoveHandler {

    boolean isPlayerOneTurn;
    Board board;

    Player playerOne;
    Player playerTwo;

    Castle castle;
    PawnCapture pawnCapture;
    Promotion promotion;

    public MoveHandler(Board board) {
        this.board = board;
        this.playerOne = new Player(true, this);
        this.playerTwo = new Player(false, this);

        this.promotion = new Promotion(board);
        this.pawnCapture = new PawnCapture(this);
        isPlayerOneTurn = true;

        setPlayerKing(playerOne, board.pieceLayout);
        setPlayerKing(playerTwo, board.pieceLayout);
        this.castle = new Castle(this, playerOne, playerTwo, board);
    }


    /**
     * @param newX        the desired x position
     * @param newY        the desired y position
     * @param pieceLayout the board that contains the pieces
     * @param piece       the current piece we are working on
     * @return true if the piece is allowed to make the desired move
     * @author Jeffrey Wolff
     */
    public boolean isMoveAllowed(int newX, int newY, Piece piece, Piece[][] pieceLayout) { // Allowed
        // NewX and NewY cannot be outside board.
        if (newX > MainBoard.WINDOW_WIDTH || newX < 0 || newY > MainBoard.WINDOW_HEIGHT || newY < 0)
            return false;

        //******** Checking if this move will result in check ************
        //System.out.println("Check CHECKER:");
        int oldX = piece.xPos;
        int oldY = piece.yPos;
        //temporary move
        movePiece(newX, newY, piece, pieceLayout);

        if (isChecked(pieceLayout)) {
            System.out.println("Move put your king in chess");
            movePiece(oldX, oldY, piece, pieceLayout); // Undo Move
            return false;
        } else {
            //System.out.println("CheckChecker end");
            movePiece(oldX, oldY, piece, pieceLayout); // Undo Move
            return isMoveAllowedHelper(newX, newY, piece, pieceLayout);// Time to see if this move will result in being checked
        }
        //if (willPutKingInCheck(pieceLayout))
        // If piece can move here without having to check if it is checked.
    }


    private boolean isChecked(Piece[][] layout) {
        if (isPlayerOneTurn) {
            return isKingChecked(playerOne.king, layout);
        } else {
            return isKingChecked(playerTwo.king, layout);
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
            if (isOccupied(newX, newY, pieceLayout)) {//is the tile not occupied
                // Special case for pawn. Cannot kill going forward.
                if (Objects.equals(piece.getType(), "Pawn")) {
                    //System.out.println("Pawn cannot go forward because is occupied");
                    return false;
                }
                if (isOccupiedByEnemy(newX, newY, piece, pieceLayout)) { //is the piece my enemy?

                    if (isPathBlocked(newX, newY, piece, pieceLayout)) {
                        return false;
                    } else { //path is blocked
                        killEnemyPiece();
                        return true;
                    }
                } else { //occupied by teammate
                    return false;
                }

            } else { // tile is not occupied
                if (isPathBlocked(newX, newY, piece, pieceLayout)) {
                    return false;
                } else {
                    return true;
                }

            }
        } else {
            return false;
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
    public void movePiece(int newX, int newY, Piece piece, Piece[][] pieceLayout) {
        board.updateLayout(pieceLayout, piece, newX, newY);
        updateAllPossibleMoves(pieceLayout);
    }

    private void updateAllPossibleMoves(Piece[][] pieceLayout) {
        playerOne.setOfAllMoves.clear();
        playerTwo.setOfAllMoves.clear();
        playerOne.setOfAllMoves = playerOne.updatePlayerPossibleMoves(pieceLayout);
        playerTwo.setOfAllMoves = playerTwo.updatePlayerPossibleMoves(pieceLayout);
        //System.out.println("UPDATED ALL POSSIBLE MOVES");
    }

    /**
     * Does vital checks before calling the basic isMoveAllowed. If any of the checks is true, it is handled
     * elsewhere and  won't call the isMoveAllowed.
     *
     * @param newX        New X position in the matrix, which piece wants to move.
     * @param newY        New Y position in the matrix, which piece wants to move.
     * @param piece       current piece that has been moved.
     * @param pieceLayout Logical matrix which is worked on.
     * @author Jeffrey Wolff && Johannes Höher
     */
    public void tryAndCheckMove(int newX, int newY, Piece piece, Piece[][] pieceLayout) {
        updateAllPossibleMoves(pieceLayout);
        int oldX = piece.xPos;
        int oldY = piece.yPos;
        //Getting copy of layout to test special cases
        Piece[][] copy = board.getCopiedLayout();

        int deltaX = Math.abs(piece.xPos - newX);

        //****** PLAYER ONE CASES **************
        if (piece.isPlayerOne() && isPlayerOneTurn) {
            if (deltaX == 2 && Objects.equals(piece.getType(), "King") && castle.isMoveWhiteCastle(newX, newY) &&
                    castle.isWhiteCastleAllowed(playerOne.king, copy) && !hasPlayerOneCastled()) {
                castle.performWhiteCastle(piece, newX, newY, copy); // also updates piecelayout
            } else if (Objects.equals(piece.getType(), "Pawn") &&
                    pawnCapture.isPlayerOnePawnCapture(copy, piece, newX, newY)) {
                pawnCapture.playerOnePawnCaptures(copy, piece, newX, newY, board); // White pawn captures an enemy piece
            } else if (isMoveAllowed(newX, newY, piece, copy)) {
                movePiece(newX, newY, piece, copy);
                board.setPieceLayout(copy);
                //updateAllPossibleMoves(copy);
            }
        }
        //********** PLAYER TWO CASES *************
        else if (!piece.isPlayerOne() && !isPlayerOneTurn) {
            //System.out.println("MOVE WAS ALLOWED, NOW CHECKING IS KING IS CHECKED.");
            //System.out.println("BLACKS TURN");
            if (deltaX == 2 && Objects.equals(piece.getType(), "King") && castle.isMoveBlackCastle(newX, newY)
                    && castle.isBlackCastleAllowed(playerTwo.king, copy) && !hasPlayerTwoCastled()) {

                castle.performBlackCastle(piece, newX, newY, copy);
            } else if (Objects.equals(piece.getType(), "Pawn") &&
                    pawnCapture.isPlayerTwoPawnCapture(pieceLayout, piece, newX, newY)) {
                pawnCapture.playerTwoPawnCaptures(pieceLayout, piece, newX, newY, board); // Black pawn captures an enemy piece
            } else if (isMoveAllowed(newX, newY, piece, copy)) {
                movePiece(newX, newY, piece, copy);
                board.setPieceLayout(copy);
            }
        }

        // if not (newX or newY is not equal to my piece current X or current Y)
        if (!(oldX == piece.xPos && oldY == piece.yPos)) { // Piece has moved
            //System.out.println("HAS MOVED");
            if (promotion.isPromotable(piece, newY)) {
                promotion.promote(piece);
            }

            //*** Check if king is Checked *** UPDATES VALUES
            if (isPlayerOneTurn) {
                playerTwo.isChecked = isKingChecked(playerTwo.king, copy);
                /*
                if (playerTwo.isChecked && isCheckMate(playerTwo, copy)) {
                    System.out.println("CheckMate");
                }
                 */
            } else {
                playerOne.isChecked = isKingChecked(playerOne.king, copy);
                /*
                if (playerOne.isChecked && isCheckMate(playerOne, copy)) {
                    System.out.println("CheckMate");
                }
                 */
            }

            //***** Switch turn *****
            isPlayerOneTurn = !isPlayerOneTurn;
            //System.out.println("Player turn: " + isPlayerOneTurn);
        }
    }

    boolean isCheckMate(Player player, Piece[][] layout) {
        boolean checkMate = true;
        for (Tuple<Integer, Integer> move : player.setOfAllMoves) {
            int newX = move.getFirst();
            int newY = move.getSecond();
            Piece dummy = new Queen(0, 0, 100, 100, "", "Queen", player.isPlayerOne(), true);
            movePiece(newX, newY, dummy, layout);
            if (!isKingChecked(player.king, layout)) {
                checkMate = false;
                break;
            }
            movePiece(0, 0, dummy, layout);
        }

        return checkMate;
    }

    /**
     * Goes through the opponents list of possible moves and returns wheaten any of those
     * moves resulting coordinates is equal to the kings Position.
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
     * @param newX        the desired x position
     * @param newY        the desired y position
     * @param pieceLayout the board that contains the pieces
     * @return true if the position on the board is occupied
     */
    public boolean isOccupied(int newX, int newY, Piece[][] pieceLayout) {
        return (pieceLayout[newY][newX] != null);
    }

    /**
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
     * removes an enemy piece from the board when it's killed
     */
    public void killEnemyPiece() {
        //TODO implement this maybe move it to another class
    }

    public boolean hasPlayerOneCastled() {
        return castle.playerOneHasCastled;
    }

    public boolean hasPlayerTwoCastled() {
        return castle.playerTwoHasCastled;
    }


    public void setPlayerKing(Player player, Piece[][] piecesLayout) {
        for (int row = 0; row < piecesLayout.length; row++) {
            for (int col = 0; col < piecesLayout[row].length; col++) {
                if (piecesLayout[row][col] != null) {
                    Piece p = piecesLayout[row][col];
                    //System.out.println(p);
                    if (p.getType().equals("King") && p.isPlayerOne() == player.isPlayerOne()) {
                        player.king = p;
                    }
                }
            }
        }
    }

}

