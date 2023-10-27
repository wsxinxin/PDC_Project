/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.tile;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

import game.monster.Boss;
import game.monster.Monster;
import game.object.*;
import game.window.GameWorldPanel;

// Asset setter constructor 
public class AssetSetter {
   
    GameWorldPanel gwp;

    public AssetSetter(GameWorldPanel gwp) {
        this.gwp = gwp;
    }
    // create objects to be displayed in the game world
    public void setObject() {
        
        int i = 0;
        gwp.obj[i] = new OBJ_ChainDoor(gwp);
        gwp.obj[i].worldX = gwp.tileSize*26;
        gwp.obj[i].worldY = gwp.tileSize*25;
        i++;
    }
    // create monsters and the boss to be displayed in the game world
    public void setMonster() {
        
        int i = 0;
        gwp.monster[i] = new Monster(gwp);
        gwp.monster[i].worldX = gwp.tileSize*24;
        gwp.monster[i].worldY = gwp.tileSize*22;
        i++;
        gwp.monster[i] = new Monster(gwp);
        gwp.monster[i].worldX = gwp.tileSize*28;
        gwp.monster[i].worldY = gwp.tileSize*27;
        i++;
        gwp.monster[i] = new Monster(gwp);
        gwp.monster[i].worldX = gwp.tileSize*23;
        gwp.monster[i].worldY = gwp.tileSize*19;
        i++;
        gwp.monster[i] = new Monster(gwp);
        gwp.monster[i].worldX = gwp.tileSize*28;
        gwp.monster[i].worldY = gwp.tileSize*20;
        i++;
        gwp.monster[i] = new Boss(gwp);
        gwp.monster[i].worldX = gwp.tileSize*18;
        gwp.monster[i].worldY = gwp.tileSize*25;
    }
}