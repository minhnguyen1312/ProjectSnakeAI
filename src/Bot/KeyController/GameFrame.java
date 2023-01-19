package Bot.KeyController;
import javax.swing.JFrame;

public class GameFrame {
    public GameFrame() {
        JFrame frame = new JFrame();
        GameMap map = new GameMap();

        frame.add(map);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake Game");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

