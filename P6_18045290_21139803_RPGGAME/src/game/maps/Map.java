/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.maps;
/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment1
 */


public class Map 
{
    //This will contain a 2d array map, when entering a new grid of the map
    //battle initiates
    private char[][] map;
    private int xAxis;
    private int yAxis;
    public int playerXcord;
    public int playerYcord;

    //Map constructor
    public Map(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        map = new char[xAxis][yAxis];
        initializeMap();
    }

    //Map render method
    private void initializeMap() {
        for (int i = 0; i < xAxis; i++) {
            for (int j = 0; j < yAxis; j++) {
                map[i][j] = '-';
            }
        }
        playerXcord = 2;
        playerYcord = 2;
        map[playerXcord][playerYcord] = 'P'; // Marks player location as 'P'
    }

    // player movement method
    public void movePlayer(int posX, int posY) {
        if (posX >= 0 && posX < xAxis && posY >= 0 && posY < yAxis) {
            map[playerXcord][playerYcord] = 'X'; // Clear the current position
            playerXcord = posX;
            playerYcord = posY;
            map[playerXcord][playerYcord] = 'O'; // Set the new player position as 'P'
        }
    }

    // map display method
    public void displayMap() {
        System.out.println("Map:");
        for (int i = 0; i < xAxis; i++) {
            for (int j = 0; j < yAxis; j++) {
                System.out.print(map[i][j] + " "); // Print the character followed by a space
            }
            System.out.println(); // Move to the next row
        }
    }

    public boolean isCleared() {
        return map[playerXcord][playerYcord] == 'X';
    }

    public void markCleared() {
        map[playerXcord][playerYcord] = 'X';
    }
    
}