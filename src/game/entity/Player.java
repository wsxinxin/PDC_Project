/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.entity;

import game.window.GameWorldPanel;
import game.window.KeyHandler;
import java.awt.Color;
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
    
    GameWorldPanel gwp;
    KeyHandler keyH;
    int playerHp = 100;

    public Player(GameWorldPanel gwp, KeyHandler keyH) {
        this.gwp = gwp;
        this.keyH = keyH;
        
        solidArea = new Rectangle(8,16,32,32);
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    
    // decrease the hp after suffer damage
    public void decreaseHP(int damage) {
        playerHp -= damage;
        if (playerHp < 0) {
            playerHp = 0;
        }
    }
    
    public void getPlayerImage(){
        
        try{
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/playersprites/Sprite-0001.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0009.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0010.png"));
            
            down1 = ImageIO.read(getClass().getResourceAsStream("/playersprites/Sprite-0004.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0005.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0006.png"));
            
            left1 = ImageIO.read(getClass().getResourceAsStream("/playersprites/Sprite-0003.png")); 
            left2 = ImageIO.read(getClass().getResourceAsStream("/playersprites/Sprite-0011.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0012.png"));  
            
            right1 = ImageIO.read(getClass().getResourceAsStream("/playersprites/Sprite-0002.png"));      
            right2 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0007.png"));  
            right3 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0008.png"));
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
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
    }
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
        g2.drawImage(image, x, y, gwp.tileSize, gwp.tileSize, null);
    }
}
