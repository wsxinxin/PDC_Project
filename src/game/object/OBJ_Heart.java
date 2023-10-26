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

public class OBJ_Heart extends Entity{
    public OBJ_Heart(GameWorldPanel gwp) {
        super(gwp); 
        
        name = "Heart";
        image = setup("objects/heart_full", gwp.tileSize, gwp.tileSize);
        image2 = setup("objects/heart_half", gwp.tileSize, gwp.tileSize);
        image3 = setup("objects/heart_empty", gwp.tileSize, gwp.tileSize);
    }
}
