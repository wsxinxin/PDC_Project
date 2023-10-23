/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.entity;

import game.tile.UtilityTool;
import game.window.GameWorldPanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Christian
 */
public class Entity {
    
    GameWorldPanel gwp;
    public int x, y;
    public int speed;  
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    
    // Entity Status
    public int maxHP;
    public int hp;
    
    public Entity(GameWorldPanel gwp){
        this.gwp = gwp;
    }
    
    public void setAction(){
        
    }
    
    public void update(){
        setAction();
        
        collisionOn = false;
        gwp.cChecker.checkTile(this);
        gwp.cChecker.checkPlayer(this);
        
        // If collision is false, entity can move
        if (collisionOn == false) {
               
            switch(direction){
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
                case "right":
                    x += speed;
                    break;
            }
        }
        
        spriteCounter++;
        if (spriteCounter > 12) {
            switch (spriteNum) {
                case 1:
                    spriteNum = 2;
                    break;
                case 2:
                    spriteNum = 3;
                    break;
                case 3:
                    spriteNum = 1;
                    break;
                default:
                    break;
            }
            spriteCounter = 0;
        }
    }
    
    public void draw(Graphics2D g2) {
        
       BufferedImage image = null;
       int screenX = x - gwp.player.x;
       int screenY = x - gwp.player.y;
       
       if (x + gwp.tileSize > gwp.player.x &&
           x - gwp.tileSize < gwp.player.x &&
           y + gwp.tileSize > gwp.player.y &&
           y - gwp.tileSize < gwp.player.y) {
           
           switch (direction) {
            
           case "up": 
               if (spriteNum == 1){
                   image = up1;
               }
               if (spriteNum == 2){
                   image = up2;
               }
               if (spriteNum == 3){
                   image = up3;
               }            
               break;
           case "down": 
               if (spriteNum == 1){
                   image = down1 ;
               }
               if (spriteNum == 2){
                   image = down2 ;
               }
               if (spriteNum == 3){
                   image = down3 ;
               }
               break;
           case "left": 
               if (spriteNum == 1){
                   image = left1;
               }
               if (spriteNum == 2){
                   image = left2;
               }
               if (spriteNum == 3){
                   image = left3;
               }
               break;
           case "right": 
               if (spriteNum == 1){
                   image = right1;
               }
               if (spriteNum == 2){
                   image = right2;
               }
               if (spriteNum == 3){
                   image = right3;
               }
               break;
           }
           g2.drawImage(image, screenX, screenY, gwp.tileSize, gwp.tileSize, null);
       }
    }
    
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null; 
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/" + imageName + ".png"));
            image = uTool.scaleImage(image, gwp.tileSize, gwp.tileSize);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
