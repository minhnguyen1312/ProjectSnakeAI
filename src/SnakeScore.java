import java.util.Date;

class SnakeScore implements Comparable<SnakeScore> {

    private String player;
    private int score;
    private int difficulty;
    private String date;
    public SnakeScore(String playerName, int score, int difficulty, String date) {
        this.player = playerName;
        this.score = score;
        this.difficulty = difficulty;
        this.date = date;
    }

    @Override
    public int compareTo(SnakeScore other) {

        if (this.score == other.score && this.difficulty > other.difficulty || this.score > other.score) {
            return -1;
        } else {
            return 1;
        }
    }


    @Override
    public String toString() {
        return player + "," + score + "," + difficulty + "," + date;
    }
}