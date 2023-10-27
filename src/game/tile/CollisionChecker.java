/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.tile;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

import game.entity.Entity;
import game.window.GameWorldPanel;

public class CollisionChecker {
    
    GameWorldPanel gwp;
    // Collision checker constructor
    public CollisionChecker(GameWorldPanel gwp) {
        this.gwp = gwp;
    }
    
    // check if the tiles are solid if tiles are solid collision happens
    public void checkTile(Entity entity){
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX/gwp.tileSize;
        int entityRightCol = entityRightWorldX/gwp.tileSize;
        int entityTopRow = entityTopWorldY/gwp.tileSize;
        int entityBottomRow = entityBottomWorldY/gwp.tileSize;
        
        int tileNum1, tileNum2;
        
        // define the hit box of the entity to check when it collides with some solid object
        switch(entity.direction) {
            
        case "up":
            entityTopRow = (entityTopWorldY - entity.speed)/gwp.tileSize;
            tileNum1 = gwp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gwp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if (gwp.tileM.tile[tileNum1].collision == true || gwp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed)/gwp.tileSize;
            tileNum1 = gwp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gwp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gwp.tileM.tile[tileNum1].collision == true || gwp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed)/gwp.tileSize;
            tileNum1 = gwp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gwp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            if (gwp.tileM.tile[tileNum1].collision == true || gwp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "right":
            entityRightCol = (entityRightWorldX + entity.speed)/gwp.tileSize;
            tileNum1 = gwp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gwp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gwp.tileM.tile[tileNum1].collision == true || gwp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break; 
        }
    }
    // check object methods check when player collides with a object
    public int checkObject(Entity entity, boolean player) {
        
        int index = 999;
        
        for (int i = 0; i < gwp.obj.length; i++) {
            
            if (gwp.obj[i] != null) {
                
                // Get enetity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Get the object's solid area position
                gwp.obj[i].solidArea.x = gwp.obj[i].worldX + gwp.obj[i].solidArea.x;
                gwp.obj[i].solidArea.y = gwp.obj[i].worldY + gwp.obj[i].solidArea.y;
                
                switch (entity.direction) {    
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }
                if(entity.solidArea.intersects(gwp.obj[i].solidArea)) {
                    if (gwp.obj[i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gwp.obj[i].solidArea.x = gwp.obj[i].solidAreaDefaultX;
                gwp.obj[i].solidArea.y = gwp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    
    // Monster collision checks when player collides with a monster
    public int checkEntity(Entity entity, Entity[] target) {
        
        int index = 999;
        
        for (int i = 0; i < target.length; i++) {
            
            if (target[i] != null) {
                
                // Get enetity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Get the target's solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
                
                switch (entity.direction) {    
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }
                if(entity.solidArea.intersects(target[i].solidArea)) {
                    if (target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    // check player check if some entity collides with the player
    public boolean checkPlayer(Entity entity) {
        
        boolean contactPlayer = false;
        
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
        gwp.player.solidArea.x = gwp.player.worldX + gwp.player.solidArea.x;
        gwp.player.solidArea.y = gwp.player.worldY + gwp.player.solidArea.y;
                
        switch (entity.direction) {           
            case "up": entity.solidArea.y -= entity.speed; break;
            case "down": entity.solidArea.y += entity.speed; break;
            case "left": entity.solidArea.x -= entity.speed; break;
            case "right": entity.solidArea.x += entity.speed; break;
        }
        if(entity.solidArea.intersects(gwp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gwp.player.solidArea.x = gwp.player.solidAreaDefaultX;
        gwp.player.solidArea.y = gwp.player.solidAreaDefaultY;
        
        return contactPlayer;
    }
}