import java.awt.*;
import java.util.Random;

/* <--- THIS BOT operates on just randomess ---> */
public class RandomMovement extends SnakeHeadAbstract {
    private static final Direction[] DIRECTIONS = new Direction[]{Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
    private final Random rnd = new Random();

    public RandomMovement(Snake snake, Direction snakeDir, Color color) {
        super(snake, snakeDir, color);
    }

    @Override
    public Coordinate getsHeadNewCoor(Coordinate xyCoor, Apple target, Direction currentDirection) {
        int i = rnd.nextInt(4);
        currentDirection = DIRECTIONS[i];

        return xyCoor.moveTo(currentDirection);
//        return xyCoor;
    }

    @Override
    public void keyPressed(int keyCode) {
        // TODO Auto-generated method stub
        // DO NOTHING!
    }
}
