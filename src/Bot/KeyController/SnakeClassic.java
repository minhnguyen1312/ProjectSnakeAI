package Bot.KeyController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SnakeClassic implements KeyListener {
    private SnakeAbstract snake1;


    public SnakeClassic(SnakeAbstract snake1) {
        super();
        this.snake1 = snake1;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        snake1.keyPressed(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
