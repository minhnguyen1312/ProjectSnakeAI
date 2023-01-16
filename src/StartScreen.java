import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


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


    StartScreen() {

        playButton.setBounds(tileSize*5,tileSize*3, tileSize*7,tileSize*1);
        playButton.setText("PLAY");
        playButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        playButton.setFocusable(false);
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.CENTER);
        playButton.setForeground(Color.white);
        playButton.setBackground(new Color(0,204,102));
        playButton.addActionListener(this);

        settingsButton.setBounds(tileSize*5,tileSize*5, tileSize*7,tileSize*1);
        settingsButton.setText("SETTINGS");
        settingsButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        settingsButton.setFocusable(false);
        settingsButton.setHorizontalTextPosition(JButton.CENTER);
        settingsButton.setVerticalTextPosition(JButton.CENTER);
        settingsButton.setForeground(Color.white);
        settingsButton.setBackground(new Color(0,204,102));
        settingsButton.addActionListener(this);

        leaderboardButton.setBounds(tileSize*5,tileSize*7, tileSize*7,tileSize*1);
        leaderboardButton.setText("LEADERBOARD");
        leaderboardButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        leaderboardButton.setFocusable(false);
        leaderboardButton.setHorizontalTextPosition(JButton.CENTER);
        leaderboardButton.setVerticalTextPosition(JButton.CENTER);
        leaderboardButton.setForeground(Color.white);
        leaderboardButton.setBackground(new Color(0,204,102));
        leaderboardButton.addActionListener(this);


        quitgameButton.setBounds(tileSize*5,tileSize*9, tileSize*7,tileSize*1);
        quitgameButton.setText("QUIT GAME");
        quitgameButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        quitgameButton.setFocusable(false);
        quitgameButton.setHorizontalTextPosition(JButton.CENTER);
        quitgameButton.setVerticalTextPosition(JButton.CENTER);
        quitgameButton.setForeground(Color.white);
        quitgameButton.setBackground(new Color(0,204,102));
        quitgameButton.addActionListener(this);


        infoButton.setBounds(2,tileSize*14, tileSize*3,tileSize*1);
        infoButton.setText("About us");
        infoButton.setFont(new Font("Comic Sans",Font.BOLD,21));
        infoButton.setFocusable(false);
        infoButton.setHorizontalTextPosition(JButton.CENTER);
        infoButton.setVerticalTextPosition(JButton.CENTER);
        infoButton.setForeground(Color.white);
        infoButton.setBackground(Color.black);
        infoButton.addActionListener(this);




        this.setTitle("SnakeAI");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(screenWidth,screenHeight);
        this.setVisible(true);


        ImageIcon logo = new ImageIcon("logo.png");
        this.setIconImage(logo.getImage());
        this.getContentPane().setBackground(new Color(102,204,0));
        this.add(playButton);
        this.add(settingsButton);
        this.add(leaderboardButton);
        this.add(quitgameButton);
        this.add(infoButton);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            Frame myWindow = new Frame();
        } else if (e.getSource() == settingsButton) {
            SettingsFrame myWindow = new SettingsFrame();
        } else if (e.getSource() == leaderboardButton) {
            LeaderboardFrame myWindow = new LeaderboardFrame();
        } else if (e.getSource() == quitgameButton) {
            this.dispose();
        } else if (e.getSource() == infoButton) {

        }

    }
}
