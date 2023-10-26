/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.monster;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

import game.entity.Entity;
import game.window.GameWorldPanel;
import java.util.Random;

public class Monster extends Entity {

    GameWorldPanel gwp;
    
    public Monster(GameWorldPanel gwp) {
        super(gwp);
        
        this.gwp = gwp;
        
        type = type_monster;
        name = "Gloon";
        speed = 1;
        maxHP = 10;
        hp = maxHP;
        
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
    public void getImage() {
        
        up1 = setup("monstersprites/Goons", gwp.tileSize, gwp.tileSize);
        up2 = setup("monstersprites/Goons-0001", gwp.tileSize, gwp.tileSize);
        up3 = setup("monstersprites/Goons-0002", gwp.tileSize, gwp.tileSize);
        down1 = setup("monstersprites/Goons", gwp.tileSize, gwp.tileSize);
        down2 = setup("monstersprites/Goons-0001", gwp.tileSize, gwp.tileSize);
        down3 = setup("monstersprites/Goons-0002", gwp.tileSize, gwp.tileSize);
        left1 = setup("monstersprites/Goons", gwp.tileSize, gwp.tileSize);
        left2 = setup("monstersprites/Goons-0001", gwp.tileSize, gwp.tileSize);
        left3 = setup("monstersprites/Goons-0002", gwp.tileSize, gwp.tileSize);
        right1 = setup("monstersprites/Goons", gwp.tileSize, gwp.tileSize);
        right2 = setup("monstersprites/Goons-0001", gwp.tileSize, gwp.tileSize);
        right3 = setup("monstersprites/Goons-0002", gwp.tileSize, gwp.tileSize);
    }
    
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
    
    @Override
    public void damageReaction() {
        
        actionLockCounter = 0;
        direction = gwp.player.direction;
    }
}