import java.awt.*;

public class Snake {
    private Coordinate xyCoor;
    private int  width, height;
    public Snake(int xCoor, int yCoor, int size) {
        this.xyCoor = new Coordinate(xCoor, yCoor);
        this.width = size;
        this.height = size;
    }



    public void draw (Graphics g, Color c) {
        g.setColor(c);
        g.fillRect(xyCoor.x * width, xyCoor.y * height, width, height);
    }


    public int getxCoor () {return xyCoor.x;}
    public int getyCoor () {return xyCoor.y;}
    public Coordinate getXyCoor () {return xyCoor;}
    public int getSize() {return width;}

}
