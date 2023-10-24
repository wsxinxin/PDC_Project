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
        gwp.monster[0].worldX = gwp.tileSize*24;
        gwp.monster[0].worldY = gwp.tileSize*22;
        
        gwp.monster[1] = new Monster(gwp);
        gwp.monster[1].worldX = gwp.tileSize*28;
        gwp.monster[1].worldY = gwp.tileSize*27;
        
        /*gwp.monster[2] = new Monster(gwp);
        gwp.monster[2].worldX = gwp.tileSize*15;
        gwp.monster[2].worldY = gwp.tileSize*7;*/
    }
}
