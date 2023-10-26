/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class simpleDBEntities {

    SimpleDBManager dbManager;
    Connection conn;
    Statement statement;

    public simpleDBEntities() {
        dbManager = new SimpleDBManager();
        conn = dbManager.getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void main(String[] args) {
        simpleDBEntities sbs = new simpleDBEntities();

        try {
            sbs.statement.addBatch("CREATE  TABLE INVENTORY  (ITEMSINBAG  BOOLEAN,   WEAPON   VARCHAR(45),   POTION  INT)");
            sbs.statement.addBatch("INSERT INTO INVENTORY VALUES (true, 'Rusty Sword', 2),\n");
            sbs.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        sbs.closeConnection();
    }

    public void closeConnection() {
        this.dbManager.closeConnections();
    }

}

