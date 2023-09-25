/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.entities;

/**
 * @author Andrew Wang 18045290
 * @author Christian Costa Gomes Jorge 21139803
 * COMP603
 * Assignment1
 */
public class Drops extends Mobs{
    
    private String dropName;
    private int items;
    
    //drops constructor
    public Drops(String dropName, int items, int mobHealth, int mobDamage, int mobLevel, String name, String weapon, String consumable) {
        super(mobHealth, mobDamage, mobLevel, name, weapon, consumable);
        this.dropName = dropName;
        this.items = items;
    }
    
    //display the loot droped 
    public void calculateDrops(){
        if(getMobHealth() == 0 || getMobHealth() <= 0)
        {
            lootDrop();
            System.out.println("You aReceived "+items+ "item called: " +this.dropName);
        } 
    }
}