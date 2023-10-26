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
import game.tile.AssetSetter;
import game.tile.CollisionChecker;
import game.tile.TileManager;
import game.tile.UI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;

public class GameWorldPanel extends JPanel implements Runnable,KeyListener {
   
    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16; //results in a 4:3 ratio
    public final int maxScreenRow = 12; //results in a 4:3 ratio
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    // WORLD SETTINGS 
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    // FPS = Frames Per Second
    int FPS = 60;
    
    // SYSTEM
    GameMenuPanel gmp;
    public TileManager tileM  = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker cChecker = new CollisionChecker(this);;
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    // Entities and Objects
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10];
    public Entity monster[] = new Entity[5];
    ArrayList<Entity> entityList = new ArrayList<>();
    
    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int characterState = 3;
    
    public GameWorldPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the dimension size of the game screen
        this.setBackground(Color.black); //set the colors of the screeen
        this.setDoubleBuffered(true); // Buffer
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame() {
        aSetter.setObject();
        aSetter.setMonster();
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this); // instanciate the thread
        gameThread.start(); //automatically calls run method
    }
    
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
                repaint();
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
    
    public void update() {
        
        if (gameState == playState) {
            //PLAYER
            player.update();
            
            //MONSTER
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive = true && monster[i].dying == false) {
                        monster[i].update();
                    }
                    if (monster[i].alive = false) {
                        monster[i] = null;
                    }
                }
            }
        } 
        if (gameState == pauseState) {
            //nothing
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g; // convert Graphics java claas to Graphics2D class which has more functions
        
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
        
        // SORT
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
        
        g2.dispose();
    }
    
    public void executeGameMenu() {
        // Create a JFrame to display the menu panel
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        gmp = new GameMenuPanel(frame);
        frame.add(gmp);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);   
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