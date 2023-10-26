/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;

/**
 *
 * @author wangs
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
