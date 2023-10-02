/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class GameStartPanel extends JPanel {
    
    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16; //results in a 4:3 ratio
    final int maxScreenRow = 12; //results in a 4:3 ratio
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
        
    public GameStartPanel(){
        // Set layout to a BorderLayout
        setLayout(new BorderLayout());
        
        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        // Create Start Game button
        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to start a new game here
                JOptionPane.showMessageDialog(null, "Starting a new game!");
                startGame();
            }
        });
        
        // Create Load Game button
        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to load a saved game here
                JOptionPane.showMessageDialog(null, "Loading a saved game!");
                // TO BE IMPLEMENTED
            }
        });
        
        // Create Quit Game button
        JButton quitGameButton = new JButton("Quit Game");
        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to quit the game here
                System.exit(0);
            }
        });
        
        // Add buttons to the button panel
        buttonPanel.add(startGameButton);
        buttonPanel.add(loadGameButton);
        buttonPanel.add(quitGameButton);
        
        // Add the button panel to the center of the GameBattlePanel
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    // Method to start the game
    private void startGame() {
        JFrame frame = new JFrame("Game Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("PDC_RPGGAME");

        // Create the GameWorldPanel and add it to the JFrame
        GameWorldPanel gameWorldPanel = new GameWorldPanel();
        frame.add(gameWorldPanel);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gameWorldPanel.startGameThread(); // Start the gamframe
    }
    
    // This method sets the preferred size of the panel
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(screenWidth, screenHeight);
    }
}