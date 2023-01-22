package Game;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Config {
    public static final int WIDTH = 750, HEIGHT = 750;
    public static final int SQUARE_SIZE = 30;
    public static final int boundSquare = 2;
    public static final Color BACKGROUND = Color.DARK_GRAY;
    public static final Color GAMEBOUND = Color.BLACK;
    public static boolean moveAtleastAKey = false;
    public static Font SCORE_FONT = new Font("Comic Sans", Font.PLAIN, 24);

    // Prey Type
    public static File APPLE_SKIN = new File("./src/Game/skin/apple8bit.png");
    public static File CHERRY_SKIN = new File("./src/Game/skin/cherry.png");
    public static File BANANA_SKIN = new File("./src/Game/skin/banana.png");
    public static File MOUSE_SKIN = new File("./src/Game/skin/mouse.png");

    // <--- TIME BETWEEN "ACTION"  ---> //
    public static final int DELAY = 75;

    public static final int appleTimer = 50; //moves


    public boolean didLoadSkin = true;
    public BufferedImage skin;
}
