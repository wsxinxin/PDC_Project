/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.main;

/**
 *
 * @author chris
 */

import javax.swing.JFrame;
import game.window.GameMenuPanel;

public class GameWindow {

    public static void main(String[] args) {
        // Create a JFrame to hold the GameStartPanel
        JFrame frame = new JFrame("Game Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create the GameStartPanel and add it to the JFrame
        GameMenuPanel gameStartPanel = new GameMenuPanel();
        frame.add(gameStartPanel);

        // Make the JFrame visible
        frame.setVisible(true);
    }
}
