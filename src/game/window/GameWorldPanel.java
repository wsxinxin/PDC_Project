/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author chris
 */
public class GameWorldPanel extends JPanel implements Runnable,KeyListener {
   
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
    
    GameMenuPanel gmp;
    KeyHandler keyH;
    Thread gameThread; 
    
    // set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    public GameWorldPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the dimension size of the game screen
        this.setBackground(Color.black); //set the colors of the screeen
        this.setDoubleBuffered(true); // Buffer
        // Initialize the KeyHandler
        keyH = new KeyHandler(this);
        // Add the KeyHandler as a key listener
        addKeyListener(keyH);
        this.setFocusable(true);
        
        /*// Create an InputMap and ActionMap for key bindings
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        // Bind the Escape key to a custom action
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "showMenu");
        actionMap.put("showMenu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeGameMenu();
            }
        });*/
        // Add the KeyListener to the GameWorldPanel
        addKeyListener(this);
        setFocusable(true);
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
        
        int code = ke.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE) {
            executeGameMenu(); // Call a method in your GameMenuPanel class to execute the menu
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }  
}