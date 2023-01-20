package classicGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class SnakeAbstract implements Movable {
    protected List<Snake> snakeList;
    protected Color snakeColor;
    protected Direction snakeDir;
    protected boolean aliveStatus;

    public SnakeAbstract(Snake snake, Direction snakeDir, Color snakeColor) {
        List<Snake> snakeList = new ArrayList<Snake>();
        snakeList.add(snake);
        this.snakeList = snakeList;
        this.snakeDir = snakeDir;
        this.snakeColor = snakeColor;
        this.aliveStatus = true;
    }

    public List<Snake> getSnakeList() {
        return snakeList;
    }

    public void setSnakeList(List<Snake> snakeList) {
        this.snakeList = snakeList;
    }

    public Direction getSnakeDir() {
        return snakeDir;
    }

    public void setSnakeDir(Direction snakeDir) {
        this.snakeDir = snakeDir;
    }

    public boolean isAliveStatus() {
        return aliveStatus;
    }

    public void setAliveStatus(boolean aliveStatus) {
        this.aliveStatus = aliveStatus;
    }

    public abstract Coordinate getsHeadNewCoor(Coordinate xyCoor, Apple target, Direction currentDirection);

    public void movement(Apple target) {

        Snake sHead = snakeList.get(snakeList.size() - 1);

        Direction currentDirection = snakeDir;

        Coordinate newCoordinate = getsHeadNewCoor(sHead.getXyCoor(), target, currentDirection);
        Snake s = new Snake(newCoordinate.x, newCoordinate.y, sHead.getSize());
        snakeList.add(s);
    }

    public void buildSnake(Graphics g) {
        int size = snakeList.get(0).getSize();

        for (int i = 0; i < snakeList.size(); i++) {
            System.out.println("Build");

            int xCoor = snakeList.get(i).getxCoor();
            int yCoor = snakeList.get(i).getyCoor();
//            System.out.print(xCoor + " " + yCoor + " ");
            Snake s = new Snake(xCoor, yCoor, size);
            s.draw(g, this.snakeColor);// build body

        }

        // build head\
    }
}
