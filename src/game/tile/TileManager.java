/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.tile;

import game.window.GameWorldPanel;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author chris
 */
public class TileManager {
    GameWorldPanel gwp;
    Tile[]tile;

    public TileManager(GameWorldPanel gwp) {
        this.gwp = gwp;
        tile = new Tile[10];
        
        getTileImage();
    }
    
    public void getTileImage(){
        
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/worldTiles/grass.png"));
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/worldTiles/infected_grass-0001.png"));
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/worldTiles/infected_grass-0002.png"));
            
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/worldTiles/polluted_water.png"));
            
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/worldTiles/road-0001.png"));
            
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/worldTiles/road-0002.png"));
            
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/worldTiles/road-0003.png"));
            
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/worldTiles/ruines-0001.png"));
            
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/worldTiles/water.png"));
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        
        int tileSize = gwp.tileSize;
        int numRows = 12;
        int numCols = 16;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                g2.drawImage(tile[0].image, x, y, tileSize, tileSize, null);
            }
        }
        
        //Infected_grass city 1
        g2.drawImage(tile[1].image, 528,0, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[2].image, 576,0, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[2].image, 624,0, gwp.tileSize, gwp.tileSize, null);
        
        g2.drawImage(tile[2].image, 480,48, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[1].image, 528,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[1].image, 576,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[2].image, 624,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[2].image, 672,48, gwp.tileSize, gwp.tileSize, null);
        
        g2.drawImage(tile[1].image, 432,96, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[2].image, 480,96, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[2].image, 528,96, gwp.tileSize, gwp.tileSize, null);

        g2.drawImage(tile[1].image, 480,144, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[1].image, 528,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[2].image, 576,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[2].image, 624,144, gwp.tileSize, gwp.tileSize, null);        
        
        //Infected_grass city 2
        g2.drawImage(tile[2].image, 240,432, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[2].image, 288,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[1].image, 336,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[1].image, 384,432, gwp.tileSize, gwp.tileSize, null); 
        
        g2.drawImage(tile[2].image, 288,480, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[1].image, 336,480, gwp.tileSize, gwp.tileSize, null);     
        
        //Ruined Cities
        g2.drawImage(tile[7].image, 528,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[7].image, 576,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[7].image, 624,48, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[7].image, 480,96, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[7].image, 528,96, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[7].image, 528,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[7].image, 576,144, gwp.tileSize, gwp.tileSize, null);
        
        g2.drawImage(tile[7].image, 288,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[7].image, 336,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[7].image, 288,480, gwp.tileSize, gwp.tileSize, null);
    
        //Water (River, lakes, sea)
        g2.drawImage(tile[8].image, 0,0, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 0,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 0,96, gwp.tileSize, gwp.tileSize, null);
        
        g2.drawImage(tile[8].image, 0,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 48,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 96,144, gwp.tileSize, gwp.tileSize, null);

        g2.drawImage(tile[8].image, 96,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 144,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 192,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 240,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 288,192, gwp.tileSize, gwp.tileSize, null);

        g2.drawImage(tile[3].image, 288,240, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[3].image, 336,240, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[3].image, 384,240, gwp.tileSize, gwp.tileSize, null);
        
        g2.drawImage(tile[3].image, 384,288, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[3].image, 432,288, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[3].image, 480,288, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 528,288, gwp.tileSize, gwp.tileSize, null);
        
        g2.drawImage(tile[8].image, 528,336, gwp.tileSize, gwp.tileSize, null);
 
        g2.drawImage(tile[8].image, 576,336, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 576,384, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 576,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 576,480, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[8].image, 576,528, gwp.tileSize, gwp.tileSize, null);

        //roads
        
        /*//row 1
        g2.drawImage(tile[0].image, 0,0, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,0, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,0, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,0, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,0, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,0, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,0, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,0, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,0, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,0, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,0, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,0, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,0, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,0, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,0, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,0, gwp.tileSize, gwp.tileSize, null);    
        
        //row 2
        g2.drawImage(tile[0].image, 0,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,48, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,48, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,48, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,48, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,48, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,48, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,48, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,48, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,48, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,48, gwp.tileSize, gwp.tileSize, null); 
        
        //row 3
        g2.drawImage(tile[0].image, 0,96, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,96, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,96, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,96, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,96, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,96, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,96, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,96, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,96, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,96, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,96, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,96, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,96, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,96, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,96, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,96, gwp.tileSize, gwp.tileSize, null); 
        
        //row 4
        g2.drawImage(tile[0].image, 0,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,144, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,144, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,144, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,144, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,144, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,144, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,144, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,144, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,144, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,144, gwp.tileSize, gwp.tileSize, null); 
        
        //row 5
        g2.drawImage(tile[0].image, 0,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,192, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,192, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,192, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,192, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,192, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,192, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,192, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,192, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,192, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,192, gwp.tileSize, gwp.tileSize, null); 
        
        //row 6
        g2.drawImage(tile[0].image, 0,240, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,240, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,240, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,240, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,240, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,240, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,240, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,240, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,240, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,240, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,240, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,240, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,240, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,240, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,240, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,240, gwp.tileSize, gwp.tileSize, null); 
        
        //row 7
        g2.drawImage(tile[0].image, 0,288, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,288, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,288, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,288, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,288, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,288, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,288, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,288, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,288, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,288, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,288, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,288, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,288, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,288, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,288, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,288, gwp.tileSize, gwp.tileSize, null); 
        
        //row 8
        g2.drawImage(tile[0].image, 0,336, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,336, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,336, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,336, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,336, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,336, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,336, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,336, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,336, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,336, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,336, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,336, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,336, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,336, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,336, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,336, gwp.tileSize, gwp.tileSize, null); 
        
        //row 9
        g2.drawImage(tile[0].image, 0,384, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,384, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,384, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,384, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,384, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,384, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,384, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,384, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,384, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,384, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,384, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,384, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,384, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,384, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,384, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,384, gwp.tileSize, gwp.tileSize, null); 

        //row 10
        g2.drawImage(tile[0].image, 0,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,432, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,432, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,432, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,432, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,432, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,432, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,432, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,432, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,432, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,432, gwp.tileSize, gwp.tileSize, null); 

        //row 11
        g2.drawImage(tile[0].image, 0,480, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,480, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,480, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,480, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,480, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,480, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,480, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,480, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,480, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,480, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,480, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,480, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,480, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,480, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,480, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,480, gwp.tileSize, gwp.tileSize, null); 

        //row 12
        g2.drawImage(tile[0].image, 0,528, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 48,528, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 96,528, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 144,528, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 192,528, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 240,528, gwp.tileSize, gwp.tileSize, null);        
        g2.drawImage(tile[0].image, 288,528, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 336,528, gwp.tileSize, gwp.tileSize, null);
        g2.drawImage(tile[0].image, 384,528, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 432,528, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 480,528, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 528,528, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 576,528, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 624,528, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 672,528, gwp.tileSize, gwp.tileSize, null);      
        g2.drawImage(tile[0].image, 720,528, gwp.tileSize, gwp.tileSize, null); */
    }
}