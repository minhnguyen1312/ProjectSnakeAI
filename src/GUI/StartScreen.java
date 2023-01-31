package GUI;

import Game.GameFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import javax.swing.*;
import java.util.LinkedList;


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
    private final ImageIcon SettingBackground = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GUI/img/settingsv5.jpg")));
    private final ImageIcon StatisticsBackground = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GUI/img/gameStatisticsv3.png")));
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

    // botVsBot
    private final static Combobox<String> bot1NameCombobox = new Combobox<>();
    private final static Combobox<String> bot2NameCombobox = new Combobox<>();
    private final static Combobox<String> bot1ColorCombobox = new Combobox<>();
    private final static Combobox<String> bot2ColorCombobox = new Combobox<>();
    private final static JTextField botNumberofTournaments = new JTextField();


    // Leaderboard
    String row = "";

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    // ============================== METHODS ===================================== //

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
        gameDifficulty.addItem("Ultra");

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
        playerBoardColor.addItem("gray");
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

        bot1ColorCombobox.addItem("sky blue");
        bot1ColorCombobox.addItem("violet");
        bot1ColorCombobox.addItem("lime green");
        bot1ColorCombobox.addItem("");

        bot2ColorCombobox.addItem("white");
        bot2ColorCombobox.addItem("gray");
        bot2ColorCombobox.addItem("lavender");
        bot2ColorCombobox.addItem("yellow");

        playButton.setBounds(tileSize*5,tileSize*3 + 50, tileSize*7,tileSize);
        playButton.setText("SINGLE PLAYER");
        playButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        playButton.setFocusable(false);
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.CENTER);
        playButton.setForeground(Color.white);
        playButton.setBackground(new Color(0,204,102));
        playButton.addActionListener(this);

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

    }


    public void paintStartScreen() {
        logo = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GUI/img/logo.png")));
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
        this.add(labelContainer);

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

        this.add(labelContainer);
        //centreWindow(this);

    }


    public void paintSettingsFrame() {
        this.setTitle("SnakeAI Revolution/Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(screenWidth,screenHeight);
        this.setVisible(true);


        gameDifficulty.setPreferredSize(new Dimension(200, 40));
        this.add(gameDifficulty);
        Insets insets = this.getInsets();
        Dimension size = gameDifficulty.getPreferredSize();
        gameDifficulty.setBounds(245 + insets.left, 142 + insets.top, size.width, size.height);


        playerColorCombobox.setPreferredSize(new Dimension(200, 40));
        this.add(playerColorCombobox);
        playerColorCombobox.setBounds(245 + insets.left, 255 + insets.top, size.width, size.height);
        playerColorCombobox.addActionListener(this);


        playerPreyType.setPreferredSize(new Dimension(200, 40));
        this.add(playerPreyType);
        playerPreyType.setBounds(586 + insets.left, 255 + insets.top, size.width, size.height);


        playerBoardColor.setPreferredSize(new Dimension(200, 40));

        this.add(playerBoardColor);
        playerBoardColor.setBounds(586 + insets.left, 142 + insets.top, size.width, size.height);


        // botVsbot Settings
        bot1NameCombobox.setPreferredSize(new Dimension(200, 40));
        this.add(bot1NameCombobox);
        bot1NameCombobox.setBounds(242 + insets.left, 428 + insets.top, size.width, size.height);


        bot2NameCombobox.setPreferredSize(new Dimension(200, 40));
        this.add(bot2NameCombobox);
        bot2NameCombobox.setBounds(541 + insets.left, 428 + insets.top, size.width, size.height);

        bot1ColorCombobox.setPreferredSize(new Dimension(200, 40));
        this.add(bot1ColorCombobox);
        bot1ColorCombobox.setBounds(242 + insets.left, 512 + insets.top, size.width, size.height);

        bot2ColorCombobox.setPreferredSize(new Dimension(200, 40));
        this.add(bot2ColorCombobox);
        bot2ColorCombobox.setBounds(541 + insets.left, 512 + insets.top, size.width, size.height);



        botNumberofTournaments.setPreferredSize(new Dimension(150, 40));
        this.add(botNumberofTournaments);
        botNumberofTournaments.setBounds(593 + insets.left, 577 + insets.top, 150, 40);


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
        this.add(labelContainer);
        this.setVisible(true);

        //centreWindow(this);
    }


    public int countLineNumberCSV(String filename) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        }
    }


    public void paintLeaderboardFrame() throws IOException {
        openLeaderboard();
        int numberOfRows = 15;
//        int numberOfRows = countLineNumberCSV("./src/Game/score.csv");
        System.out.println("The number of lines of the csv score file: " + numberOfRows);

        LinkedList<String> scoreList = new LinkedList<String>();

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("./src/Game/highscore.csv"));
            for (int rowCounter = 1 ; rowCounter <= numberOfRows && (row = csvReader.readLine()) != null ; rowCounter++) {
                String[] data = row.split(",");
                drawRow_leaderboard(data, rowCounter);
                System.out.println(rowCounter + " --- " + data[1]);
                scoreList.add(data[1]);         // playerName, Score, GameDifficulty, PreyType, Date
            }




            for (String iterator : scoreList) {
                System.out.println(iterator);
            }


            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            BufferedReader csvReader = new BufferedReader(new FileReader("./src/Game/score.csv"));
//            for (int rowCounter = 1 ; rowCounter <= 10 && (row = csvReader.readLine()) != null ; rowCounter++) {
//                String[] data = row.split(",");
//                drawRow_leaderboard(data, rowCounter);
//            }
//            csvReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
        this.setVisible(true);

        labelContainer = new JLabel(StatisticsBackground);
        labelContainer.setSize(new Dimension(screenWidth, screenHeight));


        this.add(backButton);
        this.add(labelContainer);
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
            try {
                this.paintLeaderboardFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
