package Game;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {

    public static boolean running = false;

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

    public File SKIN;


    // <--- TIME BETWEEN "ACTION"  ---> // --> defines game Difficulties
    public static final int DELAY_DEFAULT = 75;
    public static final int appleTimer_DEFAULT = 50; //moves

    public int DELAY;
    public int appleTimer;

    // GAME MODE
    public String gameDifficulty;
    public enum GameMode {
        EASY,
        NORMAL,
        HARD,
        EXTREME,
        MEGA_ULTIMATE
    }

    // Snake Config
    public Color snakeColor;

    // GameBoard Config
    public Color boardColor;


    // init the Game Config
    public void loadAllConfig() throws IOException {
        this.loadPreySkin();
        this.loadGameDifficulty();
        this.loadSnakeColor();
        this.loadBoardColor();
    }


    // load Skin for the Prey
    public void loadPreySkin() throws IOException {
        // default skin = apple
        SKIN = new File("./src/Game/skin/apple8bit.png");
        String skinInput = Files.readAllLines(Paths.get("./src/Game/gameSettings.txt")).get(8 - 1);

        switch (skinInput) {
            case "apple" -> SKIN = APPLE_SKIN;
            case "banana" -> SKIN = BANANA_SKIN;
            case "cherry" -> SKIN = CHERRY_SKIN;
            case "mouse" -> SKIN = MOUSE_SKIN;
        }
    }


    // load game Difficulty
    public void loadGameDifficulty() throws IOException {
        String gameModeInput = Files.readAllLines(Paths.get("./src/Game/gameSettings.txt")).get(2 - 1);
        this.gameDifficulty = gameModeInput;

        switch (gameModeInput) {
            case "Easy" -> {
                this.DELAY = 125;
                this.appleTimer = 80;
            }
            case "Normal" -> {
                this.DELAY = 100;
                this.appleTimer = 65;
            }
            case "Hard" -> {
                this.DELAY = 60;
                this.appleTimer = 40;
            }
            case "Extreme" -> {
                this.DELAY = 30;
                this.appleTimer = 30;
            }
            case "Mega Ultimate" -> {
                this.DELAY = 20;
                this.appleTimer = 10;
            }
        }
    }


    public void loadSnakeColor() throws IOException {
        String snakeColorInput = Files.readAllLines(Paths.get("./src/Game/gameSettings.txt")).get(6 - 1);

        switch (snakeColorInput) {
            case "red" -> this.snakeColor = Color.RED;
            case "blue" -> this.snakeColor = Color.BLUE;
            case "yellow" -> this.snakeColor = Color.YELLOW;
            case "green" -> this.snakeColor = Color.GREEN;
            case "white" -> this.snakeColor = Color.WHITE;
            case "sky blue" -> this.snakeColor = new Color(123,213,213);
        }
    }


    public void loadBoardColor() {

    }

}
