package classicGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameMap extends JPanel implements /*Runnable,*/ActionListener {


    private boolean running = false;

    // <-- DEFAULT SETTING FOR SNAKE  ---> //
    Snake classic = new Snake(0,0);



    //<--- ARRAY OF EACH BODY PART ---> //
    //list <--- array easily overflown
    private SnakeAbstract snake1;


    // <--- APPLE SIZE ---> //
    private Apple apple;
    private int appleSize = Config.SQUARE_SIZE;
    private boolean appleAppear;
    private int appleTimer; //moves
    private Timer timer;
    private Random r;

    public GameMap() {
        setFocusable(true);

        setPreferredSize(new Dimension(Config.WIDTH, Config.HEIGHT));
        snake1 = new KeyController(classic, Direction.RIGHT, new Color(123,213,213));
        snake1.addTail(new Snake(1,0));
        snake1.addTail(new Snake(1,1));
        SnakeClassic controlKey = new SnakeClassic(snake1);
        addKeyListener(controlKey);

        r = new Random();
        appleAppear = false;
        start();

    }


    public void start() {
        running = true;
        timer = new Timer(Config.DELAY, this);
        timer.start();

        //
    }

    public void stop() {
        running = false;
    }

    //     <--- GAME RUNNING ---> //
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            repaint();
        }
    }

    public void newApple(Graphics g) {
        if (!appleAppear || appleTimer == 1) {
            //it disappears or 20secs passed
            appleTimer = Config.appleTimer; //moves
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
        int coor[] = new int[2];
        while (true) {
            boolean unoccupied = true;
            int xApple = r.nextInt(Config.WIDTH / appleSize);
            int yApple = r.nextInt(Config.HEIGHT / appleSize);
            for (int i = 0 ; i < snake1.getSnakeList().size() ; i++) {
                if (xApple == snake1.getSnakeList().get(i).getxCoor() &&
                        yApple == snake1.getSnakeList().get(i).getyCoor()) {
                    unoccupied = false;//apple on snake!
                    break;
                }
            }

            if (unoccupied) {
                coor[0] = xApple;
                coor[1] = yApple;
                break;
            }
        }
        return coor;
    }

    public void checkSnake (SnakeAbstract snake, Graphics g) {
        if (!snake.isAliveStatus()) {
            //someone wins?
            saveResult();
            stop();
        } else {
            snake.buildSnake(g);
            snake.movement(apple);
            snakeCollidesWall(snake);

            //snake eats apple?
            if (snakeEatApple(snake)) {
                appleAppear = false;
            } else {
                snake.getSnakeList().remove(0);
            }
        }
    }

    private void saveResult() {
        try {
            FileWriter write = new FileWriter("classicGame/score.csv",true);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            //format for saving
            List<String> record = Arrays.asList("Minh","SnakeType",dtf.format(now));
            write.append(String.join(",", record));
            write.append("\n");
            write.flush();
            write.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean snakeEatApple(SnakeAbstract snake) {
        int snakeSize = snake.getSnakeList().size();
        if (snake.getSnakeList().get(snakeSize-1).getxCoor() == apple.getxApple()
                && snake.getSnakeList().get(snakeSize-1).getyCoor() == apple.getyApple()) {
            return true;
        }

        return false;
    }

    public void snakeCollidesWall (SnakeAbstract snake) {
        int listSize = snake.getSnakeList().size();
        if (snake.getSnakeList().get(listSize - 1).getxCoor() < 0
                || snake.getSnakeList().get(listSize - 1).getxCoor() >= Config.WIDTH/ Config.SQUARE_SIZE
                || snake.getSnakeList().get(listSize - 1).getyCoor() < 0
                || snake.getSnakeList().get(listSize - 1).getyCoor() >= Config.HEIGHT/ Config.SQUARE_SIZE) {
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
        g.setColor(Config.BACKGROUND);
        g.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);

        newApple(g);
        checkSnake(snake1, g);
        snakeCollidesBody(snake1);
    }
}