import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private SnakeHeadAbstract snake1;
    private SnakeHeadAbstract snake2;



    public Controller(SnakeHeadAbstract snake1, SnakeHeadAbstract snake2) {
        super();
        this.snake1 = snake1;
        this.snake2 = snake2;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        snake1.keyPressed(key);
        snake2.keyPressed(key);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
