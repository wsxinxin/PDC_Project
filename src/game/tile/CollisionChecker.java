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
}
