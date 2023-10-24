/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.window;

import java.awt.event.*;

/**
 *
 * @author chris
 */
public class KeyHandler implements KeyListener{

    GameWorldPanel gwp;

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GameWorldPanel gwp) {
        this.gwp = gwp;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int code = ke.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            if (gwp.gameState == gwp.playState){
                gwp.gameState = gwp.pauseState;
            }
            else if (gwp.gameState == gwp.pauseState){
                gwp.gameState = gwp.playState;
            }
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gwp.executeGameMenu(); // Call the method to execute the menu
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
        int code = ke.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }   
}