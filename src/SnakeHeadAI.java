import java.util.Random;

public class SnakeHeadAI extends SnakeHead {

    public SnakeHeadAI(Direction snakeDir, Snake s) {
        super(snakeDir, s);
    }

    @Override
    public Coordinate getsHeadNewCoor(int x, int y, Apple target, int currentDirection) {
        Coordinate oldCoordinate = new Coordinate(x, y);
        Coordinate targetCoordinate = new Coordinate(target.getxApple(), target.getyApple());
        // The algorithm is as follows: We check with the currentDirection as a new direction first!
        // Note that the snake cannot move to the opposite direction in which it is moving!
        // For the sake of simplicity, let assume this is also the case when length of the snake = 1 :-).
        int[] options = null;
        switch (currentDirection) {
            case 1: {
                options = new int[] {1,3,4};
                break;
            }
            case 2: {
                options = new int[] {2,3,4};
                break;
            }
            case 3: {
                options = new int[] {3,1,2};
                break;
            }
            case 4: {
                options = new int[] {4,1,2};
                break;
            }
        }
        return evaluateMovement(options, oldCoordinate, targetCoordinate, 0, new double[] {0,0,0});
    }

    private Coordinate evaluateMovement(int[] options, Coordinate oldCoordinate, Coordinate targetCoordinate, int pos, double[] distance) {
        // Step 3: If there is no option left!
        if (pos > options.length - 1) {
            // It could be the case that the other snake moves its tail
            // and the snake AI survives but I am lazy so let's just kill the snake AI.
            // Step 3.1: Move the direction that is closest to the apple.
            int i = MathUtils.min(distance);
            //
            switch (options[i]) {
                case 1: {
                    System.out.println("UP");
                }
                case 2: {
                    System.out.println("DOWN");
                }
                case 3: {
                    System.out.println("RIGHT");
                }
                case 4: {
                    System.out.println("LEFT");
                }
            }

            //
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
        int sHeadNew_xCoor = newCoordinate.getX();
        int sHeadNew_yCoor = newCoordinate.getY();
        return sHeadNew_xCoor <= 0 || sHeadNew_xCoor >= Config.WIDTH/Config.SQUARE_SIZE
                || sHeadNew_yCoor <= 0 || sHeadNew_yCoor >= Config.HEIGHT/Config.SQUARE_SIZE;
    }

    private boolean movable(Coordinate myCoordinate, int direction) {
        Coordinate newCoordinate = move(myCoordinate, direction);
        // You would need (i) the coordinate of the snake AI,
        //                (ii) the coordinate of your snake for the other checks
        return !outOfBound(newCoordinate);
    }

    public Coordinate move(Coordinate myCoordinate, int direction) {
        Coordinate newCoordinate = new Coordinate(myCoordinate.getX(), myCoordinate.getY());
        switch (direction) {
            case 1: {
                newCoordinate.up();
                break;
            }
            case 2: {
                newCoordinate.down();
                break;
            }
            case 3: {
                newCoordinate.right();
                break;
            }
            case 4:
                newCoordinate.left();
                break;
        }
        return newCoordinate;
    }
}
