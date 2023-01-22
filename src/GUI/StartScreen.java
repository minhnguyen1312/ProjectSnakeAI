package GUI;

import Game.GameFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;
import javax.swing.*;


public class StartScreen extends JFrame implements ActionListener, Runnable {

    @Serial
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
    JButton statisticsButton = new JButton();
    JButton quitgameButton = new JButton();
    JButton infoButton = new JButton();
    JButton backButton = new JButton();


    private final ImageIcon StartScreenBackground = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GUI/img/backgrv3.jpg")));
    private final ImageIcon SettingBackground = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GUI/img/settingsv4.png")));
    private final ImageIcon StatisticsBackground = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GUI/img/gameStatistics.png")));
    private ImageIcon logo;
    private JLabel labelContainer;

    // Settings GUI.Frame
    JButton applySettingChanges = new JButton();

    JTable statisticsTable = new JTable();

    // single player
    private final static Combobox<String> gameDifficulty = new Combobox<>();
    private final static Combobox<String> playerColorCombobox = new Combobox<>();
    private final static Combobox<String> playerBoardColor = new Combobox<>();
    private final static Combobox<String> playerPreyType = new Combobox<>();


    private final static Combobox<String> bot1NameCombobox = new Combobox<>();
    private final static Combobox<String> bot2NameCombobox = new Combobox<>();
    private final static JTextField botNumberofTournaments = new JTextField();


    // Leaderboard
    String row = "";

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public StartScreen() {
        //paintStartScreen();

        backButton.setBounds(2 + 680,tileSize*14 + 2, tileSize*3,tileSize);
        backButton.setText("Back");
        backButton.setFont(new Font("Comic Sans",Font.BOLD,21));
        backButton.setFocusable(false);
        backButton.setHorizontalTextPosition(JButton.CENTER);
        backButton.setVerticalTextPosition(JButton.CENTER);
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.black);
        backButton.addActionListener(this);

        gameDifficulty.addItem("Easy");
        gameDifficulty.addItem("Normal");
        gameDifficulty.addItem("Hard");
        gameDifficulty.addItem("Extreme");
        gameDifficulty.addItem("Mega Ultimate");


        playerColorCombobox.addItem("red");
        playerColorCombobox.addItem("blue");
        playerColorCombobox.addItem("yellow");
        playerColorCombobox.addItem("green");
        playerColorCombobox.addItem("white");
        playerColorCombobox.addItem("sky blue");

        playerPreyType.addItem("apple");
        playerPreyType.addItem("banana");
        playerPreyType.addItem("cherry");
        playerPreyType.addItem("mouse");

        playerBoardColor.addItem("black");
        playerBoardColor.addItem("grey");
        playerBoardColor.addItem("violet");
        playerBoardColor.addItem("brown");
        playerBoardColor.addItem("periwinkle");


        applySettingChanges.setBounds(2 + 425,tileSize*14 + 2, tileSize*3 + 100,tileSize);
        applySettingChanges.setText("Apply Changes");
        applySettingChanges.setFont(new Font("Comic Sans",Font.BOLD,21));
        applySettingChanges.setFocusable(false);
        applySettingChanges.setHorizontalTextPosition(JButton.CENTER);
        applySettingChanges.setVerticalTextPosition(JButton.CENTER);
        applySettingChanges.setForeground(Color.white);
        applySettingChanges.setBackground(new Color(16, 145, 55));
        applySettingChanges.addActionListener(this);

        bot1NameCombobox.addItem("a_zhuchkov");
        bot1NameCombobox.addItem("anhsBot");
        bot1NameCombobox.addItem("SampleBot");
        bot1NameCombobox.addItem("tunaBot");
        bot1NameCombobox.addItem("v_smirnov");

