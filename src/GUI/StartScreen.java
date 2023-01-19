package GUI;

import DefaultBotFrameWork.SnakesUIMain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;


public class StartScreen extends JFrame implements ActionListener, Runnable {

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
    JButton backButton = new JButton();
    JButton applySettingChanges = new JButton();

    private ImageIcon StartScreenBackground = new ImageIcon(this.getClass().getResource("/GUI/img/backgrv3.jpg"));
    private ImageIcon SettingBackground = new ImageIcon(this.getClass().getResource("/GUI/img/settingsv3.png"));;
    private ImageIcon logo;
    private JLabel labelContainer;

    // Settings GUI.Frame
    static JButton applyChange = new JButton();
    //    static JPanel snake1 = new JPanel();
    //    static JPanel snake2 = new JPanel();

    // setting sections attributes

    // single player
    private static JTextField playerNameTextField = new JTextField();
    private static Combobox<String> playerColorCombobox = new Combobox<>();
    private static Combobox<String> playerBoardColor = new Combobox<>();
    private static Combobox<String> playerPreyType = new Combobox<>();


    private static Combobox<String> bot1NameCombobox = new Combobox<>();
    private static Combobox<String> bot2NameCombobox = new Combobox<>();
    private static JTextField botNumberofTournaments = new JTextField();






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
        //paintStartScreen();

        backButton.setBounds(2 + 680,tileSize*14 + 2, tileSize*3,tileSize*1);
        backButton.setText("Back");
        backButton.setFont(new Font("Comic Sans",Font.BOLD,21));
        backButton.setFocusable(false);
        backButton.setHorizontalTextPosition(JButton.CENTER);
        backButton.setVerticalTextPosition(JButton.CENTER);
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.black);
        backButton.addActionListener(this);

        playerColorCombobox.addItem("red");
        playerColorCombobox.addItem("blue");
        playerColorCombobox.addItem("yellow");
        playerColorCombobox.addItem("green");
        playerColorCombobox.addItem("white");

        playerPreyType.addItem("apple");
        playerPreyType.addItem("banana");
        playerPreyType.addItem("blueberry");
        playerPreyType.addItem("mouse");

        playerBoardColor.addItem("black");
        playerBoardColor.addItem("grey");
        playerBoardColor.addItem("violet");
        playerBoardColor.addItem("brown");
        playerBoardColor.addItem("periwinkle");


        applySettingChanges.setBounds(2 + 425,tileSize*14 + 2, tileSize*3 + 100,tileSize*1);
        applySettingChanges.setText("Apply Changes");
        applySettingChanges.setFont(new Font("Comic Sans",Font.BOLD,21));
        applySettingChanges.setFocusable(false);
        applySettingChanges.setHorizontalTextPosition(JButton.CENTER);
        applySettingChanges.setVerticalTextPosition(JButton.CENTER);
        applySettingChanges.setForeground(Color.white);
        applySettingChanges.setBackground(new Color(16, 145, 55));
        applySettingChanges.addActionListener(this);

        bot1NameCombobox.addItem("a_zhuckov");
        bot1NameCombobox.addItem("anhsBot");
        bot1NameCombobox.addItem("johndoe");
        bot1NameCombobox.addItem("tuna");
        bot1NameCombobox.addItem("v_smirnov");

        bot2NameCombobox.addItem("a_zhuckov");
        bot2NameCombobox.addItem("anhsBot");
        bot2NameCombobox.addItem("johndoe");
        bot2NameCombobox.addItem("tuna");
        bot2NameCombobox.addItem("v_smirnov");

    }

    public void paintFrame() {
        //SnakesUIMain newFrame = new SnakesUIMain();
//        try {
//            //String[] args = "";
//            SnakesUIMain.main(null);
//        } catch (InterruptedException | IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            System.err.print("Error occurred!");
//        }
    }

    public void paintStartScreen() {
        logo = new ImageIcon(this.getClass().getResource("/GUI/img/logo.png"));
        this.setIconImage(logo.getImage());

        labelContainer = new JLabel(StartScreenBackground);
        labelContainer.setSize(new Dimension(screenWidth, screenHeight));

        // paint all Buttons
        playButton.setBounds(tileSize*5,tileSize*3 + 50, tileSize*7,tileSize*1);
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
        this.add(leaderboardButton);
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




        playerNameTextField.setPreferredSize(new Dimension(200, 40));
        this.add(playerNameTextField);
        Insets insets = this.getInsets();
        Dimension size = playerNameTextField.getPreferredSize();
        playerNameTextField.setBounds(245 + insets.left, 142 + insets.top, size.width, size.height);


        playerColorCombobox.setPreferredSize(new Dimension(200, 40));
        //myButton.setBounds(50, 50, 100, 50);
        this.add(playerColorCombobox);

        playerColorCombobox.setBounds(245 + insets.left, 255 + insets.top, size.width, size.height);
        playerColorCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(playerColorCombobox.getSelectedItem());
            }
        });


        playerPreyType.setPreferredSize(new Dimension(200, 40));
        //myButton.setBounds(50, 50, 100, 50);
        this.add(playerPreyType);

        playerPreyType.setBounds(586 + insets.left, 255 + insets.top, size.width, size.height);
        playerPreyType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(playerPreyType.getSelectedItem());
            }
        });


        playerBoardColor.setPreferredSize(new Dimension(200, 40));
        //myButton.setBounds(50, 50, 100, 50);
        this.add(playerBoardColor);

        playerBoardColor.setBounds(586 + insets.left, 142 + insets.top, size.width, size.height);
        playerBoardColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(playerBoardColor.getSelectedItem());
            }
        });

        // botVsbot Settings


        applySettingChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    FileWriter myWriter = new FileWriter("gameSettings.txt");
                    myWriter.write("playerName\n");
                    myWriter.write(playerNameTextField.getText() + "\n");
                    myWriter.write("boardColor\n");
                    myWriter.write(playerBoardColor.getSelectedItem() + "\n");
                    myWriter.write("snakeColor\n");
                    myWriter.write(playerColorCombobox.getSelectedItem() + "\n");
                    myWriter.write("preyType\n");
                    myWriter.write((String) playerPreyType.getSelectedItem());
                    myWriter.close();
                    System.out.println("Successfully Change.");
                } catch (IOException fileEx) {
                    System.out.println("An error occurred.");
                    fileEx.printStackTrace();
                }
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

        labelContainer = new JLabel(SettingBackground);
        labelContainer.setSize(new Dimension(screenWidth, screenHeight));

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
            //paintFrame();


//            try {
//                //String[] args = "";
//                SnakesUIMain.runBot();
//            } catch (InterruptedException | IOException | NoSuchMethodException | InstantiationException | IllegalAccessException |
//                     InvocationTargetException e1) {
//                System.err.print("Error occurred!");
//            }
            SnakesUIMain.runningThread = false;
            Runtime rt = Runtime.getRuntime();
            try {
                Process pr = rt.exec("cmd /c javac /DefaultBotFrameWork/SnakesUIMain.java & java SnakesUIMain");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


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
