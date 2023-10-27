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
import game.entity.Entity;
import game.object.OBJ_Heart;
import game.window.GameWorldPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {
    
    GameWorldPanel gwp;
    Graphics2D g2;
    Font arial_20;
    BufferedImage heart_full, heart_half, heart_empty;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    public int subState = 0;
    
    public UI(GameWorldPanel gwp) {
        this.gwp = gwp;
        
        arial_20 = new Font("Arial", Font.PLAIN, 20);
        
        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gwp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    }
    
    public void addMessage(String text){
        
        message.add(text);
        messageCounter.add(0);
        
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(arial_20);
        g2.setColor(Color.white);
        
        // TITLE STATE
         if (gwp.gameState == gwp.titleState) {
            g2.setColor(new Color(0,0,0,255));
            g2.fillRect(0,0,gwp.screenWidth,gwp.screenHeight);
            subState = 0;
            drawTitleScreen();
        }
        // PLAY STATE
        if (gwp.gameState == gwp.playState) {
            drawMessage();
            drawPlayerLife();
        }
        // PAUSE STATE
        if (gwp.gameState == gwp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // CHARACTER STATE
        if (gwp.gameState == gwp.characterState) {
            drawPlayerLife();
            drawInventory();
        }
        // OPTIONS STATE
        if (gwp.gameState == gwp.optionsState) {
            drawPlayerLife();
            drawOptionsScreen();
        }
        // GAME OVER STATE
        if (gwp.gameState == gwp.gameOverState) {
            drawGameOverScreen();
        }
    }
    public void drawGameOverScreen() {
        
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gwp.screenWidth, gwp.screenHeight);
        
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
        
        text = "Game Over";
        
        // SHADOW
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gwp.tileSize*4;
        g2.drawString(text, x, y);
        // MAIN
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        
        // Retry
        g2.setFont(g2.getFont().deriveFont(50F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gwp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-40, y);
        }
        // Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-40, y);
        }
    }
    public void drawPlayerLife() {
        
        int x = gwp.tileSize/2;
        int y = gwp.tileSize/2;
        int i = 0;
        
        // DRAW MAX HP 
        while (i < gwp.player.maxHP/2) {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gwp.tileSize; 
        }
        
        // RESET
        x = gwp.tileSize/2;
        y = gwp.tileSize/2;
        i = 0;
        
        // DRAW CURRENT HP
        while (i < gwp.player.hp) {
            g2.drawImage(heart_half, x, y, null);  
            i++;
            if (i < gwp.player.hp) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gwp.tileSize;        
        }
    }
    public void drawTitleScreen() {
        
        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        String text = "PDC Pos Apocalyptic RPG(DEMO)";
        int x = getXforCenteredText(text);
        int y = gwp.tileSize*3;
        
        // SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);
        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        
        // CHARACTER IMAGE
        x = gwp.screenWidth/2 - (gwp.tileSize*2)/2;
        y += gwp.tileSize*2;
        g2.drawImage(gwp.player.down1, x, y, gwp.tileSize*2, gwp.tileSize*2, null);
        
        // MENU 
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
        
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gwp.tileSize*3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gwp.tileSize, y);
        }
        
        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gwp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gwp.tileSize, y);
        }
        
        text = "QUIT";
        x = getXforCenteredText(text);
        y += gwp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gwp.tileSize, y);
        }
        
    }
    public void drawPauseScreen() {
        
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gwp.screenHeight/2;
        
        g2.drawString(text, x, y);
    }
    public void drawInventory() {
        
        // FRAME
        int frameX = gwp.tileSize*12;
        int frameY = gwp.tileSize;       
        int frameWidth = gwp.tileSize*6;
        int frameHeight = gwp.tileSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        
        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gwp.tileSize + 3;
        
        // DRAW PLAYER'S ITEMS
        for (int i = 0; i < gwp.player.inventory.size(); i++) {
            g2.drawImage(gwp.player.inventory.get(i).down1, slotX, slotY, null);
            
            slotX += slotSize;
            
            if (i == 4 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        
        // CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gwp.tileSize;
        int cursorHeight = gwp.tileSize;
        
        // DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);  
        
        // DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gwp.tileSize*3;
        
        // DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gwp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));
        
        int itemIndex = getItemIndexOnSlot();
        
        if (itemIndex < gwp.player.inventory.size()) {
            
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            
            for(String line : gwp.player.inventory.get(itemIndex).description.split("\n")) {
                
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }
    public void drawOptionsScreen() {
        
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(25F));
        
        // SUB WINDOW
        int frameX = gwp.tileSize*6;
        int frameY = gwp.tileSize;
        int frameWidth = gwp.tileSize*8;
        int frameHeight = gwp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        switch(subState){
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX,frameY); break;
            case 2: options_control(frameX, frameY); break; 
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }
        
        gwp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY) {
        
        int textX;
        int textY;
        
        // TITLE
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gwp.tileSize;
        g2.drawString(text, textX, textY);
        
        // FULL SCREEN ON/OFF
        textX = frameX + gwp.tileSize;
        textY += gwp.tileSize*2;
        g2.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gwp.keyH.enterPressed == true) {
                if (gwp.fullScreenOn == false) {
                    gwp.fullScreenOn = true;
                }
                else if (gwp.fullScreenOn == true) {
                    gwp.fullScreenOn = false;
                } 
                subState = 1;
            }  
        }
        
        // CONTROL
        textY += gwp.tileSize;
        g2.drawString("Controls", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gwp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }
        
        // SAVE GAME
        textY += gwp.tileSize;
        g2.drawString("Save Game", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }
        
        // LOAD GAME
        textY += gwp.tileSize;
        g2.drawString("Load Game", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
        }
        
        // END GAME
        textY += gwp.tileSize;
        g2.drawString("Quit Game", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if (gwp.keyH.enterPressed == true) {
                subState = 3;
                commandNum = 0;
            }
        }
        
        // BACK 
        textY += gwp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
        }
        
        // FULL SCREEN CHECK BOX
        textX = frameX + gwp.tileSize*5;
        textY = frameY + gwp.tileSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gwp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }
        
       gwp.config.saveConfig();
    }
    public void options_fullScreenNotification(int frameX, int frameY) {
        
        int textX = frameX + gwp.tileSize;
        int textY = frameY + gwp.tileSize*3;
        
        currentDialogue = "The change will take \neffect after restarting \nthe game.";
        
        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        
        // BACK 
        textY = frameY + gwp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if (gwp.keyH.enterPressed == true) {
                subState = 0;
            }
        }
    }
    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;
        
        // TITLE
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gwp.tileSize;
        g2.drawString(text, textX, textY);
        
        textX = frameX + gwp.tileSize;
        textY += gwp.tileSize;
        g2.drawString("Move", textX, textY); textY += gwp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY += gwp.tileSize;
        g2.drawString("Inventory Screen", textX, textY); textY += gwp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gwp.tileSize;
        g2.drawString("Options", textX, textY); 
        
        textX = (int) (frameX + gwp.tileSize*5.5);
        textY = frameY + gwp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gwp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gwp.tileSize;
        g2.drawString("I", textX, textY); textY += gwp.tileSize;
        g2.drawString("P", textX, textY); textY += gwp.tileSize;
        g2.drawString("ESC", textX, textY); 
        
        // BACK
        textX = frameX + gwp.tileSize;
        textY = frameY + gwp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if (gwp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void options_endGameConfirmation(int frameX, int frameY) {
        
        int textX = frameX + gwp.tileSize;
        int textY = frameY + gwp.tileSize*3;
        
        currentDialogue = "Quit the game and \nreturn to the tile screen?";
        
        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        
        // YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gwp.tileSize*3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY); 
            if (gwp.keyH.enterPressed == true) {
                subState = 0;
                gwp.gameState = gwp.titleState;
                gwp.restart();
            }
        }
        // NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gwp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY); 
            if (gwp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 4;
            }
        }
    }
    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    } 
    public void drawMessage() {
        int messageX = gwp.tileSize;
        int messageY = gwp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        
        for(int i = 0; i <message.size(); i++) {
            
            if (message.get(i) != null) {
                
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);
                
                int counter = messageCounter.get(i) + 1; // messageCounter++
                messageCounter.set(i, counter); // set the counter to the array
                messageY += 50;
                
                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawSubWindow(int x, int y, int width, int height) {
       
        g2.setColor(new Color (0,0,0, 210));
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        g2.setColor(new Color (255,255,255));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25); 
    }
    public int getXforCenteredText(String text) {
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gwp.screenWidth/2 - length/2;
        return x;
    }
}