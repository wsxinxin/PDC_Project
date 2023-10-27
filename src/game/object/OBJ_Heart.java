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

public class OBJ_Heart extends Entity {
    
    GameWorldPanel gwp;
    
    // Object Heart constructor
    public OBJ_Heart(GameWorldPanel gwp) {
        super(gwp); 
        
        this.gwp = gwp;
        
        type = type_pickupOnly;
        name = "Heart";
        value = 2;
        down1 = setup("objects/heart_full", gwp.tileSize, gwp.tileSize);
        image = setup("objects/heart_full", gwp.tileSize, gwp.tileSize);
        image2 = setup("objects/heart_half", gwp.tileSize, gwp.tileSize);
        image3 = setup("objects/heart_empty", gwp.tileSize, gwp.tileSize);
    }
    // Use methods uses the heart to add more life to the player
    @Override
    public boolean use(Entity entity) {
        gwp.ui.addMessage("Life +" + value);
        entity.hp += value;
        return true;
    }
}