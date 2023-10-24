/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.tile;

import game.window.GameWorldPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

/**
 *
 * @author Christian
 */
public class UI {
    
    GameWorldPanel gwp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GameWorldPanel gwp) {
        this.gwp = gwp;
        
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }
    
    public void showMessage(String text){
        
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        
        if (gwp.gameState == gwp.playState){
            // Do playState stuff later
        }
        if (gwp.gameState == gwp.pauseState){
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){
        
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gwp.screenHeight/2;
        
        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text){
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gwp.screenWidth/2 - length/2;
        return x;
    }
}
