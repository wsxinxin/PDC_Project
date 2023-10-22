/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.window;

import game.entity.Monster;
import game.entity.Player;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * GameBattlePanel
 * A panel for the game battle screen with a background image.
 */
public class GameBattlePanel extends JPanel {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16; // results in a 4:3 ratio
    final int maxScreenRow = 12; // results in a 4:3 ratio
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // Background Image
    public BufferedImage image;
    public BufferedImage playerImg;
    // Constructor for GameBattlePanel
    public GameBattlePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        
        try {
        // Load the background image (replace "image_path" with the actual path to your image file)
        image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/Arena1.png"));
        playerImg = ImageIO.read(getClass().getResourceAsStream("/playersprites/Sprite-0004.png"));
        } catch (IOException e) {
            
        }
        // Create a JPanel to hold the buttons in a 2x2 grid layout at the bottom
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));

        // Create the Attack button
        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener((ActionEvent e) -> {
            // Handle Attack button click
        });
        buttonPanel.add(attackButton);

        // Create the Open Inventory button
        JButton inventoryButton = new JButton("Open Inventory");
        inventoryButton.addActionListener((ActionEvent e) -> {
            // Handle Inventory button click
        });
        buttonPanel.add(inventoryButton);

        // Create the Finish Turn button
        JButton finishTurnButton = new JButton("Finish Turn");
        finishTurnButton.addActionListener((ActionEvent e) -> {
            // Handle Finish Turn button click
        });
        buttonPanel.add(finishTurnButton);

        // Create the Retreat button
        JButton retreatButton = new JButton("Retreat");
        retreatButton.addActionListener((ActionEvent e) -> {
            // Handle Retreat button click
        });
        buttonPanel.add(retreatButton);

        // Add the button panel to the bottom of the GameBattlePanel
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Set the background image
        this.setOpaque(false); // Make the panel transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Draw the background image to cover the entire panel
        g2.drawImage(image, WIDTH, WIDTH, screenWidth, screenHeight, null);
        
        g2.drawImage(playerImg, 96,336, tileSize * 3, tileSize * 3, null);
    };
}
