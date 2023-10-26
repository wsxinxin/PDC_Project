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

import game.interfaces.Function;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
    
public class GameState implements Function
{
    // creates the save file
    @Override
    public void saveGameState(String fileName, String gameData)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            bw.write(gameData);
            System.out.println("Game state has been saved.");
        }
        catch (IOException ex)
        {
            System.out.println("Game state could not be saved."+ ex.getMessage());
        }
    }
    
    // load the save file created
    @Override
    public String loadGameState(String fileName)
    {
        StringBuilder gameData = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                gameData.append(line).append("\n");
            }
            System.out.println("Game state loaded.");           
        } catch(IOException ex)
        {
            System.out.println("Game stat could not be loaded."+ ex.getMessage());
        }
        return gameData.toString();
    }
    
    /*public static void main(String args[]) {
        // Create an instance of the GameState class
        GameState gameState = new GameState();

        // Test Usage
        String saveGameState = "PlayerName: Andrew\nLevel: 10\nWave: 3";
        String fileName = "./SaveFiles/SavedState1.txt";

        // Saving game state to the text file
        gameState.saveGameState(fileName, saveGameState);

        // Load the game state from the text file
        String loadGameState = gameState.loadGameState(fileName);
        System.out.println("Loaded Game State:\n" + loadGameState);
    }*/
}
