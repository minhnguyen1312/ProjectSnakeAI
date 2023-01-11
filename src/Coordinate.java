public class Coordinate {
    private int x;
    private int y;

    public int getX () {
        return x;
    }

    public int getY() {
        return y;
    }

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void changeCoor() {

    }

    public void up() {
        this.y--;
    }

    public void down() {
        this.y++;
    }

    public void left() {
        this.x--;
    }

    public void right() {
        this.x++;
    }
}
