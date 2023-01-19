package Bot.KeyController;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Apple {
    private Coordinate appleCoor;
    private int width, height;

    public Apple (int xApple, int yApple, int size) {
        this.appleCoor = new Coordinate(xApple, yApple);
        this.width = size;
        this.height = size;
    }


    public void drawApple (Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(appleCoor.x * width, appleCoor.y * height, width, height);
    }

    public int getxApple() {
        return appleCoor.x;
    }
    public int getyApple() {
        return appleCoor.y;
    }

}
