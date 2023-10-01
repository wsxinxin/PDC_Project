/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.window;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class GameWorldPanel extends JPanel implements Runnable{
   
    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16; //results in a 4:3 ratio
    final int maxScreenRow = 12; //results in a 4:3 ratio
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    // FPS = Frames Per Second
    int FPS = 60;
            
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; 
    
    // set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    public GameWorldPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the dimension size of the game screen
        this.setBackground(Color.black); //set the colors of the screeen
        this.setDoubleBuffered(true); // Buffer
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this); // instanciate the thread
        gameThread.start(); //automatically calls run method
    }
    
    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; //0.016667 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while(gameThread != null) {
                       
            // 1st UPDATE: update information such as character positions
            update(); // invokes update method
            
            // 2nd DRAW: draw the screen with update information
            repaint(); // invokes paintComponent method
            
            
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                
                Thread.sleep((long)remainingTime);
                
                nextDrawTime += drawInterval;
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void update() {
        
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        }
        else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }
    
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g; // convert Graphics java claas to Graphics2D class which has more functions
        
        g2.setColor(Color.white);
        
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        
        g2.dispose();
    }
}