        bot2NameCombobox.addItem("a_zhuchkov");
        bot2NameCombobox.addItem("anhsBot");
        bot2NameCombobox.addItem("SampleBot");
        bot2NameCombobox.addItem("tunaBot");
        bot2NameCombobox.addItem("v_smirnov");

    }


    public void paintStartScreen() {
        logo = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GUI/img/logo.png")));
        this.setIconImage(logo.getImage());

        labelContainer = new JLabel(StartScreenBackground);
        labelContainer.setSize(new Dimension(screenWidth, screenHeight));

        // paint all Buttons
        playButton.setBounds(tileSize*5,tileSize*3 + 50, tileSize*7,tileSize);
        playButton.setText("SINGLE PLAYER");
        playButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        playButton.setFocusable(false);
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.CENTER);
        playButton.setForeground(Color.white);
        playButton.setBackground(new Color(0,204,102));
        playButton.addActionListener(this);

        //Insets insets = this.getInsets();
        //Dimension size = playButton.getPreferredSize();


        settingsButton.setBounds(tileSize*5,tileSize*3 + 140, tileSize*7,tileSize);
        settingsButton.setText("SETTINGS");
        settingsButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        settingsButton.setFocusable(false);
        settingsButton.setHorizontalTextPosition(JButton.CENTER);
        settingsButton.setVerticalTextPosition(JButton.CENTER);
        settingsButton.setForeground(Color.white);
        settingsButton.setBackground(new Color(0,204,102));
        settingsButton.addActionListener(this);

        statisticsButton.setBounds(tileSize*5,tileSize*3 + 230, tileSize*7,tileSize);
        statisticsButton.setText("STATISTICS");
        statisticsButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        statisticsButton.setFocusable(false);
        statisticsButton.setHorizontalTextPosition(JButton.CENTER);
        statisticsButton.setVerticalTextPosition(JButton.CENTER);
        statisticsButton.setForeground(Color.white);
        statisticsButton.setBackground(new Color(0,204,102));
        statisticsButton.addActionListener(this);


        quitgameButton.setBounds(tileSize*5,tileSize*3 + 320, tileSize*7,tileSize);
        quitgameButton.setText("QUIT GAME");
        quitgameButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        quitgameButton.setFocusable(false);
        quitgameButton.setHorizontalTextPosition(JButton.CENTER);
        quitgameButton.setVerticalTextPosition(JButton.CENTER);
        quitgameButton.setForeground(Color.white);
        quitgameButton.setBackground(new Color(0,204,102));
        quitgameButton.addActionListener(this);


        infoButton.setBounds(2 + 5,tileSize*14 - 10, tileSize*3,tileSize);
        infoButton.setText("Credits");
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
        this.add(statisticsButton);
        this.add(quitgameButton);
        this.add(infoButton);

        this.add(labelContainer);      // insert background img at last

        //this.pack();
        this.setVisible(true);

        centreWindow(this);

    }

    public void paintStartScreen2() {
        this.setIconImage(logo.getImage());
        labelContainer = new JLabel(StartScreenBackground);
        labelContainer.setSize(new Dimension(screenWidth, screenHeight));

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
        this.add(statisticsButton);
        this.add(quitgameButton);
        this.add(infoButton);

        this.add(labelContainer);      // insert background img at last

        //this.pack();
        this.setVisible(true);

        //centreWindow(this);

    }


    public void paintSettingsFrame() {
        this.setTitle("SnakeAI Revolution/Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(screenWidth,screenHeight);
        this.setVisible(true);

//        applyChange.setBounds(tileSize*5,tileSize*3, tileSize*7,tileSize*1);
//        applyChange.setText("CHANGE!!!!");
//        applyChange.setFont(new Font("Comic Sans",Font.BOLD,25));
//        applyChange.setFocusable(false);
//        applyChange.setHorizontalTextPosition(JButton.CENTER);
//        applyChange.setVerticalTextPosition(JButton.CENTER);
//        applyChange.setForeground(Color.white);
//        applyChange.setBackground(new Color(0,204,102));
//        applyChange.addActionListener(this);
//
//
//        this.add(applyChange);
//
//        textSnake1Name.setBounds(50,50,120,30);
//        this.add(textSnake1Name);
//        textSnake1Type.setBounds(50,100,120,30);
//        this.add(textSnake1Type);
//        textSnake1Color.setBounds(50,150,120,30);
//        this.add(textSnake1Color);
//        textSnake2Name.setBounds(50,250,120,30);
//        this.add(textSnake2Name);
//        textSnake2Type.setBounds(50,300,120,30);
//        this.add(textSnake2Type);
//        textSnake2Color.setBounds(50,350,120,30);
//        this.add(textSnake2Color);


        gameDifficulty.setPreferredSize(new Dimension(200, 40));
        this.add(gameDifficulty);
        Insets insets = this.getInsets();
        Dimension size = gameDifficulty.getPreferredSize();
        gameDifficulty.setBounds(245 + insets.left, 142 + insets.top, size.width, size.height);
        gameDifficulty.addActionListener(this);


        playerColorCombobox.setPreferredSize(new Dimension(200, 40));
        //myButton.setBounds(50, 50, 100, 50);
        this.add(playerColorCombobox);

        playerColorCombobox.setBounds(245 + insets.left, 255 + insets.top, size.width, size.height);
        playerColorCombobox.addActionListener(e -> {
            //System.out.println(playerColorCombobox.getSelectedItem());
        });


        playerPreyType.setPreferredSize(new Dimension(200, 40));
        //myButton.setBounds(50, 50, 100, 50);
        this.add(playerPreyType);

        playerPreyType.setBounds(586 + insets.left, 255 + insets.top, size.width, size.height);
        playerPreyType.addActionListener(e -> {
            //System.out.println(playerPreyType.getSelectedItem());
        });


        playerBoardColor.setPreferredSize(new Dimension(200, 40));
        //myButton.setBounds(50, 50, 100, 50);
        this.add(playerBoardColor);

        playerBoardColor.setBounds(586 + insets.left, 142 + insets.top, size.width, size.height);
        playerBoardColor.addActionListener(e -> {
            //System.out.println(playerBoardColor.getSelectedItem());
        });

        // botVsbot Settings
        bot1NameCombobox.setPreferredSize(new Dimension(200, 40));
        this.add(bot1NameCombobox);

        bot1NameCombobox.setBounds(242 + insets.left, 430 + insets.top, size.width, size.height);
        bot1NameCombobox.addActionListener(e -> {
            //System.out.println(playerColorCombobox.getSelectedItem());
        });

        bot2NameCombobox.setPreferredSize(new Dimension(200, 40));
        this.add(bot2NameCombobox);

        bot2NameCombobox.setBounds(541 + insets.left, 430 + insets.top, size.width, size.height);
        bot2NameCombobox.addActionListener(e -> {
            //System.out.println(playerColorCombobox.getSelectedItem());
        });


        botNumberofTournaments.setPreferredSize(new Dimension(200, 40));
        this.add(botNumberofTournaments);
        botNumberofTournaments.setBounds(593 + insets.left, 553 + insets.top, size.width, size.height);



        applySettingChanges.addActionListener(e -> {
            try {
                FileWriter myWriter = new FileWriter("./src/Game/gameSettings.txt");
                myWriter.write("gameDifficulty\n");
                myWriter.write(gameDifficulty.getSelectedItem() + "\n");
                myWriter.write("boardColor\n");
                myWriter.write(playerBoardColor.getSelectedItem() + "\n");
                myWriter.write("snakeColor\n");
                myWriter.write(playerColorCombobox.getSelectedItem() + "\n");
                myWriter.write("preyType\n");
                myWriter.write( playerPreyType.getSelectedItem() + "\n");
                myWriter.write("bot01\n");
                myWriter.write( bot1NameCombobox.getSelectedItem() + "\n");
                myWriter.write("bot02\n");
                myWriter.write( bot2NameCombobox.getSelectedItem() + "\n");
                myWriter.write("numberOfTournaments\n");
                myWriter.write( botNumberofTournaments.getText().isEmpty() ? "5" : botNumberofTournaments.getText());
                myWriter.close();
                System.out.println("Successfully Change.");
                //JOptionPane.showMessageDialog(null, "New Changes are applied!", "Game Settings", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException fileEx) {
                System.out.println("An error occurred.");
                fileEx.printStackTrace();
            }
        });


        labelContainer = new JLabel(SettingBackground);
        labelContainer.setSize(new Dimension(screenWidth, screenHeight));

        this.add(applySettingChanges);
        this.add(backButton);

        this.add(labelContainer);      // insert background img at last

        //this.pack();
        this.setVisible(true);

        //centreWindow(this);
    }

    public void paintLeaderboardFrame() {
        openLeaderboard();

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("./src/Game/score.csv"));
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
            l.setFont(new Font("Arial", Font.BOLD, 20));
            l.setForeground(Color.WHITE);
            l.setBounds(80*(i+1) + 100, 30*rowCount + 150, 600,100);
            labelContainer.add(l);
            this.add(labelContainer);
        }
    }

    private void openLeaderboard() {
        this.setTitle("SnakeAI Revolution/Leaderboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(screenWidth,screenHeight);
        //this.setVisible(true);

        labelContainer = new JLabel(StatisticsBackground);
        labelContainer.setSize(new Dimension(screenWidth, screenHeight));

        //statisticsTable.setBounds(200, 200, 600, 200);


//        this.add(statisticsTable);
        this.add(backButton);
        this.add(labelContainer);      // insert background img at last

        //this.pack();
        this.setVisible(true);

    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            this.setVisible(false);
            this.dispose();
            try {
                new GameFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        } else if (e.getSource() == settingsButton) {
            //GUI.SettingsFrame myWindow = new GUI.SettingsFrame();
            this.getContentPane().removeAll();
            this.validate();
            this.repaint();
            this.paintSettingsFrame();
        } else if (e.getSource() == statisticsButton) {
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
            JOptionPane.showMessageDialog(null, "Snake Revolution\nversion 1.0.0\n\nAuthors:\nNguyen Phuoc Bao Minh\nNguyen Vu Doanh Khoa\nVu Hoang Tuan Anh\nBa Nguyen Quoc Anh", "About us", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource() == backButton) {
            this.getContentPane().removeAll();
            this.validate();
            this.repaint();
            this.paintStartScreen2();
        }

    }

    @Override
    public void run() {
        paintStartScreen();
    }
}
