/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.entity;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

import game.tile.UtilityTool;
import game.window.GameWorldPanel;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entity {
    
    GameWorldPanel gwp;    
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    
    // STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    
    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    
    // CHARACTER ATTRIBUTES
    public String name;
    public int speed;
    public int maxHP;
    public int hp;
    
    //TYPE
    public int type;
    public final int type_player = 0;
    public final int type_monster = 1;
    public final int type_consumable = 2;
    public final int type_obstacle = 3;
   
    // ITEM ATTRIBUTES
    public String description = "";
    
    // Constructor
    public Entity(GameWorldPanel gwp){
        this.gwp = gwp;
    }
    public int getLeftX() {
        return worldX + solidArea.x;
    }
    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY() {
        return worldY + solidArea.y;
    }
    public int getBottomY() {
        return worldY + solidArea.y;
    }
    public int getCol() {
        return (worldX + solidArea.x)/gwp.tileSize;
    }
    public int getRow() {
        return (worldY + solidArea.y)/gwp.tileSize;
    }
    public void setAction(){}
    public void damageReaction(){}
    public void interact() {}
    public boolean use(Entity entity) {return false;}
    public void update(){
        
        setAction();
        
        collisionOn = false;
        gwp.cChecker.checkTile(this);
        gwp.cChecker.checkEntity(this, gwp.monster);
        boolean contactPlayer = gwp.cChecker.checkPlayer(this);
        
        if (this.type == type_monster && contactPlayer == true) {
            if (gwp.player.invincible == false) {
                // can give damage
                gwp.player.hp -= 1;
                gwp.player.invincible = true;
            }
        }
        
        // If collision is false, entity can move
        if (collisionOn == false) {
               
            switch(direction){
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
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
        
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {       
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    
    public int getDetected(Entity user, Entity target[], String targetName) {
        
        int index = 999;
        
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();
        
        switch(user.direction){
            case "up": nextWorldY = user.getTopY()-1; break;
            case "down": nextWorldY = user.getBottomY()+1; break;
            case "left": nextWorldX = user.getLeftX()-1; break;
            case "right": nextWorldX = user.getRightX()+1; break;
        }
        int col = nextWorldX/gwp.tileSize;
        int row = nextWorldY/gwp.tileSize;
        
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                if (target[i].getCol() == col && target[i].getRow() == row && target[i].name.equals(targetName)) {
                    index = 1;
                    break;
                }
            }
        }
        return index;
    }
    
    public void draw(Graphics2D g2) {
        
        BufferedImage image = null;
       
        int screenX = worldX - gwp.player.worldX + gwp.player.screenX;
        int screenY = worldY - gwp.player.worldY + gwp.player.screenY;
       
        if (worldX + gwp.tileSize > gwp.player.worldX - gwp.player.screenX &&
            worldX - gwp.tileSize < gwp.player.worldX + gwp.player.screenX &&
            worldY + gwp.tileSize > gwp.player.worldY - gwp.player.screenY &&
            worldY - gwp.tileSize < gwp.player.worldY + gwp.player.screenY) {
           
            switch (direction) {
            
            case "up": 
                if (spriteNum == 1) {image = up1;}
                if (spriteNum == 2) {image = up2;}
                if (spriteNum == 3) {image = up3;}            
                break;
            case "down": 
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
                if (spriteNum == 3) {image = down3;}
                break;
            case "left": 
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2) {image = left2;}
                if (spriteNum == 3) {image = left3;}
                break;
            case "right": 
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;}
                if (spriteNum == 3) {image = right3;}
                break;
            }
            
            // Monster HP bar
            if (type == 2 && hpBarOn == true) {
                
                double oneScale = (double)gwp.tileSize/maxHP;
                double hpBarValue = oneScale*hp;
                        
                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1, screenY-16, gwp.tileSize+2, 12);
                
                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
                
                hpBarCounter++;
                
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            
            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dying == true) {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gwp.tileSize, gwp.tileSize, null);
           
            changeAlpha(g2, 1f);
        }
    }
    public void dyingAnimation(Graphics2D g2) {
        
        dyingCounter++;
        
        int i = 5;
        
        if (dyingCounter <= i) {changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*8) {
            alive = false;
        }
    }
    
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    
    public BufferedImage setup(String imageName, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null; 
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/"+ imageName +".png"));
            image = uTool.scaleImage(image, width, height);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}