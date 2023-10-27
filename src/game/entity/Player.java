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

import game.object.*;
import game.window.GameWorldPanel;
import game.window.KeyHandler;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public final class Player extends Entity{
    
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

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
        
        attackArea.width = 36;
        attackArea.height = 36;
        
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues() {
        worldX = gwp.tileSize*18;
        worldY = gwp.tileSize*19;
        speed = 4;
        direction = "down";
        
        // PLAYER STATUS
        maxHP = 10;
        hp = maxHP;
    }
    public void setItems() {
        
        inventory.clear();
        inventory.add(new OBJ_Potion(gwp));
        inventory.add(new OBJ_Potion(gwp));
    } 
    public void getPlayerImage(){
    
        up1 = setup("playersprites/Sprite-0001", gwp.tileSize, gwp.tileSize); 
        up2 = setup("playersprites/Sprite-0009", gwp.tileSize, gwp.tileSize);
        up3 = setup("playersprites/Sprite-0010", gwp.tileSize, gwp.tileSize);
                
        down1 = setup("playersprites/Sprite-0004", gwp.tileSize, gwp.tileSize);
        down2 = setup("playersprites/Sprite-0005", gwp.tileSize, gwp.tileSize);        
        down3 = setup("playersprites/Sprite-0006", gwp.tileSize, gwp.tileSize); 
                
        left1 = setup("playersprites/Sprite-0003", gwp.tileSize, gwp.tileSize);        
        left2 = setup("playersprites/Sprite-0011", gwp.tileSize, gwp.tileSize);        
        left3 = setup("playersprites/Sprite-0012", gwp.tileSize, gwp.tileSize);
                    
        right1 = setup("playersprites/Sprite-0002", gwp.tileSize, gwp.tileSize);        
        right2 = setup("playersprites/Sprite-0007", gwp.tileSize, gwp.tileSize);        
        right3 = setup("playersprites/Sprite-0008", gwp.tileSize, gwp.tileSize);           
    }    
    public void getPlayerAttackImage() {
        
        attackUp1 = setup("playersprites/attacking_up", gwp.tileSize, gwp.tileSize*2);
        attackUp2 = setup("playersprites/attacking_up", gwp.tileSize, gwp.tileSize*2);
        
        attackDown1 = setup("playersprites/attacking_down", gwp.tileSize, gwp.tileSize*2);
        attackDown2 = setup("playersprites/attacking_down", gwp.tileSize, gwp.tileSize*2);
        
        attackLeft1 = setup("playersprites/Sprite-0001", gwp.tileSize*2, gwp.tileSize);
        attackLeft2 = setup("playersprites/Sprite-0001", gwp.tileSize*2, gwp.tileSize);
        
        attackRight1 = setup("playersprites/Sprite-0001", gwp.tileSize*2, gwp.tileSize);
        attackRight2 = setup("playersprites/Sprite-0001", gwp.tileSize*2, gwp.tileSize);
    }    
    @Override
    public void update() {
        
        if (attacking == true) {
            attacking();
        }
        else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
        
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
            else if (keyH.enterPressed = true) {
                attacking = true;
            }
            else if (keyH.enterPressed = true) {
                attacking = true;
            }
            
            // CHECK TILE COLLISION
            collisionOn = false;
            gwp.cChecker.checkTile(this);
            
            // CHECK OBJECT COLLISION
            int objIndex = gwp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            // CHECK MONSTER COLLISION
            int monsterIndex = gwp.cChecker.checkEntity(this, gwp.monster);
            contactMonster(monsterIndex);
            
            // If collision is false, player can move
            if (collisionOn == false && keyH.enterPressed == false) {
               
                switch(direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
                }
            }
            
            gwp.keyH.enterPressed = false;
            
            spriteCounter++;
            if (spriteCounter > 12) {
                
                switch (spriteNum) {
                case 1: spriteNum = 2; break;
                case 2: spriteNum = 3; break;
                case 3: spriteNum = 1; break;
                default: break;
                }
                spriteCounter = 0;
            }
        }
        
        if (invincible == true) {
            
            invincibleCounter++;
            if (invincibleCounter > 60) {       
                invincible = false;
                invincibleCounter = 0;
            }
        }
        
        if(hp > maxHP) {
            hp = maxHP;
        }
    }
    public void attacking() {
        
        spriteCounter++;
        
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;
            
            // Save the current worldX , worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            // Adjust player's worldX/Y for the attackArea
            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            
            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            // Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gwp.cChecker.checkEntity(this, gwp.monster);
            damageMonster(monsterIndex);
            
            // After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;         
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int i) {
        
        if (i != 999) {
            
            if (gwp.obj[i].type == type_obstacle) {
                if (keyH.enterPressed == true) {
                    gwp.obj[i].interact();
                }
            }
            else if (gwp.obj[i].type == type_pickupOnly) {
                
                gwp.obj[i].use(this);
                gwp.obj[i] = null;
            }
            else {
                String text;
                
                if (inventory.size() != maxInventorySize) {
                   inventory.add(gwp.obj[i]);
                   text = "Got a " + gwp.obj[i].name + "!";
                }
                else {
                    text = "You cannot carry anymore item!";
                }
                gwp.ui.addMessage(text);
                gwp.obj[i] = null;  
            }   
        }
    }
    public void contactMonster(int i) {
        
        if (i != 999) {
            
            if (invincible == false & gwp.monster[i].dying == false) {
                hp -= 1;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i) {
        
        if (i != 999) {
            
            if (gwp.monster[i].invincible == false) {
                gwp.monster[i].hp -= 1;
                gwp.monster[i].invincible = true;
                
                if (gwp.monster[i].hp <= 0) {
                    gwp.monster[i].dying = true;
                    gwp.monster[i].damageReaction();
                }
            }
        }
    }
    public void selectItem() {
        
        int itemIndex = gwp.ui.getItemIndexOnSlot();
        
        if (itemIndex < inventory.size()) {
            
            Entity selectedItem = inventory.get(itemIndex);
            
            if (selectedItem.type == type_consumable) {
                
                if(selectedItem.use(this) == true) {
                    
                    inventory.remove(itemIndex);
                }
            }
        }
    }
    @Override
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        switch (direction) {
            
        case "up": 
            if (attacking == false) {
                if (spriteNum == 1){image = up1;}
                if (spriteNum == 2){image = up2;}
                if (spriteNum == 3){image = up3;} 
            }
            if (attacking == true) {
                tempScreenY = screenY - gwp.tileSize;
                if (spriteNum == 1){image = attackUp1;}
                if (spriteNum == 2){image = attackUp2;}
            }
            break;
        case "down": 
            if (attacking == false) {
                if (spriteNum == 1){image = down1;}
                if (spriteNum == 2){image = down2;}
                if (spriteNum == 3){image = down3;} 
            }
            if (attacking == true) {
                if (spriteNum == 1){image = attackDown1;}
                if (spriteNum == 2){image = attackDown2;}
            }
            break;
        case "left": 
            if (attacking == false) {
                if (spriteNum == 1){image = left1;}
                if (spriteNum == 2){image = left2;}
                if (spriteNum == 3){image = left3;} 
            }
            if (attacking == true) {
                tempScreenX = screenX - gwp.tileSize;
                if (spriteNum == 1){image = attackLeft1;}
                if (spriteNum == 2){image = attackLeft2;}
            }
            break;
        case "right": 
            if (attacking == false) {
                if (spriteNum == 1){image = right1;}
                if (spriteNum == 2){image = right2;}
                if (spriteNum == 3){image = right3;} 
            }
            if (attacking == true) {
                if (spriteNum == 1){image = attackRight1;}
                if (spriteNum == 2){image = attackRight2;}
            }
            break;
        }
        
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }        
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        
        // REST ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}