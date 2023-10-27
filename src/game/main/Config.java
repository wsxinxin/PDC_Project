/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.main;

import game.window.GameWorldPanel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author wangs
 */
public class Config {
    GameWorldPanel gwp;
    
    public Config(GameWorldPanel gwp){
        this.gwp = gwp;
    }
    
    public void saveConfig(){
       try{
           BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
           //Full Screen setting
           if(gwp.fullScreenOn == true)
           {
               bw.write("On");
           }
           if(gwp.fullScreenOn == false)
           {
               bw.write("Off");
           }
           bw.newLine();
           
       } catch (IOException e){
           e.printStackTrace();
       }
    }
    
    public void loadConfig() {
        try{
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String s = br.readLine();
            
            //FullScreen 
            if(s.equals("On")){
                gwp.fullScreenOn = true;
            }
            if(s.equals("Off")){
                gwp.fullScreenOn = false;
            }
            
            br.close();
        }   catch (Exception e){
            e.printStackTrace();
        }
    }
}
