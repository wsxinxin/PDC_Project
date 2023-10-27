/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment2
 */
import game.entity.Entity;
import game.object.OBJ_Potion;
import game.object.OBJ_Key;
import game.window.GameWorldPanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
    GameWorldPanel gwp;
    
    public SaveLoad(GameWorldPanel gwp){
        this.gwp = gwp;
    }
    
    // get objects methods
    public Entity getObject(String itemName){
       Entity obj = null; 
       
       switch(itemName){
           case "Potion": obj = new OBJ_Potion(gwp); break;
           case "Key": obj = new OBJ_Key(gwp); break;
       }
       return obj;
    }
    
    // save method
    public void save(){
        try{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
        
        DataStorage ds = new DataStorage();
        //Player stats
        ds.hp = gwp.player.hp;
        //Player inventory
        for(int i = 0; i < gwp.player.inventory.size(); i++){
            ds.itemNames.add(gwp.player.inventory.get(i).name);
        }
        //Write the DataStorage object
        oos.writeObject(ds);
        
        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }
    // load method
    public void load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            
            //Read the DataStorage Object
            DataStorage ds = (DataStorage)ois.readObject();
            //Player stats
            gwp.player.hp = ds.hp;
            
            //Player inventory
            gwp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++){
                gwp.player.inventory.add(getObject(ds.itemNames.get(i)));
               
            }

        } catch (Exception e){
            System.out.println("Load Exception!");
        }
    }
}
