/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.tile;

import game.entity.Entity;
import game.window.GameWorldPanel;

/**
 *
 * @author Christian
 */
public class CollisionChecker {
    
    GameWorldPanel gwp;

    public CollisionChecker(GameWorldPanel gwp) {
        this.gwp = gwp;
    }
    
    public void checkTile(Entity entity){
        
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftX/gwp.tileSize;
        int entityRightCol = entityRightX/gwp.tileSize;
        int entityTopRow = entityTopY/gwp.tileSize;
        int entityBottomRow = entityBottomY/gwp.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction){
            
        case "up":
            entityTopRow = (entityTopY - entity.speed)/gwp.tileSize;
            tileNum1 = gwp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gwp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if (gwp.tileM.tile[tileNum1].collision == true || gwp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "down":
            entityBottomRow = (entityBottomY + entity.speed)/gwp.tileSize;
            tileNum1 = gwp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gwp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gwp.tileM.tile[tileNum1].collision == true || gwp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "left":
            entityLeftCol = (entityLeftX - entity.speed)/gwp.tileSize;
            tileNum1 = gwp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gwp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            if (gwp.tileM.tile[tileNum1].collision == true || gwp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "right":
            entityRightCol = (entityRightX + entity.speed)/gwp.tileSize;
            tileNum1 = gwp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gwp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gwp.tileM.tile[tileNum1].collision == true || gwp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
            
        }

    }
    // Monster collision
    public int checkEntity(Entity entity, Entity[] target) {
        
        int index = 999;
        
        for (int i = 0; i < target.length; i++) {
            
            if (target[i] != null) {
                
                // Get enetity's solid area position
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                
                target[i].solidArea.x = target[i].x + target[i].solidArea.x;
                target[i].solidArea.y = target[i].y + target[i].solidArea.y;
                
                switch (entity.direction) {
                    
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(target[i].solidArea)) {
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(target[i].solidArea)) {
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(target[i].solidArea)) {
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(target[i].solidArea)) {
                        entity.collisionOn = true;
                        index = i;
                    break;
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
    
    public void checkPlayer(Entity entity){
        
        entity.solidArea.x = entity.x + entity.solidArea.x;
        entity.solidArea.y = entity.y + entity.solidArea.y;
                
        gwp.player.solidArea.x = gwp.player.x + gwp.player.solidArea.x;
        gwp.player.solidArea.y = gwp.player.y + gwp.player.solidArea.y;
                
        switch (entity.direction) {
                    
        case "up":
            entity.solidArea.y -= entity.speed;
            if(entity.solidArea.intersects(gwp.player.solidArea)) {
                entity.collisionOn = true;
            }
            break;
        case "down":
            entity.solidArea.y += entity.speed;
            if(entity.solidArea.intersects(gwp.player.solidArea)) {
                entity.collisionOn = true;
            }
            break;
        case "left":
            entity.solidArea.x -= entity.speed;
            if(entity.solidArea.intersects(gwp.player.solidArea)) {
                entity.collisionOn = true;
            }
            break;
        case "right":
            entity.solidArea.x += entity.speed;
            if(entity.solidArea.intersects(gwp.player.solidArea)) {
                entity.collisionOn = true;
            break;
            }
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gwp.player.solidArea.x = gwp.player.solidAreaDefaultX;
        gwp.player.solidArea.y = gwp.player.solidAreaDefaultY;
    }
}
