/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;
import game.entity.Player;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

public class gameDatabaseMain {
    public static void main(String[] args) {

        Player player = new Player();
        Player.connectPlayerDB();
        Player.createLastSavedTable();
        Player.createLastSavedTable(Player.getLastSaved());
        Player.closeConnection();
    }
    
}
