/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author wangs
 */
public class DerbyNetworkClient {
    public static void main(String[] args) throws SQLException {
        String databaseURL = "jdbc:derby:C:/Users/wangs/OneDrive/Desktop/UniStuff/NetBeans Stuff/P6_18045290_21139803/PDC_Project/DB/gamedb";
        Connection connection = DriverManager.getConnection(databaseURL);
        String sql = "SELECT * FROM inventory";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        
        while(result.next()){
            String weapon = result.getString("weapon");
            int potion = result.getInt("potion");
            
            System.out.printf("%s - %d\n",weapon, potion);
        }
        connection.close();
        
    }
}
