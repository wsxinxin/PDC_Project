/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.window;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

import game.entity.Entity;
import game.entity.Player;
import game.main.Config;
import game.main.GameWindow;
import game.tile.AssetSetter;
import game.tile.CollisionChecker;
import game.tile.TileManager;
import game.tile.UI;
import game.database.SaveLoad;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;

public class GameWorldPanel extends JPanel implements Runnable, KeyListener {
   
    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 20; //results in a 16:9 ratio
    public final int maxScreenRow = 12; //results in a 16:9 ratio
    public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    // WORLD SETTINGS 
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    // FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    
    // FPS = Frames Per Second
    int FPS = 60;
    
    // SYSTEM
    public TileManager tileM  = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker cChecker = new CollisionChecker(this);;
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Config config = new Config(this);
    SaveLoad saveLoad = new SaveLoad(this);
    Thread gameThread;
    
    // Entities and Objects
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[20];
    public Entity monster[] = new Entity[5];
    ArrayList<Entity> entityList = new ArrayList<>();
    
    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int characterState = 3;
    public final int optionsState = 4;
    public final int gameOverState = 6;
    
    // Panel Constructor
    public GameWorldPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the dimension size of the game screen
        this.setBackground(Color.black); //set the colors of the screeen
        this.setDoubleBuffered(true); // Buffer
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    // setup what will be created the the game world 
    public void setupGame() {
        aSetter.setObject();
        aSetter.setMonster();
        gameState = titleState;
        
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics(); 
        
        if (fullScreenOn == true) {
            setFullScreen();
        }
    }
    // retry option if the game over screen appears
    public void retry() {
        
        player.setDefaultPositions();
        player.restore();
        aSetter.setMonster();
    }
    // quit option if the game over screen appears will restart the whole game
    public void restart() {
        
        player.setDefaultValues();
        player.setItems();
        aSetter.setObject();
        aSetter.setMonster();  
    }
    // Set Full Screen Resolution
    public void setFullScreen() {
        
        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(GameWindow.frame);
        
        // GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = GameWindow.frame.getWidth();
        screenHeight2 = GameWindow.frame.getHeight();
    }
    // Start the character and monster threads
    public void startGameThread() {
        gameThread = new Thread(this); // instanciate the thread
        gameThread.start(); //automatically calls run method
    }
    // Run the threads
    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; //0.016667 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while (gameThread != null) {
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1) {
                update();
                drawToTempScreen(); // draw everything to the buffered image
                drawToScreen(); // draw the buffered image to the screen
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000) {
                //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    // UPDATE THE IMAGES IN THE GAME
    public void update() {
        // set the game state to play state 
        if (gameState == playState) {
            //PLAYER
            player.update();
            
            //MONSTER
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == true && monster[i].dying == false) {
                        monster[i].update();
                    }
                    if (monster[i] != monster[4] && monster[i].alive == false) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                    if (monster[i] == monster[4] && monster[4].alive == false) {
                        player.bossDefeated();
                        monster[4] = null;
                    }
                }
            }
        } 
        if (gameState == pauseState) {
            //nothing
        }
    }
    
    // Assume the function of draw paintComponent
    public void drawToTempScreen() {
        
        // TITLE SCREEN
        if (gameState == titleState) {
           ui.draw(g2); 
        }
        // OTHERS
        else {
            // TILE
            tileM.draw(g2);
        
            // ADD ENTITIES TO THE LIST
            entityList.add(player);
        
            for(int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
        
            for(int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
        
            // SORT THE LIST
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
                
            });
            
            // DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            // EMPTY EMTITY LIST
            entityList.clear();
            
            // UI 
            ui.draw(g2);
        }
    }
    
    // draw to the screen method
    public void drawToScreen() {
        
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }
      
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }  
}
