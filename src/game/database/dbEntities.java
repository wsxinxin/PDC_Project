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

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbEntities {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public dbEntities() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public void connectGameDB() {
        try {
            this.statement = conn.createStatement();
            this.checkExistedTable("BOOK");
            this.statement.addBatch("CREATE  TABLE INVENTORY  (ITEMSINBAG  BOOLEAN,   WEAPON   VARCHAR(45),   POTION  INT)");
            this.statement.addBatch("INSERT INTO INVENTORY VALUES (true, 'Rusty Sword', 2),\n");

            this.statement.executeBatch();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
/*
    public void createPromotionTable() {
        try{
            /* You may need the following SQL statements for this method:
            
             * Create the table:
               CREATE TABLE PROMOTION (CATEGORY VARCHAR(20), DISCOUNT INT);
            
             * Insert records into the table: 
               INSERT INTO PROMOTION VALUES ('Fiction', 0),
               ('Non-fiction', 10),
               ('Textbook', 20);

             
            this.statement = conn.createStatement();
            this.checkExistedTable("PROMOTION");
            this.statement.addBatch("CREATE TABLE PROMOTION (CATEGORY VARCHAR(20), DISCOUNT INT)");
            this.statement.addBatch("INSERT INTO PROMOTION VALUES ('Fiction', 0),\n"
                + "('Non-fiction', 10),\n"
                + "('Textbook', 20)");
            this.statement.executeBatch();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    */
    
    public void createAnotherSave(ResultSet rs) {
        
        try {
            this.checkExistedTable("Another Save");
            this.statement = conn.createStatement();
            this.statement.addBatch("CREATE TABLE ANOTHERSAVE (ITEMSINBAG BOOLEAN, WEAPON VARCHAR(45), POTION INT)");
            while (rs.next()) {
                boolean itemsisbag = rs.getBoolean("true");
                String weapon = rs.getString("WEAPON");
                int potion = rs.getInt("POTION");
                this.statement.addBatch("INSERT INTO ANOTHERSAVE VALUES(itemsinbag, weapon, potion");
            }
            this.statement.executeBatch();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ResultSet getAnotherSave() {
        /* You may need the following SQL statements for this method:

        * Query multiple tables:
        
          SELECT TITLE, PRICE, DISCOUNT FROM BOOK, PROMOTION WHERE BOOK.CATEGORY=PROMOTION.CATEGORY;

         */

        ResultSet rs = null;
        try{
            rs = this.statement.executeQuery("SELECT ITEMSINBAG, WEAPON, POTION "
                    + "FROM INVENTORY "
                    + "WHERE INVENTORY.CATEGORY=PROMOTION.CATEGORY");
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