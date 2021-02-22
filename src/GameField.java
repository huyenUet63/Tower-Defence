import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;

public class GameField extends JPanel implements Runnable {
    private boolean clickingStart = false;
    private boolean clickingPause = false;
    private boolean clickingExit = false;
    private boolean clickingTryAgain = false;

    private ImageIcon startIcon = new ImageIcon("res/newGameButton1.png");
    private ImageIcon tryAgainIcon = new ImageIcon("res/restartButton.png");
    private ImageIcon exitIcon = new ImageIcon("res/quitButton1.png");

    private JButton startButton = new JButton(startIcon);
    private JButton tryAgainButton = new JButton(tryAgainIcon);
    private JButton exitButton = new JButton(exitIcon);
    private JButton pauseButton = new JButton("Pause");
    
    
    public Thread thread = new Thread(this);
    
    public static Image[] set_ground = new Image[100];
    public static Image[] set_air = new Image[100];
    public static Image[] set_cell = new Image[100];
    public static Image[] set_enemy = new Image[100];
    public static Image[] set_bullet = new Image[100];
    
    public static int myWidth, myHeight;
    public static int coinage = 30, health = 5;
    
    public static int killed = 0, killsToWin = 0, level = 1, maxlevel = 3;
    public static int winTime = 4000, winFrame = 0;
    public static int num = 0;
   
    public static boolean isFirst = true; //first time running  
    public static boolean isDebug = true;
    public static boolean isWin = false;
    
    public static Point mouse = new Point(0, 0);

    public static Save1 save;
    public static Store1 store;
    public static Map map;
   
    public static ArrayList<Enemy1> enemy;
    public static Enemy1[] enemies = new NormalEnemy[100];
    public static Enemy1[] enemies1 = new TankerEnemy[100];
    public static Enemy1[] enemies2 = new SmallEnemy[100];
    public static Enemy1[] enemies3 = new BossEnemy[100];

    public static Bullet[] bullet = new Bullet[100];
    
    public GameField(GameStage stage) {
        stage.addMouseListener(new KeyHandle());
        stage.addMouseMotionListener(new KeyHandle());

        thread.start();
        //setBackground(Color.PINK);

    }
          
    public static void hasWon() {
        if (killed == killsToWin) {
            isWin = true;
            killed = 0;
            level += 1;
        }
    }

    public void define() {
        map = new Map();        
        save = new Save1();
        store = new Store1();

        for (int i=0; i<set_ground.length; i++) {
            set_ground[i] = new ImageIcon("res/set_ground.png").getImage();
            set_ground[i] = createImage(new FilteredImageSource(set_ground[i].getSource(), new CropImageFilter(0, 26 * i, 26, 26)));
        }

        for (int i=0; i<set_air.length; i++) {
            set_air[i] = new ImageIcon("res/set_air2.png").getImage();
            set_air[i] = createImage(new FilteredImageSource(set_air[i].getSource(), new CropImageFilter(0, 26*i, 26, 26)));
        }
  
        set_air[2] = new ImageIcon("res/tower1.png").getImage();
        set_air[3] = new ImageIcon("res/tower2.png").getImage();
        set_air[4] = new ImageIcon("res/tower3.png").getImage();

        set_ground[4] = new ImageIcon("res/cell.png").getImage();
        
        set_cell[0] = new ImageIcon("res/cell.png").getImage();
        set_cell[1] = new ImageIcon("res/like.png").getImage();
        set_cell[2] = new ImageIcon("res/dollar.png").getImage();
        //for (int i=0; i<4; i++){ set_enemy[i] = new ImageIcon("res/enemy" +  i+ ".png").getImage();}
        set_enemy[0] = new ImageIcon("res/like.png").getImage();
        set_enemy[1] = new ImageIcon("res/coin.png").getImage();
        set_enemy[2] = new ImageIcon("res/dollar.png").getImage();
        set_enemy[3] = new ImageIcon("res/Bullet.png").getImage();

        set_bullet[0] = new ImageIcon("res/like.png").getImage();
        //save.loadSave(new File("save/level1.utl"));
        save.loadSave(new File("save/level" + level + ".utl"));

        for (int i=0; i<enemies.length; i++) {
            enemies[i] = new NormalEnemy();
        }
        for (int i=0; i<enemies1.length; i++) {
            enemies1[i] = new TankerEnemy();
        }
        for (int i=0; i<enemies2.length; i++) {
            enemies2[i] = new SmallEnemy();
        }
        for (int i=0; i<enemies3.length; i++) {
            enemies3[i] = new BossEnemy();
        }
        enemy = new ArrayList<>();
     
    }

