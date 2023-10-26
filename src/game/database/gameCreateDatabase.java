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

import game.window.GameWorldPanel;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class gameCreateDatabase 
{
    private final gameDBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public gameCreateDatabase() {
        dbManager = new gameDBManager();
        conn = dbManager.getConnection();
    }

    public void connectPlayerDB() {
        try {
            this.statement = conn.createStatement();
            this.checkExistedTable("Player");
            this.statement.addBatch("CREATE TABLE PLAYER(PLAYER VARCHAR(10),  WEAPON VARCHAR(50), POTION VARCHAR(20), MAPX FLOAT, MAPY FLOAT)");
            this.statement.addBatch("INSERT INTO PLAYER VALUES (Player, 'RustySword','Potion', 1.0, 3.0 , \n"
                + "(Player, 'RustySword', 'Potion', 1.0, 2.0),\n"
                + "(Player, 'RustySword', 'Potion', 2.0, 2.0),\n");
            this.statement.executeBatch();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void createLastSavedTable(ResultSet rs) {
        
        try {
            this.checkExistedTable("LastSaved");
            this.statement = conn.createStatement();
            this.statement.addBatch("CREATE TABLE PLAYER(PLAYER STRING,  WEAPON VARCHAR(50), POTION VARCHAR(20), MAPX FLOAT, MAPY FLOAT)");
            while (rs.next()) {
                String player = rs.getString("PLAYER");
                String weapon = rs.getString("WEAPON");
                String potion = rs.getString("POTION");
                float mapx = rs.getFloat("MAPX");
                float mapy = rs.getFloat("MAPY");
                this.statement.addBatch("INSERT INTO LASTSAVEDTABLE VALUES('" + player + "'," + weapon + "'," + potion +"'," + mapx +"'," + mapy +"'")");
            }
            this.statement.executeBatch();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ResultSet getLastSaved() {
        ResultSet rs = null;
        try{
            rs = this.statement.executeQuery("SELECT PLAYER, WEAPON, POTION, MAPX, MAPY "
                    + "FROM PLAYER"
                    + "WHERE PLAYER"); //"WHERE PLAYER.something = PLAYER.something
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;

    }
    
    public void checkExistedTable(String name){
        try{
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);
            
            while(rs.next()){
                String table_name = rs.getString("TABLE_NAME");
                System.out.println(table_name);
                if(table_name.equalsIgnoreCase(name)){
                    statement.executeUpdate("Drop table "+ name);
                    System.out.println("Table "+ name + " has been deleted.");
                    break;
                }
            }
            rs.close();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeConnection() {
        this.dbManager.closeConnections();
    }
    
}
