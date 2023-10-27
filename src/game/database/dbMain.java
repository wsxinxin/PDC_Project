/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

public class dbMain {

    public static void main(String[] args) {

        dbEntities gameDB = new dbEntities();
        gameDB.connectGameDB();
        //gameDB.createPromotionTable();
        gameDB.createAnotherSave(gameDB.getAnotherSave());
        gameDB.closeConnection();
    }
}