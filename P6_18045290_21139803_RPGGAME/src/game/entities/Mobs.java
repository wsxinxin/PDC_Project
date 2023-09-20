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
import game.content.GameContent;
import game.main.GameMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Mobs extends GameContent{
    // Attributes
    private String mobName;
    private int mobHealth;
    private int mobDamage;
    private int mobLevel;
    
    //Mobs constructor
    public Mobs(int mobHealth, int mobDamage, int mobLevel, String name, String weapon, String consumable) {
        super(name, weapon, consumable);
        this.mobName = name;
        this.mobHealth = mobHealth;
        this.mobDamage = mobDamage;
        this.mobLevel = mobLevel;
    }
    
    /*public ArrayList<Mobs> generateRandomMob() {
        
        ArrayList<Mobs> mobList = new ArrayList<>();
        
        Mobs goblin = new Mobs(50, 3, 1, "Goblin", "Axe", "Potion");
        Mobs orc = new Mobs(50, 5, 2, "Orc", "Sword", "Potion");
        Mobs demon = new Mobs(50, 8, 3, "Demon", "Spear", "Potion");
        
        // Add the Mobs objects to the ArrayList
        mobList.add(goblin);
        mobList.add(orc);
        mobList.add(demon);

        return mobList; 
    }*/
    
   /* public Mobs getRandomMob(ArrayList<Mobs> mobList) {
        // Use a random number generator to select a random index
        Random rand = new Random();
        int randomIndex = rand.nextInt(mobList.size());

        // Return the randomly selected Mobs object
        return mobList.get(randomIndex);
    }*/
    
    //Method to get the level of the mob
    public int getMobLevel()
    {
        return mobLevel;
    }

    // Method to get the name of the mob
    public String getMobName() {
        return mobName;
    }

    // Method to get the health of the mob
    public int getMobHealth() {
        return mobHealth;
    }

    // Method to get the damage of the mob
    public int getMobDamage() {
        Random rand = new Random();
        int minDamage = rand.nextInt(1)+1;
        int maxDamage = rand.nextInt(5)+1;
        return (maxDamage - minDamage + 1) + minDamage;
    }

    // Method to attack the player
    public void attack(Player player) {
        System.out.println("The " + mobName + " attacks the: " + player.getPlayerName());
        player.decreaseHP(mobDamage);
    }
    
    //idk mob attack power???
    public int getAttackPower()
    {
        return mobLevel * 3;
    }
    
    //I think we should remake the "take damage" to decreaseHP
    public void decreaseHP(int damage)
    {
        mobHealth -= damage;
        if (mobHealth < 0)
        {
            mobHealth = 0;
        }
    }
    
    //return if mobs is alive
    public boolean isAlive()
    {
        return mobHealth > 0;
    }
    
    //generate the drops 
    public List<String> lootDrop()
    {
        List<String> loot = new ArrayList<>();
        Random rand = new Random();
        if(rand.nextDouble() < 0.5)
        {
            loot.add("Health Potion");
        }
        if(rand.nextDouble() < 0.3)
        {
            loot.add("Sword");
        }
        return loot;
    }

}