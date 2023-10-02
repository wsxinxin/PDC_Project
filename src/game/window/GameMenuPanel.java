/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.window;

/**
 *
 * @author chris
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMenuPanel extends JPanel {
    private JButton resumeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
   
    public GameMenuPanel() {
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

        // Add action listeners to the buttons (unchanged from previous code)

        // Create a KeyHandler and add it as a key listener to this panel

        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the background color with transparency
        g.setColor(new Color(0, 0, 0, 150)); // R, G, B, Alpha (0-255)
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}