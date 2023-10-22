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
public class BattleDetection {
    GameWorldPanel gwp;
    Entity entity;
    
    public BattleDetection(GameWorldPanel gwp) {
        this.gwp = gwp;
    }

    public boolean isPlayerInSpecificTile() {
        int playerLeftX = entity.x + entity.solidArea.x;
        int playerRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int playerTopY = entity.y + entity.solidArea.y;
        int playerBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;
        
        int playerLeftCol = playerLeftX / gwp.tileSize;
        int playerRightCol = playerRightX / gwp.tileSize;
        int playerTopRow = playerTopY / gwp.tileSize;
        int playerBottomRow = playerBottomY / gwp.tileSize;
        
        // Define the specific tile location (e.g., column 2 and row 13)
        int specificTileX = 13;
        int specificTileY = 2;
        
        // Check if any part of the player's solid area overlaps with the specific tile
        boolean isPlayerInTile = (playerRightCol >= specificTileX && playerLeftCol <= specificTileX)
            && (playerBottomRow >= specificTileY && playerTopRow <= specificTileY);
        
        return isPlayerInTile;
    }

}
