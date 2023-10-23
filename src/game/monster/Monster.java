/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.monster;

import game.entity.Entity;
import game.window.GameWorldPanel;
import java.util.Random;

/**
 *
 * @author Christian
 */
public class Monster extends Entity{

    public Monster(GameWorldPanel gwp) {
        super(gwp);
        
        name = "Gloon";
        speed = 1;
        maxHP = 4;
        hp = maxHP;
        
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
    public void getImage(){
        
        up1 = setup("monstersprites/Goons.png");
        up2 = setup("monstersprites/Goons.png");
        down1 = setup("monstersprites/Goons.png");
        down2 = setup("monstersprites/Goons.png");
        left1 = setup("monstersprites/Goons.png");
        left2 = setup("monstersprites/Goons.png");
        right1 = setup("monstersprites/Goons.png");
        right2 = setup("monstersprites/Goons.png");
 
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
    
}
