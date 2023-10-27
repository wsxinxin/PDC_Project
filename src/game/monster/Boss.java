/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.monster;

import game.entity.Entity;
import game.window.GameWorldPanel;
import java.util.Random;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

public class Boss extends Entity {
    
    GameWorldPanel gwp;
    // Boss constructor
    public Boss(GameWorldPanel gwp) {
        super(gwp);
        this.gwp = gwp;
        
        type = type_monster;
        name = "Boss";
        speed = 2;
        maxHP = 30;
        hp = maxHP;
        
        solidArea.x = 16;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        
        getImage();
    }
    // get Boss sprites
    public void getImage() {
        
        up1 = setup("monstersprites/Mini_boss", gwp.tileSize, gwp.tileSize);
        up2 = setup("monstersprites/Mini_boss_arms_up", gwp.tileSize, gwp.tileSize);
        up3 = setup("monstersprites/Mini_boss", gwp.tileSize, gwp.tileSize);
        down1 = setup("monstersprites/Mini_boss", gwp.tileSize, gwp.tileSize);
        down2 = setup("monstersprites/Mini_boss_arms_up", gwp.tileSize, gwp.tileSize);
        down3 = setup("monstersprites/Mini_boss", gwp.tileSize, gwp.tileSize);
        left1 = setup("monstersprites/Mini_boss", gwp.tileSize, gwp.tileSize);
        left2 = setup("monstersprites/Mini_boss_arms_up", gwp.tileSize, gwp.tileSize);
        left3 = setup("monstersprites/Mini_boss", gwp.tileSize, gwp.tileSize);
        right1 = setup("monstersprites/Mini_boss", gwp.tileSize, gwp.tileSize);
        right2 = setup("monstersprites/Mini_boss_arms_up", gwp.tileSize, gwp.tileSize); 
        right3 = setup("monstersprites/Mini_boss", gwp.tileSize, gwp.tileSize);
    }
    // set the basic AI actions 
    @Override
    public void setAction() {
        
        actionLockCounter++;
        
        if (actionLockCounter == 120){
            Random rand = new Random();
            int i = rand.nextInt(100)+1;
            
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            
            actionLockCounter = 0;
        }
    }
    // damage Reaction method, give a basic reaction when boss is attacked
    @Override
    public void damageReaction() {
        
        actionLockCounter = 0;
        direction = gwp.player.direction;
    }
    
}
