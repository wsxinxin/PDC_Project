/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.entity;

import game.window.GameWorldPanel;
import game.window.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Christian
 */
public class Player extends Entity{
    
    GameWorldPanel gwp;
    KeyHandler keyH;

    public Player(GameWorldPanel gwp, KeyHandler keyH) {
        this.gwp = gwp;
        this.keyH = keyH;
        
        setDefaultValues();
    }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }
    
    public void update(){
        
        if (keyH.upPressed == true) {
            y -= speed;
        }
        else if (keyH.downPressed == true) {
            y += speed;
        }
        else if (keyH.leftPressed == true) {
            x -= speed;
        }
        else if (keyH.rightPressed == true) {
            x += speed;
        }
    }
    public void draw(Graphics2D g2){
        
        g2.setColor(Color.white);
        
        g2.fillRect(x, y, gwp.tileSize, gwp.tileSize);
    }
}
