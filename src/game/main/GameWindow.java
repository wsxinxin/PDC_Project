/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.main;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

import javax.swing.JFrame;

import game.window.GameWorldPanel;

public class GameWindow {
    
    public static JFrame frame;

    public static void main(String[] args) {
        // Create a JFrame to hold the GameStartPanel
        frame = new JFrame("Game Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("PDC_RPGGAME");
        //frame.setUndecorated(true);

        // Create the GameWorldPanel and add it to the JFrame
        GameWorldPanel gameWorldPanel = new GameWorldPanel();
        frame.add(gameWorldPanel);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        gameWorldPanel.setupGame();
        gameWorldPanel.startGameThread();
    }
}