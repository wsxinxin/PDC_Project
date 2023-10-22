/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.entity;

import game.window.GameBattlePanel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Christian
 */
public class Monster extends Entity{
    
    GameBattlePanel gbp;
    private String mobName;
    private int mobHealth;
    private int mobDamage;
    private int mobLevel;
    
    public Monster(GameBattlePanel gbp) {
        this.gbp = gbp;
    }
    
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
    public void attack(game.utilities.Player player) {
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
