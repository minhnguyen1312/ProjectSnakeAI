import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeaderboardFrame extends JFrame {
    //SCREEN SETTINGS
    final int originalTile = 16; //16*16
    final int scale = 3;

    final int tileSize = originalTile * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    String row = "";

    LeaderboardFrame() {
        openLeaderboard();

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("src\\score.csv"));
            for (int rowCounter = 1 ; rowCounter <= 10 && (row = csvReader.readLine()) != null ; rowCounter++) {
                String[] data = row.split(",");
                drawRow_leaderboard(data, rowCounter);
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawRow_leaderboard(String[] data, int rowCount) {
        for (int i = 0 ; i < data.length  ; i++) {
            JLabel l = new JLabel(data[i]);
            l.setBounds(80*(i+1), 30*rowCount, 120,30);
            this.add(l);
        }
    }

    private void openLeaderboard() {
        this.setTitle("SnakeAI/Leaderboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(screenWidth,screenHeight);
        this.setVisible(true);
    }
}
