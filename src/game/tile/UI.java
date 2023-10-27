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
    public int slotCol = 0;
    public int slotRow = 0;
    
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
    public void drawPauseScreen() {
        
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gwp.screenHeight/2;
        
        g2.drawString(text, x, y);
    }
    public void drawInventory() {
        
        // FRAME
        int frameX = gwp.tileSize*9;
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