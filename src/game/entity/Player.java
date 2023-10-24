/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.entity;

import game.tile.UtilityTool;
import game.window.GameWorldPanel;
import game.window.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Christian
 */
public final class Player extends Entity{
    
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;

    public Player(GameWorldPanel gwp, KeyHandler keyH) {
        super(gwp);
        this.keyH = keyH;
        
        screenX = gwp.screenWidth/2 - (gwp.tileSize/2);
        screenY = gwp.screenHeight/2 -(gwp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x; 
        solidAreaDefaultY = solidArea.y; 
        solidArea.width = 32;
        solidArea.height = 32;
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gwp.tileSize*18;
        worldY = gwp.tileSize*20;
        speed = 4;
        direction = "down";
    }
     
    public void getPlayerImage(){
    
        up1 = setup("playersprites/Sprite-0001"); 
        up2 = setup("playersprites/Sprite-0009");
        up3 = setup("playersprites/Sprite-0010");
                
        down1 = setup("playersprites/Sprite-0004");
        down2 = setup("playersprites/Sprite-0005");        
        down3 = setup("playersprites/Sprite-0006"); 
                
        left1 = setup("playersprites/Sprite-0003");        
        left2 = setup("playersprites/Sprite-0011");        
        left3 = setup("playersprites/Sprite-0012");
                    
        right1 = setup("playersprites/Sprite-0002");        
        right2 = setup("playersprites/Sprite-0007");        
        right3 =  setup("playersprites/Sprite-0008");           
    }
     
    @Override
    public void update(){
        
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
        
            if (keyH.upPressed == true) {
                direction = "up";
            }
            else if (keyH.downPressed == true) {
                direction = "down";
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
            }
            
            // Check Tile Collision
            collisionOn = false;
            gwp.cChecker.checkTile(this);
            
            // If collision is false, player can move
            if (collisionOn == false) {
               
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
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
    }
    @Override
    public void draw(Graphics2D g2){
        
        //g2.setColor(Color.white);  
        //g2.fillRect(x, y, gwp.tileSize, gwp.tileSize);
        
        BufferedImage image = null;
        
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
        g2.drawImage(image, screenX, screenY, null);
    }
}
