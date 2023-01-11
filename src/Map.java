import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;

public class Map extends JPanel implements /*Runnable,*/ KeyListener, ActionListener{


    private boolean running = false;

    // <-- DEFAULT SETTING FOR SNAKE 1 ---> //
    private static final int init1XCoor = 1, init1YCoor = 12;
    //    private int snake1Size = 1;
    private Direction dirSnake1 = new Direction(false, false, false, true);


    // <-- DEFAULT SETTING FOR SNAKE 2 ---> //
    private static final int init2XCoor = (Config.WIDTH/Config.SQUARE_SIZE-1) - init1XCoor;
    private static final int init2YCoor = (Config.HEIGHT/Config.SQUARE_SIZE-1) - init1YCoor;
    //    private int snake2Size = 1;
    private Direction dirSnake2 = new Direction(false, false, true, false);



    //<--- ARRAY OF EACH BODY PART ---> //
    //list <--- array easily overflown
    private SnakeHead snake1;
    //    private SnakeHeadAI snake1;
    private SnakeHeadAI snake2;


    // <--- APPLE SIZE ---> //
    private Apple apple;
    private int appleSize = Config.SQUARE_SIZE;
    private boolean appleAppear;
    private int appleTimer = Config.appleTimer; //moves

    private Timer timer;


    private Random r;

    public Map() {
        setFocusable(true);

        addKeyListener(this);
        setPreferredSize(new Dimension(Config.WIDTH, Config.HEIGHT));

        //<---      choose MANUAL or AI   --->//
        snake1 = new SnakeHead(dirSnake1, new Snake(init1XCoor, init1YCoor, Config.SQUARE_SIZE));
//        		new SnakeHeadAI(dirSnake1, new Snake(init1XCoor, init1YCoor, Config.SQUARE_SIZE));
        snake2 = new SnakeHeadAI(dirSnake2, new Snake(init2XCoor, init2YCoor, Config.SQUARE_SIZE));

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
        timer.stop();
    }

    public void newApple(Graphics g) {
        if (!appleAppear || appleTimer == 1) {
            //it disappears or 10secs passed
            appleTimer = Config.appleTimer; //moves
            int xApple = findNonOccupiedAppleSpace()[0];
            int yApple = findNonOccupiedAppleSpace()[1];
            // find random unoccupied coordinate


            apple = new Apple(xApple, yApple, appleSize);
            appleAppear = true;
        } else {
            appleTimer--;
//            System.out.print(appleTimer);
        }
        apple.drawApple(g);
    }

    public int[] findNonOccupiedAppleSpace() {
        int coor[] = new int[2];
        boolean unoccupied = true;
        while (true) {
            int xApple = r.nextInt(Config.WIDTH / appleSize);
            int yApple = r.nextInt(Config.HEIGHT / appleSize);
            for (int i = 0 ; i < snake1.getSnakeList().size() ; i++) {
                if (xApple == snake1.getSnakeList().get(i).getxCoor() &&
                        yApple == snake1.getSnakeList().get(i).getyCoor()) {
                    unoccupied = false;//apple on snake!
                    break;
                }
            }
            for (int i = 0 ; i < snake1.getSnakeList().size() ; i++) {
                if (xApple == snake1.getSnakeList().get(i).getxCoor() &&
                        yApple == snake1.getSnakeList().get(i).getyCoor()) {
                    unoccupied = false;
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

    public void checkSnake (SnakeHead snake, Graphics g) {
        if (!snake.getSnakeAliveStatus()) {
            //someone wins?
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

    public boolean snakeEatApple(SnakeHead snake) {
        int snakeSize = snake.getSnakeList().size();
        if (snake.getSnakeList().get(snakeSize-1).getxCoor() == apple.getxApple()
                && snake.getSnakeList().get(snakeSize-1).getyCoor() == apple.getyApple()) {
            return true;
        }

        return false;
    }

    public void snakeCollidesWall (SnakeHead snake) {
        int listSize = snake.getSnakeList().size();
        if (snake.getSnakeList().get(listSize - 1).getxCoor() < 0
                || snake.getSnakeList().get(listSize - 1).getxCoor() > Config.WIDTH/Config.SQUARE_SIZE
                || snake.getSnakeList().get(listSize - 1).getyCoor() < 0
                || snake.getSnakeList().get(listSize - 1).getyCoor() > Config.HEIGHT/Config.SQUARE_SIZE) {
            snake.setSnakeAliveStatus();
        }
    }

    public void snakeCollidesBody(SnakeHead aSnake, SnakeHead otherSnake) {
        Snake s = aSnake.getSnakeList().get(aSnake.getSnakeList().size() - 1);
        //snake collides its own body
        for (int i = 0 ; i < aSnake.getSnakeList().size() - 1 ; i++) {
            if (s.getxCoor() == aSnake.getSnakeList().get(i).getxCoor()
                    && s.getyCoor() == aSnake.getSnakeList().get(i).getyCoor()) {
                aSnake.setSnakeAliveStatus();
                System.out.print("collides on its own!!");
                break;
            }
        }

        //snake collides other
        for (int i = 0 ; i < otherSnake.getSnakeList().size() ; i++) {
            if (s.getxCoor() == otherSnake.getSnakeList().get(i).getxCoor()
                    && s.getyCoor() == otherSnake.getSnakeList().get(i).getyCoor()) {
                aSnake.setSnakeAliveStatus();
                System.out.print("collides each other!!");
                break;
            }
        }
    }

    // <--- Press UP DOWN LEFT RIGHT ---> //
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT && !snake1.getSnakeDir().left) {
            snake1.getSnakeDir().up = false;
            snake1.getSnakeDir().down = false;
            snake1.getSnakeDir().right = true;
        }
        if(key == KeyEvent.VK_LEFT && !snake1.getSnakeDir().right) {
            snake1.getSnakeDir().up = false;
            snake1.getSnakeDir().down = false;
            snake1.getSnakeDir().left = true;
        }
        if(key == KeyEvent.VK_UP && !snake1.getSnakeDir().down) {
            snake1.getSnakeDir().left = false;
            snake1.getSnakeDir().right = false;
            snake1.getSnakeDir().up = true;
        }
        if(key == KeyEvent.VK_DOWN && !snake1.getSnakeDir().up) {
            snake1.getSnakeDir().left = false;
            snake1.getSnakeDir().right = false;
            snake1.getSnakeDir().down = true;
        }
    }


    //     <--- GAME RUNNING ---> //
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
//            System.out.print("running?");
            repaint();
        }
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, Config.WIDTH, Config.HEIGHT);


        //BACKGROUND color
        g.setColor(Config.BACKGROUND);
        g.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);


        //Grid color
//        g.setColor(Color.GREEN);
        // <--- DRAW GAME GRID ---> //
//        for(int i = 0; i < HEIGHT / squareSize; ++i) {
//            g.drawLine(i * squareSize, 0, i * squareSize, HEIGHT);
//        }
//        for (int i = 0; i < WIDTH / snake1Size ; i++) {
//            g.drawLine(0, i * squareSize, WIDTH, i * squareSize);
//        }

        newApple(g);
        checkSnake(snake1, g);
        checkSnake(snake2, g);
        snakeCollidesBody(snake1, snake2);
        snakeCollidesBody(snake2, snake1);
    }



    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}