package classicGame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SnakeClassic implements KeyListener {
    private SnakeAbstract snake;


    public SnakeClassic(SnakeAbstract snake) {
        super();
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        snake.keyPressed(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
