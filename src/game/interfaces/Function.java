/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.interfaces;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment1
 */

// functions of the game
public interface Function 
{          
    public void saveGameState(String fileName, String gameData);
    
    public String loadGameState(String fileName);  
}