    @Override
    public void paintComponent(Graphics g) {
        if (isFirst) {
            myWidth = getWidth();
            myHeight = getHeight();
            g.drawImage(new ImageIcon("res/imageMenu.png").getImage(), 0, 0, 840, 610, this);
        startButton.setBounds(150, 400, 250, 200);
        exitButton.setBounds(435, 400, 250, 200);
        add(startButton);
        add(exitButton);

        startButton.addActionListener(e -> {
            clickingStart = true;
            startButton.setVisible(false);
            exitButton.setVisible(false);
            define();
            isFirst = false;
        });

        exitButton.addActionListener(e -> {
            clickingExit = true;
            System.exit(0);
        });
            define();
            isFirst = false;
        }

        g.setColor(new Color(70, 70, 70));
        //g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(0, 0, 0));
        g.drawLine(map.block[0][0].x-1, 0, map.block[0][0].x-1, map.block[map.worldHeight-1][0].y+map.blockSize);
        g.drawLine(map.block[0][map.worldWidth-1].x+map.blockSize, 0, map.block[0][map.worldWidth-1].x+map.blockSize, map.block[map.worldHeight-1][0].y+map.blockSize);
        g.drawLine(map.block[0][0].x, map.block[map.worldHeight-1][0].y+map.blockSize, map.block[0][map.worldWidth-1].x+map.blockSize, map.block[map.worldHeight-1][0].y+map.blockSize);
        // ve vien bao quanh map
        map.draw(g);

//        for (int i=0; i<enemies.length; i++) {
//            if (enemies[i].inGame) {
//                enemies[i].draw(g);
//            }
//        }
//        
//        for (int i=0; i<enemies1.length; i++) {
//            if (enemies1[i].inGame) {
//                enemies1[i].draw(g);
//            }
//        }
        
        for (int i=0; i<enemy.size(); i++) {
            enemy.get(i).draw(g);
        }
   
        store.draw(g);
        
        if (health < 1) {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, myWidth, myHeight);
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
            g.drawString("Game Over...", 15, 25);
            // sau game over them 'try again' vao
        }
         
        if (isWin) {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
            if (level == maxlevel) {
                g.drawString("Clear Game!", 15, 25);
            }
            else g.drawString("You won! Please wait for the next level...", 15, 25);
        }
               
    }
    
    public int spawnTime = 2000, spawnFrame = 0;
    public void enemySpawner() {
            if(spawnFrame >= spawnTime) {
                //if (num > 5) Value1.enemyGhost = 1;
//                Enemy1 e;
//                switch (Value1.enemyGhost) {
//                    case 0:
//                        break;
//                    case 1:
//                        break
//                }
                
                for (int i=0; i<enemies.length; i++) {
                    if(!enemies[i].inGame) {
                        enemies[i].spawnEnemy(0);
                        enemy.add(enemies[i]);                               
                        break;
                    }
                }
                  for (int i=0; i<enemies1.length; i++) {
                    if(!enemies1[i].inGame) {
                        enemies1[i].spawnEnemy(1);
                        enemy.add(enemies1[i]);                               
                        break;
                    }
                }
                  for (int i=0; i<enemies.length; i++) {
                    if(!enemies[i].inGame) {
                        enemies2[i].spawnEnemy(2);
                        enemy.add(enemies2[i]);                               
                        break;
                    }
                }
                  for (int i=0; i<enemies1.length; i++) {
                    if(!enemies3[i].inGame) {
                        enemies3[i].spawnEnemy(3);
                        enemy.add(enemies1[i]);                               
                        break;
                    }
                  }
                spawnFrame = 0;
            } else {
                spawnFrame += 1;
            }
    }
  

    public void run(){
        while(true) {
            if (!isFirst && health > 0 && !isWin) {
                map.physic();
                enemySpawner();
//                for (int i=0; i<enemies.length; i++) {
//                    if (enemies[i].inGame) {
//                        enemies[i].physic();
//                    }
//                }
for (int i=0; i<enemy.size(); i++)
    if (enemy.get(i).inGame){
        enemy.get(i).physic();
    }
    else enemy.remove(i);
                
            }
                else if (!isFirst && !isWin) {
                if (winFrame >= winTime) {
                    level = 1;
                    define();
                    health = 1;
                    coinage = 20; 
                    winFrame = 0;
                }
                else {   
                   winFrame += 1; 
                }
            }
            else {
                if (isWin) {
                    if (winFrame >= winTime) {                       
                        if (level == maxlevel) {
                            System.exit(0);
                        }
                        else {
                            level += 1;
                            define();  
                            isWin = false;                                                      
                        } 
                        winFrame = 0;
                    }
                    else {
                        winFrame += 1;
                    }
                }
            }
            repaint();
            try {
                Thread.sleep(1);
           } catch (Exception e) {}
        }
    }

}
