/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.object;

/**
 *
 * @author Christian
 */

import game.entity.Entity;
import game.window.GameWorldPanel;

public class OBJ_Potion extends Entity {
    
    GameWorldPanel gwp;
    int value = 5;

    public OBJ_Potion(GameWorldPanel gwp) {
        super(gwp);
        
        this.gwp = gwp;
        
        type = type_consumable;
        name = "Potion";
        down1 = setup("objects/syringe_with_potion", gwp.tileSize, gwp.tileSize); 
        description = "[" + name + "]\n heal your life by " + value + ".";
    }
    @Override
    public boolean use(Entity entity) {
        
        entity.hp += value;
        if (gwp.player.hp > gwp.player.maxHP) {
            gwp.player.hp = gwp.player.maxHP;
        }
        return true;
    }
}
