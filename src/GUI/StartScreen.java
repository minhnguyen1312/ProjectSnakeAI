package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;

import DefaultBotFrameWork.SnakesUIMain;
import Game.Map;


public class StartScreen extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    //SCREEN SETTINGS
    final int originalTile = 16; //16*16
    final int scale = 3;

    final int tileSize = originalTile * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    JButton playButton = new JButton();
    JButton settingsButton = new JButton();
    JButton leaderboardButton = new JButton();
    JButton quitgameButton = new JButton();
    JButton infoButton = new JButton();

    private ImageIcon StartScreenBackground;
    private JLabel labelContainer;

    // Settings GUI.Frame
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

    // Leaderboard
    String row = "";

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public StartScreen() {
        paintStartScreen();
    }

    public void paintFrame() {
        SnakesUIMain newFrame = new SnakesUIMain();
        try {
            //String[] args = "";
            newFrame.main(null);
        } catch (InterruptedException | IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.print("Error occured!");
        }
    }

    public void paintStartScreen() {
        ImageIcon logo = new ImageIcon(this.getClass().getResource("/GUI/img/logo.png"));
        this.setIconImage(logo.getImage());

        StartScreenBackground = new ImageIcon(this.getClass().getResource("/GUI/img/backgrv3.jpg"));
        labelContainer = new JLabel(StartScreenBackground);
        labelContainer.setSize(new Dimension(screenWidth, screenHeight));

        // paint all Buttons
        playButton.setBounds(tileSize*5,tileSize*3 + 50, tileSize*7,tileSize*1);
        playButton.setText("PLAY");
        playButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        playButton.setFocusable(false);
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.CENTER);
        playButton.setForeground(Color.white);
        playButton.setBackground(new Color(0,204,102));
        playButton.addActionListener(this);

        //Insets insets = this.getInsets();
        //Dimension size = playButton.getPreferredSize();


        settingsButton.setBounds(tileSize*5,tileSize*3 + 140, tileSize*7,tileSize*1);
        settingsButton.setText("SETTINGS");
        settingsButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        settingsButton.setFocusable(false);
        settingsButton.setHorizontalTextPosition(JButton.CENTER);
        settingsButton.setVerticalTextPosition(JButton.CENTER);
        settingsButton.setForeground(Color.white);
        settingsButton.setBackground(new Color(0,204,102));
        settingsButton.addActionListener(this);

        leaderboardButton.setBounds(tileSize*5,tileSize*3 + 230, tileSize*7,tileSize*1);
        leaderboardButton.setText("LEADERBOARD");
        leaderboardButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        leaderboardButton.setFocusable(false);
        leaderboardButton.setHorizontalTextPosition(JButton.CENTER);
        leaderboardButton.setVerticalTextPosition(JButton.CENTER);
        leaderboardButton.setForeground(Color.white);
        leaderboardButton.setBackground(new Color(0,204,102));
        leaderboardButton.addActionListener(this);


        quitgameButton.setBounds(tileSize*5,tileSize*3 + 320, tileSize*7,tileSize*1);
        quitgameButton.setText("QUIT GAME");
        quitgameButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        quitgameButton.setFocusable(false);
        quitgameButton.setHorizontalTextPosition(JButton.CENTER);
        quitgameButton.setVerticalTextPosition(JButton.CENTER);
        quitgameButton.setForeground(Color.white);
        quitgameButton.setBackground(new Color(0,204,102));
        quitgameButton.addActionListener(this);


        infoButton.setBounds(2 + 5,tileSize*14 - 10, tileSize*3,tileSize*1);
        infoButton.setText("About us");
        infoButton.setFont(new Font("Comic Sans",Font.BOLD,21));
        infoButton.setFocusable(false);
        infoButton.setHorizontalTextPosition(JButton.CENTER);
        infoButton.setVerticalTextPosition(JButton.CENTER);
        infoButton.setForeground(Color.white);
        infoButton.setBackground(Color.black);
        infoButton.addActionListener(this);




        this.setTitle("SnakeAI Revolution");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(screenWidth,screenHeight);
        this.setVisible(true);



        this.getContentPane().setBackground(new Color(102,204,0));
        this.add(playButton);
        this.add(settingsButton);
        this.add(leaderboardButton);
        this.add(quitgameButton);
        this.add(infoButton);

        this.add(labelContainer);      // insert background img at last

        //this.pack();
        this.setVisible(true);

        centreWindow(this);

    }


    public void paintSettingsFrame() {
        this.setTitle("SnakeAI Revolution/Settings");
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

    public void paintLeaderboardFrame() {
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
        this.setTitle("SnakeAI Revolution/Leaderboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(screenWidth,screenHeight);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            this.setVisible(false);
            this.dispose();
            paintFrame();
        } else if (e.getSource() == settingsButton) {
            //GUI.SettingsFrame myWindow = new GUI.SettingsFrame();
            this.getContentPane().removeAll();
            this.validate();
            this.repaint();
            this.paintSettingsFrame();
        } else if (e.getSource() == leaderboardButton) {
            this.getContentPane().removeAll();
            this.validate();
            this.repaint();
            this.paintLeaderboardFrame();
        } else if (e.getSource() == quitgameButton) {
            int respone = JOptionPane.showConfirmDialog(null, "You are about to exit the Game. Are you sure?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (respone == JOptionPane.YES_OPTION) {
                this.setVisible(false);
                this.dispose();
            }

        } else if (e.getSource() == infoButton) {

        }

    }
}
