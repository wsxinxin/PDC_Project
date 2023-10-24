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
public class OBJ_Key extends Entity{
    
    public OBJ_Key(GameWorldPanel gwp){
        super(gwp);   
        
        name = "Key";
        down1 = setup("objects/key");
        
    }
}
