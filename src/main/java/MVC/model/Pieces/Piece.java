package MVC.model.Pieces;

public abstract class Piece {

    public int xPos;
    public int yPos;
    public int width;
    public int height;
    private String imagePath;
    private String type;

    public Piece(int xPos, int yPos, int width, int height, String imagePath, String type){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.imagePath = imagePath;
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getType() {
        return type;
    }

    void move(){

    }
}
