package Game;

import java.awt.*;
import java.awt.event.KeyEvent;

//<--- THIS IS RESPONSIBLE FOR A WHOLE BODY + ALIVE STATUS OF A SNAKE
public class KeyController extends SnakeAbstract {

    public KeyController(Snake snake, Direction snakeDir, Color color) {
        super(snake, snakeDir, color);
    }

    @Override
    public Coordinate getsHeadNewCoor(Coordinate xyCoor, Apple target, Direction currentDirection) {
        return xyCoor.moveTo(currentDirection);
    }

    @Override
    public void keyPressed(int keyCode) {
        if (Config.moveAtleastAKey) {
            if (keyCode == KeyEvent.VK_RIGHT && !(this.getSnakeDir() == Direction.LEFT)) {
                this.setSnakeDir(Direction.RIGHT);
            } else if (keyCode == KeyEvent.VK_LEFT && !(this.getSnakeDir() == Direction.RIGHT)) {
                this.setSnakeDir(Direction.LEFT);
            } else if (keyCode == KeyEvent.VK_UP && !(this.getSnakeDir() == Direction.DOWN)) {
                this.setSnakeDir(Direction.UP);
            } else if (keyCode == KeyEvent.VK_DOWN && !(this.getSnakeDir() == Direction.UP)) {
                this.setSnakeDir(Direction.DOWN);
            }
            Config.moveAtleastAKey = false;
        }
    }
}

