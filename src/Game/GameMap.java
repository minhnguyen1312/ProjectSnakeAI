package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameMap extends JPanel implements /*Runnable,*/ActionListener, KeyListener {



    // <-- DEFAULT SETTING FOR SNAKE  ---> //
    Snake classic = new Snake(Config.boundSquare,Config.boundSquare);



    //<--- ARRAY OF EACH BODY PART ---> //
    //list <--- array easily overflown
    private final SnakeAbstract snake1;


    // <--- APPLE SIZE ---> //
    private Apple apple;
    private final int appleSize = Config.SQUARE_SIZE;
    private boolean appleAppear;
    private int appleTimer; //moves
    private Timer timer;
    private Random r;

    // Game stat
    private Integer score;
    private String playerName;

    // Game Config
    private Config gameConfig;

    public GameMap() throws IOException {
        // init the game Config
        gameConfig = new Config();
        gameConfig.loadAllConfig();

        setFocusable(true);
        setPreferredSize(new Dimension(Config.WIDTH, Config.HEIGHT));
        snake1 = new KeyController(classic, Direction.RIGHT, gameConfig.snakeColor);

        snake1.addTail(new Snake(Config.boundSquare+1,Config.boundSquare));
        snake1.addTail(new Snake(Config.boundSquare+2,Config.boundSquare));
        SnakeClassic controlKey = new SnakeClassic(snake1);
        addKeyListener(controlKey);

        // init the score
        score = 0;

        r = new Random();
        appleAppear = false;
        start();
        addKeyListener(this);
    }


    public void start() {
        timer = new Timer(gameConfig.DELAY, this);
        timer.start();
    }

    public void stop() {
        Config.running = false;
    }

    //     <--- GAME RUNNING ---> //
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Config.running) {
            repaint();
        }
    }

    public void newApple(Graphics g) {
        if (!appleAppear || appleTimer == 1) {
            //it disappears or 20secs passed
            appleTimer = gameConfig.appleTimer; //moves
            int[] appleCoor = findNonOccupiedAppleSpace();
            int xApple = appleCoor[0];
            int yApple = appleCoor[1];
            // find random unoccupied coordinate

            apple = new Apple(xApple, yApple, appleSize);
            appleAppear = true;
        } else {
            appleTimer--;
        }
        apple.drawApple(g);
    }

    public int[] findNonOccupiedAppleSpace() {
        int[] coor = new int[2];
        while (true) {
            boolean newAppleCoor = true;
            int xApple = r.nextInt(Config.WIDTH / appleSize);       // bound for xApple
            int yApple = r.nextInt(Config.HEIGHT / appleSize);      // bound for yApple
            int n = Config.HEIGHT/Config.SQUARE_SIZE - Config.boundSquare;
            for (int i = 0 ; i < snake1.getSnakeList().size() ; i++) {
                if (xApple == snake1.getSnakeList().get(i).getxCoor() &&
                        yApple == snake1.getSnakeList().get(i).getyCoor()) {
                    newAppleCoor = false;//apple on snake!
                    break;
                }
                if ((xApple < Config.boundSquare || xApple > Config.WIDTH/Config.SQUARE_SIZE - Config.boundSquare-1) ||
                        (yApple < Config.boundSquare || yApple > Config.HEIGHT/Config.SQUARE_SIZE - Config.boundSquare-1)) {
                    newAppleCoor = false;//apple out of bound!
                    break;
                }
            }

            if (newAppleCoor) {
                coor[0] = xApple;
                coor[1] = yApple;
                break;
            }
        }
        return coor;
    }

    public void checkSnake (SnakeAbstract snake, Graphics g) {
        if (!snake.isAliveStatus()) {
            //end game
            stop();
//            saveResult();
        } else {
            snake.buildSnake(g);
            snake.movement(apple);
            snakeCollidesWall(snake);

            //snake eats apple?
            if (snakeEatApple(snake)) {
                score++;
                appleAppear = false;
            } else {
                snake.getSnakeList().remove(0);
            }
        }
    }

    private void saveResult() {
        try {
            File file = new File(".");
            // for(String fileNames : file.list()) System.out.println(fileNames);

//            String path = GameMap.class.getPackage().getName();
            String path = "";
            path += "./src/Game/score.csv";

            FileWriter write = new FileWriter(path,true);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            // ask for the Name of player
            playerName = JOptionPane.showInputDialog("Enter your name");
            if (playerName.equals("")) {
                playerName = "DefaultPlayer";
            }

            //format for saving
            List<String> record = Arrays.asList(playerName,score.toString(),gameConfig.gameDifficulty,apple.getSkin(),dtf.format(now));
            write.append(String.join(",", record));
            write.append("\n");
            write.flush();
            write.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.exit(1);
    }

    public boolean snakeEatApple(SnakeAbstract snake) {
        int snakeSize = snake.getSnakeList().size();
        return snake.getSnakeList().get(snakeSize - 1).getxCoor() == apple.getxApple()
                && snake.getSnakeList().get(snakeSize - 1).getyCoor() == apple.getyApple();
    }

    public void snakeCollidesWall (SnakeAbstract snake) {
        int listSize = snake.getSnakeList().size();
        if (snake.getSnakeList().get(listSize - 1).getxCoor() < Config.boundSquare
                || snake.getSnakeList().get(listSize - 1).getxCoor() > Config.WIDTH/ Config.SQUARE_SIZE - Config.boundSquare-1
                || snake.getSnakeList().get(listSize - 1).getyCoor() < Config.boundSquare
                || snake.getSnakeList().get(listSize - 1).getyCoor() > Config.HEIGHT/ Config.SQUARE_SIZE - Config.boundSquare-1) {
            snake.setAliveStatus(false);
        }
    }

    public void snakeCollidesBody(SnakeAbstract aSnake) {
        Snake s = aSnake.getSnakeList().get(aSnake.getSnakeList().size() - 1);
        //snake collides its own body
        for (int i = 0 ; i < aSnake.getSnakeList().size() - 1 ; i++) {
            if (s.getxCoor() == aSnake.getSnakeList().get(i).getxCoor()
                    && s.getyCoor() == aSnake.getSnakeList().get(i).getyCoor()) {
                aSnake.setAliveStatus(false);
                break;
            }
        }
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, Config.WIDTH, Config.HEIGHT);

        //BACKGROUND color
        g.setColor(Config.GAMEBOUND);
        g.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);

        // draw grid
