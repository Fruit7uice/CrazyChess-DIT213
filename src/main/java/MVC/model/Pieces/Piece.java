package MVC.model.Pieces;

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

    public boolean isPlayer1() {
        return isPlayer1;
    }

    void move(int newX, int newY){

    }
}
