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
    
    // Key Handler constructor
    public KeyHandler(GameWorldPanel gwp) {
        this.gwp = gwp;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }
    // key pressed according with each game state
    @Override
    public void keyPressed(KeyEvent ke) {
        
        int code = ke.getKeyCode();
        
        // TITLE STATE
        if (gwp.titleState == gwp.gameState) {
            titleState(code);
        }
        // PLAY STATE
        else if (gwp.playState == gwp.gameState) {
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
        else if (gwp.gameState == gwp.optionsState) {
            optionsState(code);
        }
        // GAME OVER STATE
        else if (gwp.gameState == gwp.gameOverState) {
            gameOverState(code);
        }
    }
    // title state keys
    public void titleState(int code) {
        
        if (code == KeyEvent.VK_W) {
            gwp.ui.commandNum--;
            if (gwp.ui.commandNum < 0) {
                gwp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_S) {
            gwp.ui.commandNum++;
            if (gwp.ui.commandNum > 2) {
                gwp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if(gwp.ui.commandNum == 0) {
                gwp.gameState = gwp.playState;
            }
            if(gwp.ui.commandNum == 1) {
                // add later
            }
            if(gwp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }
    // play state keys
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
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }  
        if (code == KeyEvent.VK_ESCAPE) {
            gwp.gameState = gwp.optionsState;
        } 
        
    }
    // pause state keys
    public void pauseState(int code) {
        if(code == KeyEvent.VK_P) {                
            gwp.gameState = gwp.playState;
        }
    }
    // character state (inventory) keys
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
    // option state keys
    public void optionsState(int code) {
        
        if (code == KeyEvent.VK_ESCAPE) {
            gwp.gameState = gwp.playState;  
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        
        int maxCommandNum = 0;
        switch(gwp.ui.subState){
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }
        
        if (code == KeyEvent.VK_W) {
            gwp.ui.commandNum--;
            if (gwp.ui.commandNum < 0) {
                gwp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gwp.ui.commandNum++;
            if (gwp.ui.commandNum > maxCommandNum) {
                gwp.ui.commandNum = 0;
            }
        }
    }
    // game over state keys 
    public void gameOverState(int code) {
        
        if (code == KeyEvent.VK_W) {
            gwp.ui.commandNum--;
            if (gwp.ui.commandNum < 0) {
                gwp.ui.commandNum = 1;
            }
        }
        if (code == KeyEvent.VK_S) {
            gwp.ui.commandNum++;
            if (gwp.ui.commandNum > 1) {
                gwp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gwp.ui.commandNum == 0) {
                gwp.gameState = gwp.playState;
                gwp.retry();
            }
            else if (gwp.ui.commandNum == 1) {
                gwp.gameState = gwp.titleState;
                gwp.restart();
            }
        }
    }
    // key released methods
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