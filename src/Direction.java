public class Direction {
    /*private*/ boolean up;
    /*private*/ boolean down;
    /*private*/ boolean right;
    /*private*/ boolean left;

    public Direction () {}
    public Direction (boolean moveUp, boolean moveDown, boolean moveLeft, boolean moveRight) {
        this.up = moveUp;
        this.down = moveDown;
        this.left = moveLeft;
        this.right = moveRight;
    }

    public Direction chooseDirection () {
//        Direction rightPath = new Direction();


//        return rightPath;
        return null;
    }

    public int getCurrentDirection () {
        // TODO: Please do a ENUM class, this is too bad!!
        if (up) return 1;
        if (down) return 2;
        if (right) return 3;
        return 4;
    }

}