//        g.setColor(new Color(194, 245, 66));
//        for (int i = 0; i < Config.HEIGHT / Config.SQUARE_SIZE; ++i) {
//            g.drawLine(0, i*Config.SQUARE_SIZE, Config.WIDTH, i*Config.SQUARE_SIZE );
//            //g.drawLine(i*Config.SQUARE_SIZE, 0, Config.HEIGHT, i*Config.SQUARE_SIZE );
//            g.drawLine(i*Config.SQUARE_SIZE, 0, i*Config.SQUARE_SIZE, Config.HEIGHT);
//        }

        // PLAYGROUND color
        g.setColor(Config.BACKGROUND);
        g.fillRect(Config.SQUARE_SIZE* Config.boundSquare, Config.SQUARE_SIZE*Config.boundSquare,
                Config.WIDTH - Config.SQUARE_SIZE*Config.boundSquare*2,
                Config.HEIGHT - Config.SQUARE_SIZE*Config.boundSquare*2);

        g.setFont(Config.SCORE_FONT);
        g.setColor(Color.WHITE);
        g.drawString("SCORE: " + String.format ("%04d", score), Config.SQUARE_SIZE*2 , Config.SQUARE_SIZE*2 - 20);

        newApple(g);
        checkSnake(snake1, g);
        snakeCollidesBody(snake1);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!Config.running && e.getKeyCode() == KeyEvent.VK_SPACE) {
            Config.running = true;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}