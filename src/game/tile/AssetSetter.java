/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.tile;

import game.monster.Monster;
import game.window.GameWorldPanel;

/**
 *
 * @author Christian
 */
public class AssetSetter {
   
    GameWorldPanel gwp;

    public AssetSetter(GameWorldPanel gwp) {
        this.gwp = gwp;
    }
    
    public void setMonster(){
        
        gwp.monster[0] = new Monster(gwp);
        gwp.monster[0].x = gwp.tileSize*13;
        gwp.monster[0].y = gwp.tileSize*3;
        
        gwp.monster[1] = new Monster(gwp);
        gwp.monster[1].x = gwp.tileSize*5;
        gwp.monster[1].y = gwp.tileSize*2;
        
        gwp.monster[0] = new Monster(gwp);
        gwp.monster[0].x = gwp.tileSize*15;
        gwp.monster[0].y = gwp.tileSize*7;
    }
}
