import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Apple {
    private int xApple;
    private int yApple;
    private int width, height;

    public Apple (int xApple, int yApple, int size) {
        this.xApple = xApple;
        this.yApple = yApple;
        width = size;
        height = size;
    }


    public void drawApple (Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(xApple * width, yApple * height, width, height);
    }

    public int getxApple() {
        return xApple;
    }
    public int getyApple() {
        return yApple;
    }

}
