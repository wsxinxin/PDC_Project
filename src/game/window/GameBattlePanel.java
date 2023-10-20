/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class GameBattlePanel extends JPanel{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16; //results in a 4:3 ratio
    final int maxScreenRow = 12; //results in a 4:3 ratio
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    // Constructor for GameBattlePanel
    public GameBattlePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

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
    
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
               
    }
}
