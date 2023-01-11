import java.awt.*;
import java.util.ArrayList;
//<--- THIS IS RESPONSIBLE FOR A WHOLE BODY + ALIVE STATUS OF A SNAKE
public class SnakeHead {
    private ArrayList<Snake> snakeList;
    private Direction snakeDir;
    private boolean aliveStatus;




    public SnakeHead(Direction snakeDir, Snake s) {
        aliveStatus = true;
        this.snakeDir = snakeDir;

        snakeList = new ArrayList<Snake>();
        snakeList.add(s);

    }

//    public void newSnake(Snake s) {
//        snakeList.add(s);
////        System.out.print(snakeList.size());\
////            System.out.print(snakeList.get(0).getxCoor());
//    }

    public ArrayList<Snake> getSnakeList() {return snakeList;}
    public void setSnakeAliveStatus () {
        this.aliveStatus = false;
    }

    public boolean getSnakeAliveStatus () {  return aliveStatus;  }

    public int getSnakePt() {   return snakeList.size() - 1;   }

    public void buildSnake(Graphics g) {
        int size = snakeList.get(0).getSize();
        for (int i = 0; i < snakeList.size() ; i++) {
            int xCoor = snakeList.get(i).getxCoor();
            int yCoor = snakeList.get(i).getyCoor();
//            System.out.print(xCoor + " " + yCoor + " ");
            Snake s = new Snake(xCoor, yCoor, size);
            s.draw(g, new Color(23,183,228));
        }
    }

    public void movement (Apple target) {
//        snakeDir = snakeDir.chooseDirection();
        Snake sHead = snakeList.get(snakeList.size() -1);

        int currentDirection = snakeDir.getCurrentDirection();

        Coordinate newCoordinate = getsHeadNewCoor(sHead.getxCoor(), sHead.getyCoor(), target, currentDirection);

        Snake s = new Snake (newCoordinate.getX(), newCoordinate.getY(), sHead.getSize());
        snakeList.add(s);

        //if snake not eats apple in MAP --> remove
//        snakeList.remove(0);

    }

    public Coordinate getsHeadNewCoor(int x, int y, Apple target, int currentDirection) {
        int sHeadNew_xCoor = x, sHeadNew_yCoor = y;
        if (snakeDir.up) {
            sHeadNew_yCoor--;
        } else if (snakeDir.down) {
            sHeadNew_yCoor++;
        } else if (snakeDir.left) {
            sHeadNew_xCoor--;
        } else /*right*/ {
            sHeadNew_xCoor++;
        }
        return new Coordinate(sHeadNew_xCoor,sHeadNew_yCoor);
    }

    public Direction getSnakeDir() {
        return snakeDir;
    }

}
