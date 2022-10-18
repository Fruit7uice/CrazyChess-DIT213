package MVC.model.SpecialMoves;

import MVC.model.Pieces.Piece;

import java.util.Objects;

public class EnPassant {

    // Not completed as of right now.


    public boolean isWhiteEnPassant(Piece[][] pieceLayout, Piece pawn, int newX, int newY) {
        if (pawn.yPos == 3 && pawn.isPlayerOne()) {
            if (Objects.equals(pieceLayout[pawn.yPos][pawn.xPos - 1].getType(), "Pawn") && Objects.equals(pieceLayout[pawn.yPos][pawn.xPos - 1].getType(), !pawn.isPlayerOne()) ||
                    Objects.equals(pieceLayout[pawn.yPos][pawn.xPos + 1].getType(), "Pawn") && Objects.equals(pieceLayout[pawn.yPos][pawn.xPos - 1].getType(), !pawn.isPlayerOne())) {
            return true;
            }
        }return false;
    }



    public void whiteEnPassant(Piece[][] pieceLayout, Piece pawn, int newX, int newY){
        if(isWhiteEnPassant(pieceLayout, pawn, newX, newY)){

        }





    }


        public boolean isBlackEnPassant (Piece[][]pieceLayout, Piece pawn,int newX, int newY){

            return false;
        }


    }

