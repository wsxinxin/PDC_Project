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

public class OBJ_Potion extends Entity {
    
    GameWorldPanel gwp;
    
    public OBJ_Potion(GameWorldPanel gwp) {
        super(gwp);
        
        this.gwp = gwp;
        
        value = 5;
        type = type_consumable;
        name = "Potion";
        down1 = setup("objects/syringe_with_potion", gwp.tileSize, gwp.tileSize); 
        description = "[" + name + "]\n heal your life by " + value + ".";
    }
    @Override
    public boolean use(Entity entity) {
        
        entity.hp += value;
        return true;
    }
}