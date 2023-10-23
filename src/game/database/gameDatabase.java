/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;

/**
 *
 * @author wangs
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class gameDatabase {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Connect to the SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:game.db");
            statement = connection.createStatement();

            // Create a game state table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS game_state (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "player_name TEXT," +
                    "score INT)";
            statement.execute(createTableSQL);

            // Insert a sample game state
            String insertDataSQL = "INSERT INTO game_state (player_name, score) VALUES ('Player 1', 100)";
            statement.execute(insertDataSQL);

            System.out.println("Database and table created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

