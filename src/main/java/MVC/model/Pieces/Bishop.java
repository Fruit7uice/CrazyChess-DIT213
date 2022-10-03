package MVC.model.Pieces;

public class Bishop extends Piece{
    public Bishop(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type) {
        super(xPos, yPos, width, height, firstImagePath, secondImagePath, type);
    }


    @Override
    public void move(){

        /*
        piece has been dragged

        check: isOccupied
        false -> move here;
        true ->
            case isOccupied of
                tileType = getType.tile.desiredMove();

                if this.type == tile.pieceType{
                    then can't move here
                    }
                    else(this.type) == opposingColour{
                        capture;
                        diagonalStrategy; // kolla strategi
                    }.

         */





    }
}
