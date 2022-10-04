package MVC.model.Pieces;

import MVC.model.DummyTile;

public abstract class Piece {
    public int xPos;
    public int yPos;
    public int width;
    public int height;
    private String firstImagePath;
    private String secondImagePath;
    private String type;
    private boolean isPlayer1;

    public Piece(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String typen, boolean isPlayer1){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.firstImagePath = firstImagePath;
        this.secondImagePath = secondImagePath;
        this.type = type;
        this.isPlayer1 = isPlayer1;
    }


    public String getFirstImagePath() {
        return firstImagePath;
    }

    public String getSecondImagePath() {
        return secondImagePath;
    }

    public String getType() {
        return type;
    }
    public abstract boolean legalMove(int newX, int newY);

    /**
     * @param dummyTile the tile that the piece wants to move to
     * @return returns a boolean if a tile is occupied or not
     */
    public boolean isOccupied(DummyTile dummyTile){
        return dummyTile.getIsOccupied();
    }

    /**
     * @param dummyTile the tile that the piece wants to move to
     * @return returns a boolean if the piece on a tile is occupied by an enemy to this piece
     */
    public boolean isOccupiedByEnemy(DummyTile dummyTile){
        boolean piecePlayer1 = dummyTile.getIsPlayer1();
        return piecePlayer1 != this.isPlayer1();
    }

    /**
     * removes an enemy piece from the board when it's killed
     */
    public void killEnemyPiece (){
        //TODO implement this maybe move it to another class
    }

    public boolean isPlayer1() {
        return isPlayer1;
    }

    void move(int newX, int newY){

    }
}
