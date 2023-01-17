package Game;

import GUI.Config;

import java.awt.*;

public class UnfinishedAI extends SnakeHeadAbstract {

    public UnfinishedAI(Snake s, Direction snakeDir, Color color) {

        super(s, snakeDir, color);
    }

    @Override
    public Coordinate getsHeadNewCoor(Coordinate oldCoordinate, Apple target, Direction currentDirection) {
        Coordinate targetCoordinate = new Coordinate(target.getxApple(), target.getyApple());
        // The algorithm is as follows: We check with the currentDirection as a new direction first!
        // Note that the snake cannot move to the opposite direction in which it is moving!
        // For the sake of simplicity, let assume this is also the case when length of the snake = 1 :-).

        Direction[] options = new Direction[3];
        if (currentDirection == Direction.UP) {
            options =  new Direction[]{Direction.UP, Direction.LEFT, Direction.RIGHT};
        } else if (currentDirection == Direction.DOWN) {
            options =  new Direction[]{Direction.DOWN, Direction.LEFT, Direction.RIGHT};
        } else if (currentDirection == Direction.LEFT) {
            options =  new Direction[]{Direction.UP, Direction.LEFT, Direction.DOWN};
        } else if (currentDirection == Direction.RIGHT) {
            options =  new Direction[]{Direction.UP, Direction.DOWN, Direction.RIGHT};
        }
        return evaluateMovement(options, oldCoordinate, targetCoordinate, 0, new double[] {0,0,0});
    }

    private Coordinate evaluateMovement(Direction[] options, Coordinate oldCoordinate, Coordinate targetCoordinate, int pos, double[] distance) {
        // Step 3: If there is no option left!
        if (pos > options.length - 1) {
            // It could be the case that the other snake moves its tail
            // and the snake AI survives but I am lazy so let's just kill the snake AI.
            // Step 3.1: Move the direction that is closest to the apple.
            int i = MathUtils.min(distance);

            Coordinate newCoordinate = move(oldCoordinate, options[i]);
            return newCoordinate;
        }
        // Step 1: Check if this direction is possible
        if (movable(oldCoordinate, options[pos])) {
            // Step 1.1: If so, check if it is closer to the apple
            Coordinate newCoordinate = move(oldCoordinate, options[pos]);
            Double newDistance = MathUtils.distance(newCoordinate, targetCoordinate);
            distance[pos] = newDistance;
        } else {
            distance[pos] = Double.MAX_VALUE;
        }
        // Step 2: If not, then we should choose another direction!
        return evaluateMovement(options, oldCoordinate, targetCoordinate, pos+1, distance);
    }

    private boolean outOfBound(Coordinate newCoordinate) {
        int sHeadNew_xCoor = newCoordinate.x;
        int sHeadNew_yCoor = newCoordinate.y;
        return sHeadNew_xCoor <= 0 || sHeadNew_xCoor >= Config.WIDTH/Config.SQUARE_SIZE
                || sHeadNew_yCoor <= 0 || sHeadNew_yCoor >= Config.HEIGHT/Config.SQUARE_SIZE;
    }

    private boolean movable(Coordinate myCoordinate, Direction direction) {
        Coordinate newCoordinate = move(myCoordinate, direction);
        // You would need (i) the coordinate of the snake AI,
        //                (ii) the coordinate of your snake for the other checks
        return !outOfBound(newCoordinate);
    }

    public Coordinate move(Coordinate myCoordinate, Direction direction) {
        Coordinate newCoordinate = new Coordinate(myCoordinate.x, myCoordinate.y);
        return newCoordinate.moveTo(direction);
    }




    @Override
    public void keyPressed(int keyCode) {
        //DO NOTHING
    }
}


//
