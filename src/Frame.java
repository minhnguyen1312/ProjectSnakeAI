import javax.swing.JFrame;

public class Frame {
    public Frame() {
        JFrame frame = new JFrame();
        Map map = new Map();

        frame.add(map);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake Game");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
