/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.object;

import game.entity.Entity;
import game.window.GameWorldPanel;

/**
 *
 * @author Christian
 */
public class OBJ_ChainDoor extends Entity {
    
    GameWorldPanel gwp;
    
    public OBJ_ChainDoor(GameWorldPanel gwp) {
        super(gwp);
        
        this.gwp = gwp;
        
        type = type_obstacle;
        name = "ChainDoor";
        down1 = setup("objects/chain_door", gwp.tileSize, gwp.tileSize);
        collision = true;
        
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    } 
}
