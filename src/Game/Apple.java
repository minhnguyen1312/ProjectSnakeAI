package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Apple {
    private Coordinate appleCoor;
    private int width, height;

    public Apple (int xApple, int yApple, int size) {
//        try {
//            skin = ImageIO.read(new File("./skin/apple.png"));
//        } catch (IOException e) {
//            didLoadSkin = false;
//        }

        this.appleCoor = new Coordinate(xApple, yApple);
        this.width = size;
        this.height = size;
    }


    public void drawApple (Graphics g) {
//        g.setColor(Color.RED);
//        g.fillOval(appleCoor.x * width, appleCoor.y * height, width, height);

            //g.setColor(Color.RED);
            //g.fillOval(appleCoor.x * width, appleCoor.y * height, width, height);

        try {
            BufferedImage img = ImageIO.read(Config.MOUSE_SKIN);
            g.drawImage(img, appleCoor.x * width, appleCoor.y * height, width, height, null);
        } catch (Exception ex){
            g.setColor(Color.RED);
            g.fillOval(appleCoor.x * width, appleCoor.y * height, width, height);
            ex.printStackTrace();
        }


    }

    public int getxApple() {
        return appleCoor.x;
    }
    public int getyApple() {
        return appleCoor.y;
    }

}
