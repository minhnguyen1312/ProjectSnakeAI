package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame implements ActionListener {
    //SCREEN SETTINGS
    final int originalTile = 16; //16*16
    final int scale = 3;

    final int tileSize = originalTile * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    static JButton applyChange = new JButton();
    //    static JPanel snake1 = new JPanel();
//    static JPanel snake2 = new JPanel();
    private static JTextField textSnake1Name = new JTextField();
    private static JTextField textSnake1Type = new JTextField();
    private static JTextField textSnake1Color = new JTextField();

    private static JTextField textSnake2Name = new JTextField();
    private static JTextField textSnake2Type = new JTextField();
    private static JTextField textSnake2Color = new JTextField();

    public static String snake1Name;
    public static int snake1Type;
    public static Color snake1Color;

    public static String snake2Name;
    public static int snake2Type;
    public static Color snake2Color;
    public SettingsFrame() {
        this.setTitle("SnakeAI/Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(screenWidth,screenHeight);
        this.setVisible(true);

        applyChange.setBounds(tileSize*5,tileSize*3, tileSize*7,tileSize*1);
        applyChange.setText("CHANGE!!!!");
        applyChange.setFont(new Font("Comic Sans",Font.BOLD,25));
        applyChange.setFocusable(false);
        applyChange.setHorizontalTextPosition(JButton.CENTER);
        applyChange.setVerticalTextPosition(JButton.CENTER);
        applyChange.setForeground(Color.white);
        applyChange.setBackground(new Color(0,204,102));
        applyChange.addActionListener(this);


        this.add(applyChange);

        textSnake1Name.setBounds(50,50,120,30);
        this.add(textSnake1Name);
        textSnake1Type.setBounds(50,100,120,30);
        this.add(textSnake1Type);
        textSnake1Color.setBounds(50,150,120,30);
        this.add(textSnake1Color);
        textSnake2Name.setBounds(50,250,120,30);
        this.add(textSnake2Name);
        textSnake2Type.setBounds(50,300,120,30);
        this.add(textSnake2Type);
        textSnake2Color.setBounds(50,350,120,30);
        this.add(textSnake2Color);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        this.snake1Name = textSnake1Name.getText();
        this.snake1Type = Integer.parseInt(textSnake1Type.getText());
        this.snake1Color = ColorParse.StringToColor(textSnake1Color.getText());

        this.snake2Name = textSnake2Name.getText();
        this.snake2Type = Integer.parseInt(textSnake2Type.getText());
        this.snake2Color = ColorParse.StringToColor(textSnake2Color.getText());
        this.dispose();
    }
}

