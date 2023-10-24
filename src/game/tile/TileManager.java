/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.tile;

import game.entity.Player;
import game.window.GameWorldPanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author chris
 */
public class TileManager {
    GameWorldPanel gwp;
    Tile[]tile;
    int mapTileNum[][]; 
    int Arena1;

    public TileManager(GameWorldPanel gwp) {
        this.gwp = gwp;
        tile = new Tile[30];
        mapTileNum = new int[gwp.maxWorldCol][gwp.maxWorldRow];
        
        getTileImage();
        loadMap("map/worldmap1.txt");
    }
    
    public void getTileImage(){
        
        setup(0, "grass", false);
        setup(1, "infected_grass-0001", false);
        setup(2, "infected_grass-0002", true);
        setup(3, "infected_grass-0003", false);
        setup(4, "water-polluted", true);
        setup(5, "ruines-0001", true);
        setup(6, "water-transition-0001", true);
        setup(7, "water-transition-0002", true);
        setup(8, "water-0001", true);
        setup(9, "water-0002", true);
        setup(10, "water-0003", true);
        setup(11, "water-0004", true);
        setup(12, "water-0005", true);
        setup(13, "water-0006", true);
        setup(14, "wall-0001", true);
        setup(15, "wall-0002", true);
        setup(16, "wall-0003", true);
        setup(17, "wall-0004", true);
        setup(18, "corner_wall-0001", true);
        setup(19, "corner_wall-0002", true);
        setup(20, "corner_wall-0003", true);
        setup(21, "corner_wall-0004", true);
        setup(22, "water", true);
        setup(23, "bridge", false);
    }
    
    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/worldtiles/"+ imageName +".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gwp.tileSize, gwp.tileSize);
            tile[index].collision = collision;
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }  
    
    public void loadMap(String fileName) {
        
        try {
            InputStream is = getClass().getResourceAsStream("/res/"+fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while (col < gwp.maxWorldCol && row < gwp.maxWorldRow) {
                
                String line = br.readLine();
                
                while (col < gwp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gwp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
            
        } catch (Exception e){
                
        }
    }
    
    public void draw(Graphics2D g2) {
        
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gwp.maxWorldCol && worldRow < gwp.maxWorldRow) {
            
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gwp.tileSize;
            int worldY = worldRow * gwp.tileSize;
            int screenX = worldX - gwp.player.worldX + gwp.player.screenX;
            int screenY = worldY - gwp.player.worldY + gwp.player.screenY;
            
            if (worldX + gwp.tileSize > gwp.player.worldX - gwp.player.screenX &&
                worldX - gwp.tileSize < gwp.player.worldX + gwp.player.screenX &&
                worldY + gwp.tileSize > gwp.player.worldY - gwp.player.screenY &&
                worldY - gwp.tileSize < gwp.player.worldY + gwp.player.screenY) {
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);   
            }
            
            worldCol++;
       
            if (worldCol == gwp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        } 
        
        /*// Cities in ruines 
        g2.drawImage(tile[5].image, 576, 0, null);
        g2.drawImage(tile[5].image, 624, 0, null);
        g2.drawImage(tile[5].image, 480, 48, null);
        g2.drawImage(tile[5].image, 624, 48, null);
        g2.drawImage(tile[5].image, 672, 48, null);
        g2.drawImage(tile[5].image, 480, 96, null);
        g2.drawImage(tile[5].image, 528, 96, null);
        g2.drawImage(tile[5].image, 576, 144, null);
        g2.drawImage(tile[5].image, 624, 144, null);
        
        g2.drawImage(tile[5].image, 336,432, null);
        g2.drawImage(tile[5].image, 384,432, null);
        g2.drawImage(tile[5].image, 336,480, null);
        
        // Lockdown Walls
        g2.drawImage(tile[18].image, 0,0, null);
        g2.drawImage(tile[14].image, 96,0, null);
        g2.drawImage(tile[14].image, 144,0, null);
        g2.drawImage(tile[14].image, 192,0, null);
        g2.drawImage(tile[14].image, 240,0, null);        
        g2.drawImage(tile[14].image, 288,0, null);
        g2.drawImage(tile[14].image, 336,0, null);
        g2.drawImage(tile[14].image, 384,0, null);      
        g2.drawImage(tile[14].image, 432,0, null);      
        g2.drawImage(tile[14].image, 480,0, null);      
        g2.drawImage(tile[14].image, 528,0, null);      
        g2.drawImage(tile[14].image, 672,0, null);      
        g2.drawImage(tile[19].image, 720,0, null);*/
    }
}