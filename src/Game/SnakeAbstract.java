package Game;

import java.awt.*;
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

    public void addTail (Snake snake) {
        this.snakeList.add(snake);
    }

    public List<Snake> getSnakeList() {
        return snakeList;
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
        Snake s = new Snake(newCoordinate.x, newCoordinate.y);
        snakeList.add(s);

        Config.moveAtleastAKey = true;
    }

    public void buildSnake(Graphics g) {
        for (int i = 0; i < snakeList.size(); i++) {
            snakeList.get(i).draw(g, this.snakeColor);// build body
        }

        // build head

        snakeList.get(snakeList.size()-1).drawHead(g, Color.WHITE);
    }
}
