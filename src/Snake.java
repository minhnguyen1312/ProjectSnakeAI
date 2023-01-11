import java.awt.*;

public class Snake {
    private int xCoor, yCoor, width, height;
    public Snake(int xCoor, int yCoor, int size) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = size;
        height = size;
    }



    public void draw (Graphics g, Color c) {
        g.setColor(c);
        g.fillRect(xCoor * width, yCoor * height, width, height);
    }
    //
    public int getxCoor() {return  xCoor;}
    public int getyCoor() {return  yCoor;}
    public int getSize() {return width;}





}
