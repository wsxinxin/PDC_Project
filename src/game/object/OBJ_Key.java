/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.object;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

import game.entity.Entity;
import game.window.GameWorldPanel;

public class OBJ_Key extends Entity {
    
    GameWorldPanel gwp;
    
    public OBJ_Key(GameWorldPanel gwp) {
        super(gwp);  
        
        this.gwp = gwp;
        
        type = type_consumable;
        name = "Key";
        down1 = setup("objects/key", gwp.tileSize, gwp.tileSize);
        description = "[" + name + "]\n open the bridge's\n chains door";
    }
    @Override
    public boolean use(Entity entity) {
        
        int objIndex = getDetected(entity, gwp.obj, "ChainDoor");
        
        if(objIndex != 999) {
            gwp.obj[objIndex] = null;
            return true;
        }
        else{
            return false;
        }
    }
}
