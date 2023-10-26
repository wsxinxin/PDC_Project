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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMenuPanel extends JPanel {
    private JButton resumeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;

    // Reference to the JFrame that contains this panel
    private JFrame parentFrame; // It is being used but NetBeans do not recognize it until remove it.

    public GameMenuPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame; // Store the reference to the parent frame

        // Set the layout for the panel
        setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column, 10px vertical and horizontal gap
        setOpaque(false); // Make the panel transparent

        // Create buttons
        resumeButton = new JButton("Resume Game");
        saveButton = new JButton("Save Game");
        loadButton = new JButton("Load Game");
        quitButton = new JButton("Quit Game");

        // Add buttons to the panel
        add(resumeButton);
        add(saveButton);
        add(loadButton);
        add(quitButton);

        // Add action listeners to the buttons
        resumeButton.addActionListener((ActionEvent e) -> {
            // Close the parent JFrame when "Resume Game" is clicked
            parentFrame.dispose();
        });
        
        saveButton.addActionListener((ActionEvent e) -> {
            // Save the Game State when "Save Game" is clicked
        });
        
        loadButton.addActionListener((ActionEvent e) ->{
            // Load an existent Game Save when "Load Game" is clicked
        });
        
        quitButton.addActionListener((ActionEvent e) ->{
            // code to quit the game here
            System.exit(0);
        });
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the background color with transparency
        g.setColor(new Color(0, 0, 0, 150)); // R, G, B, Alpha (0-255)
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}