/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.tile;

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

    public TileManager(GameWorldPanel gwp) {
        this.gwp = gwp;
        tile = new Tile[20];
        mapTileNum = new int[gwp.maxScreenCol][gwp.maxScreenRow];
        
        getTileImage();
        loadMap();
    }
    
    public void getTileImage(){
        
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/grass.png"));
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/infected_grass-0001.png"));
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/infected_grass-0002.png"));
            tile[2].collision = true;
            
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/polluted_water.png"));
            tile[3].collision = true;
            
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/road-0001.png"));
            
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/road-0002.png"));
            
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/road-0003.png"));
            
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/ruines-0001.png"));
            tile[7].collision = true;
            
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/water.png"));
            tile[8].collision = true;
            
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/water-transition-0001.png"));
            tile[9].collision = true;
            
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/worldtiles/water-transition-0002.png"));
            tile[10].collision = true;
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadMap() {
        
        try {
            InputStream is = getClass().getResourceAsStream("/resmap/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while (col < gwp.maxScreenCol && row < gwp.maxScreenRow) {
                
                String line = br.readLine();
                
                while (col < gwp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gwp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
            
        } catch (Exception e){
                
        }
    }
    
    public void draw(Graphics2D g2) {
        
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gwp.maxScreenCol && row < gwp.maxScreenRow) {
            
            int tileNum = mapTileNum[col][row];
            
            g2.drawImage(tile[tileNum].image, x, y, gwp.tileSize,gwp.tileSize, null);
            col++;
            x += gwp.tileSize;
            
            if (col == gwp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gwp.tileSize;
            }
        } 
        
        // Cities in ruines 
        g2.drawImage(tile[7].image, 576, 0, gwp.tileSize,gwp.tileSize, null);
        g2.drawImage(tile[7].image, 624, 0, gwp.tileSize,gwp.tileSize, null);
        g2.drawImage(tile[7].image, 480, 48, gwp.tileSize,gwp.tileSize, null);
        g2.drawImage(tile[7].image, 624, 48, gwp.tileSize,gwp.tileSize, null);
        g2.drawImage(tile[7].image, 672, 48, gwp.tileSize,gwp.tileSize, null);
        g2.drawImage(tile[7].image, 480, 96, gwp.tileSize,gwp.tileSize, null);
        g2.drawImage(tile[7].image, 528, 96, gwp.tileSize,gwp.tileSize, null);
        g2.drawImage(tile[7].image, 576, 144, gwp.tileSize,gwp.tileSize, null);
        g2.drawImage(tile[7].image, 624, 144, gwp.tileSize,gwp.tileSize, null);
        
        g2.drawImage(tile[7].image, 336,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[7].image, 384,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[7].image, 336,480, gwp.tileSize, gwp.tileSize, null);
        
    }
}