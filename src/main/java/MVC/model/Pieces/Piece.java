package MVC.model.Pieces;

public abstract class Piece {

    public int xPos;
    public int yPos;
    public int width;
    public int height;

    private String firstImagePath;
    private String secondImagePath;
    private String type;

    public Piece(int xPos, int yPos, int width, int height, String firstImagePath, String secondImagePath, String type){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.firstImagePath = firstImagePath;
        this.secondImagePath = secondImagePath;
        this.type = type;
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

    void move(){

    }
}
