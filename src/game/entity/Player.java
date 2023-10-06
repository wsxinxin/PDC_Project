/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.entity;

import game.window.GameWorldPanel;
import game.window.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
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

    public Player(GameWorldPanel gwp, KeyHandler keyH) {
        this.gwp = gwp;
        this.keyH = keyH;
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    
    public void getPlayerImage(){
        
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0001.png"));
            //up2 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0001.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0004.png"));
            //down2 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0001.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0003.png"));       
            //left2 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0001.png"));       
            right1 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0002.png"));      
            //right2 = ImageIO.read(getClass().getResourceAsStream("/playerSprites/Sprite-0001.png"));     
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void update(){
        
        if (keyH.upPressed == true) {
            direction = "up";
            y -= speed;
        }
        else if (keyH.downPressed == true) {
            direction = "down";
            y += speed;
        }
        else if (keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        else if (keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        }
    }
    public void draw(Graphics2D g2){
        
        //g2.setColor(Color.white);  
        //g2.fillRect(x, y, gwp.tileSize, gwp.tileSize);
        
        BufferedImage image = null;
        
        switch (direction) {
            
        case "up": 
            image = up1;
            break;
        case "down": 
            image = down1;
            break;
        case "left": 
            image = left1;
            break;
        case "right": 
            image = right1;
            break;
        }
        g2.drawImage(image, x, y, gwp.tileSize, gwp.tileSize, null);
    }
}
