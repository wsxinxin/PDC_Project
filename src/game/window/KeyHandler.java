/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.window;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */

import java.awt.event.*;

public class KeyHandler implements KeyListener{

    GameWorldPanel gwp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GameWorldPanel gwp) {
        this.gwp = gwp;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        int code = ke.getKeyCode();
        
        // PLAY STATE
        if (gwp.playState == gwp.gameState) {
            playState(code);
        }
        // PAUSE STATE
        else if (gwp.gameState == gwp.pauseState) {
            pauseState(code);
        }
        // CHARACTER STATE
        else if (gwp.gameState == gwp.characterState) {
            characterState(code);
        }
    }
    
    public void playState(int code) {
              
        if(code == KeyEvent.VK_W) {
        upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P) {                
           gwp.gameState = gwp.pauseState;
        }
        if (code == KeyEvent.VK_I) {
            gwp.gameState = gwp.characterState;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gwp.executeGameMenu(); // Call the method to execute the menu
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }    
    }
    public void pauseState(int code) {
        if(code == KeyEvent.VK_P) {                
            gwp.gameState = gwp.playState;
        }
    }
    public void characterState(int code) {
        if(code == KeyEvent.VK_I) {                
            gwp.gameState = gwp.playState;
        }
        if(code == KeyEvent.VK_W) {
            if(gwp.ui.slotRow != 0) {
                gwp.ui.slotRow--;
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gwp.ui.slotCol != 0) {
                gwp.ui.slotCol--;
            }
        }
        if(code == KeyEvent.VK_S) {
            if(gwp.ui.slotRow != 3) {
                gwp.ui.slotRow++;
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gwp.ui.slotCol != 4) {
                gwp.ui.slotCol++;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            gwp.player.selectItem();
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