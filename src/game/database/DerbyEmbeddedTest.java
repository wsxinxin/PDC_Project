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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyEmbeddedTest {
    public static void main(String[] args) {
        String databaseURL = "jdbc:derby:C:/Users/wangs/OneDrive/Desktop/UniStuff/NetBeans Stuff/P6_18045290_21139803/PDC_Project/DB/gamedb";
        try{
            Connection connection = DriverManager.getConnection(databaseURL);
            System.out.println("Connected to the Database");
            
            String sql = "INSERT INTO inventory (itemsinbag, weapon, potion) VALUES"
                    + "('true', Rusty Sword', 2)";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            
            if (rows > 0){
                System.out.println("A new save state has been created");
            }
            
            String shutdownURL = "jdbc:derby:;shutdown=true";
            DriverManager.getConnection(shutdownURL);
            
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
